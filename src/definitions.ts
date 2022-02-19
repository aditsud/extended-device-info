export interface ExtendedDeviceInfoPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
