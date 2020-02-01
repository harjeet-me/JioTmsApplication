import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvoiceRef } from 'app/shared/model/invoice-ref.model';
import { InvoiceRefService } from './invoice-ref.service';

@Component({
  templateUrl: './invoice-ref-delete-dialog.component.html'
})
export class InvoiceRefDeleteDialogComponent {
  invoiceRef?: IInvoiceRef;

  constructor(
    protected invoiceRefService: InvoiceRefService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.invoiceRefService.delete(id).subscribe(() => {
      this.eventManager.broadcast('invoiceRefListModification');
      this.activeModal.close();
    });
  }
}
