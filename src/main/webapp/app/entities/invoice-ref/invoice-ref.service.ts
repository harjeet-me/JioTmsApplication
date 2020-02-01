import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IInvoiceRef } from 'app/shared/model/invoice-ref.model';

type EntityResponseType = HttpResponse<IInvoiceRef>;
type EntityArrayResponseType = HttpResponse<IInvoiceRef[]>;

@Injectable({ providedIn: 'root' })
export class InvoiceRefService {
  public resourceUrl = SERVER_API_URL + 'api/invoice-refs';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/invoice-refs';

  constructor(protected http: HttpClient) {}

  create(invoiceRef: IInvoiceRef): Observable<EntityResponseType> {
    return this.http.post<IInvoiceRef>(this.resourceUrl, invoiceRef, { observe: 'response' });
  }

  update(invoiceRef: IInvoiceRef): Observable<EntityResponseType> {
    return this.http.put<IInvoiceRef>(this.resourceUrl, invoiceRef, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInvoiceRef>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInvoiceRef[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInvoiceRef[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
