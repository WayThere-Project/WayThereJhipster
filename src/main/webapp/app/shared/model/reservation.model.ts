import dayjs from 'dayjs';
import { IInsurance } from 'app/shared/model/insurance.model';
import { ILocation } from 'app/shared/model/location.model';
import { ICustomer } from 'app/shared/model/customer.model';
import { IDriver } from 'app/shared/model/driver.model';
import { IVehicle } from 'app/shared/model/vehicle.model';
import { IRate } from 'app/shared/model/rate.model';
import { IPayment } from 'app/shared/model/payment.model';

export interface IReservation {
  id?: number;
  reservationID?: number | null;
  requestedTime?: number | null;
  date?: dayjs.Dayjs | null;
  startTime?: number | null;
  endTime?: number | null;
  waitTime?: number | null;
  driverRating?: number | null;
  customerRating?: number | null;
  comment?: string | null;
  tripCost?: number | null;
  insurance?: IInsurance | null;
  pickupLocation?: ILocation | null;
  dropoffLocation?: ILocation | null;
  customer?: ICustomer | null;
  driver?: IDriver | null;
  vehicle?: IVehicle | null;
  rate?: IRate | null;
  payment?: IPayment | null;
}

export const defaultValue: Readonly<IReservation> = {};
