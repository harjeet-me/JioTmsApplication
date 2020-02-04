import { IFileSystem } from 'app/shared/model/file-system.model';

export interface IEmail {
  id?: number;
  userto?: string;
  usercc?: string;
  userbcc?: string;
  subject?: string;
  message?: string;
  multipart?: boolean;
  htmlBody?: boolean;
  attachmentContentType?: string;
  attachment?: any;
  fileSystems?: IFileSystem[];
}

export class Email implements IEmail {
  constructor(
    public id?: number,
    public userto?: string,
    public usercc?: string,
    public userbcc?: string,
    public subject?: string,
    public message?: string,
    public multipart?: boolean,
    public htmlBody?: boolean,
    public attachmentContentType?: string,
    public attachment?: any,
    public fileSystems?: IFileSystem[]
  ) {
    this.multipart = this.multipart || false;
    this.htmlBody = this.htmlBody || false;
  }
}
