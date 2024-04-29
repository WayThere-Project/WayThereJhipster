import dayjs from 'dayjs';
import { IVehicle } from 'app/shared/model/vehicle.model';

export interface IDriver {
  id?: number;
  driverID?: number | null;
  fname?: string | null;
  lname?: string | null;
  dob?: dayjs.Dayjs | null;
  vehicle?: IVehicle | null;
}

export const defaultValue: Readonly<IDriver> = {};
