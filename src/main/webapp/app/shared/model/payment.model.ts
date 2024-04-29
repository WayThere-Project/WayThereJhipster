import { ICustomer } from 'app/shared/model/customer.model';

export interface IPayment {
  id?: number;
  paymentID?: number | null;
  paymentMode?: number | null;
  customer?: ICustomer | null;
}

export const defaultValue: Readonly<IPayment> = {};
