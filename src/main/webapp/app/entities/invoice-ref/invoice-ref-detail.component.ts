import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInvoiceRef } from 'app/shared/model/invoice-ref.model';

@Component({
  selector: 'jhi-invoice-ref-detail',
  templateUrl: './invoice-ref-detail.component.html'
})
export class InvoiceRefDetailComponent implements OnInit {
  invoiceRef: IInvoiceRef | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invoiceRef }) => (this.invoiceRef = invoiceRef));
  }

  previousState(): void {
    window.history.back();
  }
}
