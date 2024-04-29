export interface IRate {
  id?: number;
  rateID?: number | null;
  timeOfDay?: number | null;
  vehicleSize?: number | null;
  baseRate?: number | null;
}

export const defaultValue: Readonly<IRate> = {};
