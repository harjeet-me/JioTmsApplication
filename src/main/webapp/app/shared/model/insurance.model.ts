import { Moment } from 'moment';

export interface IInsurance {
  id?: number;
  providerName?: string;
  issueDate?: Moment;
  expiryDate?: Moment;
  policyDocumentContentType?: string;
  policyDocument?: any;
  coverageStatement?: string;
  ownerOperatorId?: number;
}

export class Insurance implements IInsurance {
  constructor(
    public id?: number,
    public providerName?: string,
    public issueDate?: Moment,
    public expiryDate?: Moment,
    public policyDocumentContentType?: string,
    public policyDocument?: any,
    public coverageStatement?: string,
    public ownerOperatorId?: number
  ) {}
}
