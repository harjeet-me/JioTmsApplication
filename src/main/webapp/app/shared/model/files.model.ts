export interface IFiles {
  id?: number;
  contentContentType?: string;
  content?: any;
}

export class Files implements IFiles {
  constructor(public id?: number, public contentContentType?: string, public content?: any) {}
}
