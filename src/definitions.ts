export interface ExtendedDeviceInfoPlugin {
  getRamSize(): Promise<{value: number}>;
  getScreenSize(): Promise<{value: string}>;
  getScreenSizeInInch(): Promise<{value: number}>;
  getCPUInfo(): Promise<{value: string}>;
  getNumberCPU(): Promise<{value: number}>;
  getProcessor(): Promise<{value: string}>;
  getClockSpeed(): Promise<{value: number}>;
  getFrequencies(): Promise<{value: number}>;
}
