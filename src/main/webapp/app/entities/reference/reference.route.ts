import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IReference, Reference } from 'app/shared/model/reference.model';
import { ReferenceService } from './reference.service';
import { ReferenceComponent } from './reference.component';
import { ReferenceDetailComponent } from './reference-detail.component';
import { ReferenceUpdateComponent } from './reference-update.component';

@Injectable({ providedIn: 'root' })
export class ReferenceResolve implements Resolve<IReference> {
  constructor(private service: ReferenceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReference> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((reference: HttpResponse<Reference>) => {
          if (reference.body) {
            return of(reference.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Reference());
  }
}

export const referenceRoute: Routes = [
  {
    path: '',
    component: ReferenceComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jioTmsApplicationApp.reference.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ReferenceDetailComponent,
    resolve: {
      reference: ReferenceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jioTmsApplicationApp.reference.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ReferenceUpdateComponent,
    resolve: {
      reference: ReferenceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jioTmsApplicationApp.reference.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ReferenceUpdateComponent,
    resolve: {
      reference: ReferenceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jioTmsApplicationApp.reference.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
