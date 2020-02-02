import { IFiles } from 'app/shared/model/files.model';

export interface IEmail {
  id?: number;
  userto?: string;
  usercc?: string;
  userbcc?: string;
  message?: string;
  files?: IFiles[];
}

export class Email implements IEmail {
  constructor(
    public id?: number,
    public userto?: string,
    public usercc?: string,
    public userbcc?: string,
    public message?: string,
    public files?: IFiles[]
  ) {}
}
