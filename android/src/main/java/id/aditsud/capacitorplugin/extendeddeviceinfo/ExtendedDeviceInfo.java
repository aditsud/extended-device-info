package id.aditsud.capacitorplugin.extendeddeviceinfo;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.regex.Pattern;

public class ExtendedDeviceInfo {

    private Context context;

    ExtendedDeviceInfo(Context context) {
        this.context = context;
    }

    public long getRamSize() {
        ActivityManager actManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        return memInfo.totalMem;
    }

    public double getScreenSizeInInch(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        double x = Math.pow(displayMetrics.widthPixels / displayMetrics.xdpi, 2);
        double y = Math.pow((displayMetrics.heightPixels + getNavigationBarHeight()) / displayMetrics.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        return screenInches;
    }

    public String getScreenSize(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels + getNavigationBarHeight();
        int width = displayMetrics.widthPixels;
        return Integer.toString(width) + "x" + Integer.toString(height) + "px " + Integer.toString(displayMetrics.densityDpi) + "dpi";
    }

    private int getNavigationBarHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            ((Activity) context).getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }
        return 0;
    }

    public int getNumberCPU(){
        if(Build.VERSION.SDK_INT >= 17) {
            return Runtime.getRuntime().availableProcessors();
        }
        else {
           return getNumCoresOldPhones();
        }
    }

    private int getNumCoresOldPhones() {
        //Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                //Check if filename is "cpu", followed by a single digit number
                if(Pattern.matches("cpu[0-9]+", pathname.getName())) {
                    return true;
                }
                return false;
            }      
        }
    
        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            //Return the number of cores (virtual CPU devices)
            return files.length;
        } catch(Exception e) {
            //Default to return 1 core
            return 1;
        }
    }

    public String getCPUName(){

         try {
             Process proc = Runtime.getRuntime().exec("cat /proc/cpuinfo");
             InputStream is = proc.getInputStream();
             return getStringFromInputStream(is, "Hardware");
         }
         catch (IOException e) {
             return  e.getMessage();
         }
    }

    public String getProcessor(){

        try {
            Process proc = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStream is = proc.getInputStream();
            return getStringFromInputStream(is, "Processor");
        }
        catch (IOException e) {
            return  e.getMessage();
        }
   }

    private static String getStringFromInputStream(InputStream is, String keyNeedle) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;

        try {
            while((line = br.readLine()) != null) {
                sb.append(line);
                String[] data = line.split (":");
                if (data.length > 1) {

                    String key = data[0].trim ().replace (" ", "_");
                    if (key.equals (keyNeedle))
                        return data[1].trim ();

                }
            }
        }
        catch (IOException e) {
            return  e.getMessage();
        }
        finally {
            if(br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    return  e.getMessage();
                }
            }
        }

        return sb.toString();
    }

    public float getClockSpeed() {
        float cpuclock = 0;
        try {
            final StringBuffer s = new StringBuffer();
            final Process p = Runtime.getRuntime()
                    .exec("cat /proc/cpuinfo");
            final BufferedReader input = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null
                    && s.toString().length() == 0) {
                if (line.startsWith("BogoMIPS")) {
                    s.append(line + "\n");
                }
            }
            final String cpuclockstr = s.substring(s.indexOf(":") + 2,
                    s.length());
            cpuclock = Float.parseFloat(cpuclockstr);
        } catch (final Exception err) {
            // if ANYTHING goes wrong, just report 0 since this is only used for
            // performance appraisal.
        }
        return cpuclock;
    }

    public double getFrequencies(){
        String cpuMaxFreq = "";
        String cpuMinFreq = "";
        RandomAccessFile reader1 = null;
        RandomAccessFile reader2 = null;
        try {
            reader1 = new RandomAccessFile("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq", "r");
            cpuMaxFreq = reader1.readLine();
            reader2 = new RandomAccessFile("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq", "r");
            cpuMinFreq = reader2.readLine();
            reader1.close();
            reader2.close();
            return Integer.parseInt(cpuMaxFreq);
        } catch (FileNotFoundException e) {
            return 0;
        } catch (IOException e) {
            return 0;
        }
    }
}
