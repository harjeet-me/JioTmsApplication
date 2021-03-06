import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFiles } from 'app/shared/model/files.model';
import { FilesService } from './files.service';
import { FilesDeleteDialogComponent } from './files-delete-dialog.component';

@Component({
  selector: 'jhi-files',
  templateUrl: './files.component.html'
})
export class FilesComponent implements OnInit, OnDestroy {
  files?: IFiles[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected filesService: FilesService,
    protected dataUtils: JhiDataUtils,
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
      this.filesService
        .search({
          query: this.currentSearch
        })
        .subscribe((res: HttpResponse<IFiles[]>) => (this.files = res.body || []));
      return;
    }

    this.filesService.query().subscribe((res: HttpResponse<IFiles[]>) => (this.files = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFiles();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFiles): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInFiles(): void {
    this.eventSubscriber = this.eventManager.subscribe('filesListModification', () => this.loadAll());
  }

  delete(files: IFiles): void {
    const modalRef = this.modalService.open(FilesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.files = files;
  }
}
