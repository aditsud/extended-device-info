import { WebPlugin } from '@capacitor/core';

import type { ExtendedDeviceInfoPlugin } from './definitions';

export class ExtendedDeviceInfoWeb
  extends WebPlugin
  implements ExtendedDeviceInfoPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
