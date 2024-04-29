export interface ILocation {
  id?: number;
  locationID?: number | null;
  landmarkName?: string | null;
  addressLine1?: string | null;
  addressLine2?: string | null;
  city?: string | null;
  state?: string | null;
  country?: string | null;
  zipCode?: number | null;
}

export const defaultValue: Readonly<ILocation> = {};
