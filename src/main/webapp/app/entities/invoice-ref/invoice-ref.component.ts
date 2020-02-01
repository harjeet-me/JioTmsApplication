import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInvoiceRef } from 'app/shared/model/invoice-ref.model';
import { InvoiceRefService } from './invoice-ref.service';
import { InvoiceRefDeleteDialogComponent } from './invoice-ref-delete-dialog.component';

@Component({
  selector: 'jhi-invoice-ref',
  templateUrl: './invoice-ref.component.html'
})
export class InvoiceRefComponent implements OnInit, OnDestroy {
  invoiceRefs?: IInvoiceRef[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected invoiceRefService: InvoiceRefService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected activatedRoute: ActivatedRoute
  ) {
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  loadAll(): void {
    if (this.currentSearch) {
      this.invoiceRefService
        .search({
          query: this.currentSearch
        })
        .subscribe((res: HttpResponse<IInvoiceRef[]>) => (this.invoiceRefs = res.body ? res.body : []));
      return;
    }
    this.invoiceRefService.query().subscribe((res: HttpResponse<IInvoiceRef[]>) => {
      this.invoiceRefs = res.body ? res.body : [];
      this.currentSearch = '';
    });
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInvoiceRefs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInvoiceRef): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInvoiceRefs(): void {
    this.eventSubscriber = this.eventManager.subscribe('invoiceRefListModification', () => this.loadAll());
  }

  delete(invoiceRef: IInvoiceRef): void {
    const modalRef = this.modalService.open(InvoiceRefDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.invoiceRef = invoiceRef;
  }
}
