export interface IFileSystem {
  id?: number;
  dataContentType?: string;
  data?: any;
  emailId?: number;
}

export class FileSystem implements IFileSystem {
  constructor(public id?: number, public dataContentType?: string, public data?: any, public emailId?: number) {}
}
