import { Moment } from 'moment';
import { IReference } from 'app/shared/model/reference.model';
import { IInvoiceItem } from 'app/shared/model/invoice-item.model';
import { ICustomer } from 'app/shared/model/customer.model';
import { ITrip } from 'app/shared/model/trip.model';
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
  reference1?: IReference;
  reference2?: IReference;
  reference3?: IReference;
  invoiceItems?: IInvoiceItem[];
  customer?: ICustomer;
  trip?: ITrip;
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
    public reference1?: IReference,
    public reference2?: IReference,
    public reference3?: IReference,
    public invoiceItems?: IInvoiceItem[],
    public customer?: ICustomer,
    public trip?: ITrip
  ) {}
}
