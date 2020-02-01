export interface IInvoiceRef {
  id?: number;
  refName?: string;
}

export class InvoiceRef implements IInvoiceRef {
  constructor(public id?: number, public refName?: string) {}
}
