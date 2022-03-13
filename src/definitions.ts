export interface ExtendedDeviceInfoPlugin {
  getRamSize(): Promise<{value: number}>;
  getScreenSize(): Promise<{value: string}>;
  getCPUInfo(): Promise<{value: string}>;
  getNumberCPU(): Promise<{value: number}>;
  getProcessor(): Promise<{value: string}>;
  getClockSpeed(): Promise<{value: number}>;
  getFrequencies(): Promise<{value: string}>;
}
