export interface IContainer {
  id?: number;
  number?: string;
  description?: string;
  size?: number;
  tripId?: number;
}

export class Container implements IContainer {
  constructor(public id?: number, public number?: string, public description?: string, public size?: number, public tripId?: number) {}
}
