import { IEmail } from 'app/shared/model/email.model';

export interface IFiles {
  id?: number;
  contentContentType?: string;
  content?: any;
  email?: IEmail;
}

export class Files implements IFiles {
  constructor(public id?: number, public contentContentType?: string, public content?: any, public email?: IEmail) {}
}
