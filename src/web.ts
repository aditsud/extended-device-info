import { WebPlugin } from '@capacitor/core';

import type { ExtendedDeviceInfoPlugin } from './definitions';

export class ExtendedDeviceInfoWeb
  extends WebPlugin
  implements ExtendedDeviceInfoPlugin {
    async getRamSize(): Promise<{value: number}> {
      return { value: -1 }
    }
    async getScreenSize(): Promise<{value: string}> {
      return { value: window.screen.width + 'x' + window.screen.height + ' px' }
    }
    async getCPUInfo(): Promise<{value: string}> {
      return { value: '-' }
    }
    async getNumberCPU(): Promise<{value: number}> {
      return { value: -1 }
    }
    async getProcessor(): Promise<{value: string}> {
      return { value: '-' }
    }
    async getClockSpeed(): Promise<{value: number}> {
      return { value: 0 }
    }
    async getFrequencies(): Promise<{value: string}> {
      return { value: '-' }
    }
}
