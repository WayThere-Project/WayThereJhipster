import dayjs from 'dayjs';

export interface ICustomer {
  id?: number;
  customerID?: number | null;
  fname?: string | null;
  lname?: string | null;
  dob?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<ICustomer> = {};
