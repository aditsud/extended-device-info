package id.aditsud.capacitorplugin.extendeddeviceinfo;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "ExtendedDeviceInfo")
public class ExtendedDeviceInfoPlugin extends Plugin {

    private ExtendedDeviceInfo implementation;
    @Override
    public void load() {
        implementation = new ExtendedDeviceInfo(getContext());
    }


    @PluginMethod
    public void getRamSize(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", implementation.getRamSize());
        call.resolve(ret);
    }

    @PluginMethod
    public void getScreenSize(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", implementation.getScreenSize());
        call.resolve(ret);
    }

    @PluginMethod
    public void getScreenSizeInInch(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", implementation.getScreenSizeInInch());
        call.resolve(ret);
    }

    @PluginMethod
    public void getNumberCPU(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", implementation.getNumberCPU());
        call.resolve(ret);
    }

    @PluginMethod
    public void getCPUInfo(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", implementation.getCPUName());
        call.resolve(ret);
    }

    @PluginMethod
    public void getProcessor(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", implementation.getProcessor());
        call.resolve(ret);
    }

    @PluginMethod
    public void getClockSpeed(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", implementation.getClockSpeed());
        call.resolve(ret);
    }

    @PluginMethod
    public void getFrequencies(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", implementation.getFrequencies());
        call.resolve(ret);
    }
}
