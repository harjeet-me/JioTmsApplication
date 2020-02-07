import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IReference } from 'app/shared/model/reference.model';
import { ReferenceService } from './reference.service';
import { ReferenceDeleteDialogComponent } from './reference-delete-dialog.component';

@Component({
  selector: 'jhi-reference',
  templateUrl: './reference.component.html'
})
export class ReferenceComponent implements OnInit, OnDestroy {
  references?: IReference[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected referenceService: ReferenceService,
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
      this.referenceService
        .search({
          query: this.currentSearch
        })
        .subscribe((res: HttpResponse<IReference[]>) => (this.references = res.body || []));
      return;
    }

    this.referenceService.query().subscribe((res: HttpResponse<IReference[]>) => (this.references = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInReferences();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IReference): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInReferences(): void {
    this.eventSubscriber = this.eventManager.subscribe('referenceListModification', () => this.loadAll());
  }

  delete(reference: IReference): void {
    const modalRef = this.modalService.open(ReferenceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.reference = reference;
  }
}
