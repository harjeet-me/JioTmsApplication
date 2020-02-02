import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'company-profile',
        loadChildren: () => import('./company-profile/company-profile.module').then(m => m.JioTmsApplicationCompanyProfileModule)
      },
      {
        path: 'customer',
        loadChildren: () => import('./customer/customer.module').then(m => m.JioTmsApplicationCustomerModule)
      },
      {
        path: 'trip',
        loadChildren: () => import('./trip/trip.module').then(m => m.JioTmsApplicationTripModule)
      },
      {
        path: 'invoice',
        loadChildren: () => import('./invoice/invoice.module').then(m => m.JioTmsApplicationInvoiceModule)
      },
      {
        path: 'invoice-item',
        loadChildren: () => import('./invoice-item/invoice-item.module').then(m => m.JioTmsApplicationInvoiceItemModule)
      },
      {
        path: 'accounts',
        loadChildren: () => import('./accounts/accounts.module').then(m => m.JioTmsApplicationAccountsModule)
      },
      {
        path: 'transactions-record',
        loadChildren: () =>
          import('./transactions-record/transactions-record.module').then(m => m.JioTmsApplicationTransactionsRecordModule)
      },
      {
        path: 'equipment',
        loadChildren: () => import('./equipment/equipment.module').then(m => m.JioTmsApplicationEquipmentModule)
      },
      {
        path: 'insurance',
        loadChildren: () => import('./insurance/insurance.module').then(m => m.JioTmsApplicationInsuranceModule)
      },
      {
        path: 'contact',
        loadChildren: () => import('./contact/contact.module').then(m => m.JioTmsApplicationContactModule)
      },
      {
        path: 'driver',
        loadChildren: () => import('./driver/driver.module').then(m => m.JioTmsApplicationDriverModule)
      },
      {
        path: 'owner-operator',
        loadChildren: () => import('./owner-operator/owner-operator.module').then(m => m.JioTmsApplicationOwnerOperatorModule)
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.JioTmsApplicationLocationModule)
      },
      {
        path: 'product-item',
        loadChildren: () => import('./product-item/product-item.module').then(m => m.JioTmsApplicationProductItemModule)
      },
      {
        path: 'invoice-ref',
        loadChildren: () => import('./invoice-ref/invoice-ref.module').then(m => m.JioTmsApplicationInvoiceRefModule)
      },
      {
        path: 'reference',
        loadChildren: () => import('./reference/reference.module').then(m => m.JioTmsApplicationReferenceModule)
      },
      {
        path: 'email',
        loadChildren: () => import('./email/email.module').then(m => m.JioTmsApplicationEmailModule)
      },
      {
        path: 'files',
        loadChildren: () => import('./files/files.module').then(m => m.JioTmsApplicationFilesModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JioTmsApplicationEntityModule {}
