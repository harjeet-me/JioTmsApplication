import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IFileSystem, FileSystem } from 'app/shared/model/file-system.model';
import { FileSystemService } from './file-system.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IEmail } from 'app/shared/model/email.model';
import { EmailService } from 'app/entities/email/email.service';

@Component({
  selector: 'jhi-file-system-update',
  templateUrl: './file-system-update.component.html'
})
export class FileSystemUpdateComponent implements OnInit {
  isSaving = false;
  emails: IEmail[] = [];

  editForm = this.fb.group({
    id: [],
    data: [],
    dataContentType: [],
    emailId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected fileSystemService: FileSystemService,
    protected emailService: EmailService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fileSystem }) => {
      this.updateForm(fileSystem);

      this.emailService.query().subscribe((res: HttpResponse<IEmail[]>) => (this.emails = res.body || []));
    });
  }

  updateForm(fileSystem: IFileSystem): void {
    this.editForm.patchValue({
      id: fileSystem.id,
      data: fileSystem.data,
      dataContentType: fileSystem.dataContentType,
      emailId: fileSystem.emailId
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('jioTmsApplicationApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fileSystem = this.createFromForm();
    if (fileSystem.id !== undefined) {
      this.subscribeToSaveResponse(this.fileSystemService.update(fileSystem));
    } else {
      this.subscribeToSaveResponse(this.fileSystemService.create(fileSystem));
    }
  }

  private createFromForm(): IFileSystem {
    return {
      ...new FileSystem(),
      id: this.editForm.get(['id'])!.value,
      dataContentType: this.editForm.get(['dataContentType'])!.value,
      data: this.editForm.get(['data'])!.value,
      emailId: this.editForm.get(['emailId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFileSystem>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IEmail): any {
    return item.id;
  }
}
