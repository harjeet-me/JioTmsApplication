import { ITransactionsRecord } from 'app/shared/model/transactions-record.model';

export interface IAccounts {
  id?: number;
  balance?: number;
  over30?: number;
  over60?: number;
  over90?: number;
  customerCompany?: string;
  customerId?: number;
  transactionsRecords?: ITransactionsRecord[];
}

export class Accounts implements IAccounts {
  constructor(
    public id?: number,
    public balance?: number,
    public over30?: number,
    public over60?: number,
    public over90?: number,
    public customerCompany?: string,
    public customerId?: number,
    public transactionsRecords?: ITransactionsRecord[]
  ) {}
}
