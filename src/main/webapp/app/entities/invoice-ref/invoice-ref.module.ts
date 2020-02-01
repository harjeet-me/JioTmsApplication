import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JioTmsApplicationSharedModule } from 'app/shared/shared.module';
import { InvoiceRefComponent } from './invoice-ref.component';
import { InvoiceRefDetailComponent } from './invoice-ref-detail.component';
import { InvoiceRefUpdateComponent } from './invoice-ref-update.component';
import { InvoiceRefDeleteDialogComponent } from './invoice-ref-delete-dialog.component';
import { invoiceRefRoute } from './invoice-ref.route';

@NgModule({
  imports: [JioTmsApplicationSharedModule, RouterModule.forChild(invoiceRefRoute)],
  declarations: [InvoiceRefComponent, InvoiceRefDetailComponent, InvoiceRefUpdateComponent, InvoiceRefDeleteDialogComponent],
  entryComponents: [InvoiceRefDeleteDialogComponent]
})
export class JioTmsApplicationInvoiceRefModule {}
