import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInvoiceRef, InvoiceRef } from 'app/shared/model/invoice-ref.model';
import { InvoiceRefService } from './invoice-ref.service';
import { InvoiceRefComponent } from './invoice-ref.component';
import { InvoiceRefDetailComponent } from './invoice-ref-detail.component';
import { InvoiceRefUpdateComponent } from './invoice-ref-update.component';

@Injectable({ providedIn: 'root' })
export class InvoiceRefResolve implements Resolve<IInvoiceRef> {
  constructor(private service: InvoiceRefService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInvoiceRef> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((invoiceRef: HttpResponse<InvoiceRef>) => {
          if (invoiceRef.body) {
            return of(invoiceRef.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InvoiceRef());
  }
}

export const invoiceRefRoute: Routes = [
  {
    path: '',
    component: InvoiceRefComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jioTmsApplicationApp.invoiceRef.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: InvoiceRefDetailComponent,
    resolve: {
      invoiceRef: InvoiceRefResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jioTmsApplicationApp.invoiceRef.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: InvoiceRefUpdateComponent,
    resolve: {
      invoiceRef: InvoiceRefResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jioTmsApplicationApp.invoiceRef.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: InvoiceRefUpdateComponent,
    resolve: {
      invoiceRef: InvoiceRefResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jioTmsApplicationApp.invoiceRef.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
