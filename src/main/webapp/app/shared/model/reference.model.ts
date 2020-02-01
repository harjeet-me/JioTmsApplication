export interface IReference {
  id?: number;
  reference?: string;
}

export class Reference implements IReference {
  constructor(public id?: number, public reference?: string) {}
}
