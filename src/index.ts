import { registerPlugin } from '@capacitor/core';

import type { ExtendedDeviceInfoPlugin } from './definitions';

const ExtendedDeviceInfo = registerPlugin<ExtendedDeviceInfoPlugin>(
  'ExtendedDeviceInfo',
  {
    web: () => import('./web').then(m => new m.ExtendedDeviceInfoWeb()),
  },
);

export * from './definitions';
export { ExtendedDeviceInfo };
