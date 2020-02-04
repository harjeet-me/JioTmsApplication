export interface IInvoiceItem {
  id?: number;
  itemName?: string;
  description?: string;
  qty?: number;
  price?: number;
  discount?: number;
  total?: number;
  invoiceId?: number;
}

export class InvoiceItem implements IInvoiceItem {
  constructor(
    public id?: number,
    public itemName?: string,
    public description?: string,
    public qty?: number,
    public price?: number,
    public discount?: number,
    public total?: number,
    public invoiceId?: number
  ) {}
}
