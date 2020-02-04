import { Moment } from 'moment';
import { IInvoiceItem } from 'app/shared/model/invoice-item.model';
import { TaxType } from 'app/shared/model/enumerations/tax-type.model';
import { CURRENCY } from 'app/shared/model/enumerations/currency.model';
import { InvoiceStatus } from 'app/shared/model/enumerations/invoice-status.model';

export interface IInvoice {
  id?: number;
  orderNo?: string;
  taxRate?: number;
  taxType?: TaxType;
  currency?: CURRENCY;
  invoiceTaxTotal?: number;
  invoiceSubTotal?: number;
  invoiceTotal?: number;
  invoiceDate?: Moment;
  invoicePaidDate?: Moment;
  refValue1?: string;
  refValue2?: string;
  refValue3?: string;
  payRefNo?: string;
  invoiceDueDate?: Moment;
  status?: InvoiceStatus;
  invoicePdfContentType?: string;
  invoicePdf?: any;
  remarks?: string;
  reference1Reference?: string;
  reference1Id?: number;
  reference2Reference?: string;
  reference2Id?: number;
  reference3Reference?: string;
  reference3Id?: number;
  invoiceItems?: IInvoiceItem[];
  customerCompany?: string;
  customerId?: number;
  tripId?: number;
}

export class Invoice implements IInvoice {
  constructor(
    public id?: number,
    public orderNo?: string,
    public taxRate?: number,
    public taxType?: TaxType,
    public currency?: CURRENCY,
    public invoiceTaxTotal?: number,
    public invoiceSubTotal?: number,
    public invoiceTotal?: number,
    public invoiceDate?: Moment,
    public invoicePaidDate?: Moment,
    public refValue1?: string,
    public refValue2?: string,
    public refValue3?: string,
    public payRefNo?: string,
    public invoiceDueDate?: Moment,
    public status?: InvoiceStatus,
    public invoicePdfContentType?: string,
    public invoicePdf?: any,
    public remarks?: string,
    public reference1Reference?: string,
    public reference1Id?: number,
    public reference2Reference?: string,
    public reference2Id?: number,
    public reference3Reference?: string,
    public reference3Id?: number,
    public invoiceItems?: IInvoiceItem[],
    public customerCompany?: string,
    public customerId?: number,
    public tripId?: number
  ) {}
}
