import dayjs from 'dayjs';

export interface IVehicle {
  id?: number;
  vehicleID?: number | null;
  vehicleSize?: number | null;
  make?: string | null;
  lastServicingDate?: dayjs.Dayjs | null;
  model?: string | null;
}

export const defaultValue: Readonly<IVehicle> = {};
