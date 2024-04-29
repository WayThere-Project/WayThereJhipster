import dayjs from 'dayjs';

export interface IInsurance {
  id?: number;
  insID?: number | null;
  insDescription?: string | null;
  startDate?: dayjs.Dayjs | null;
  expirationDate?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IInsurance> = {};
