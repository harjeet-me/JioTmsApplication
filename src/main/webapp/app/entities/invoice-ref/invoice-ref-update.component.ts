import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInvoiceRef, InvoiceRef } from 'app/shared/model/invoice-ref.model';
import { InvoiceRefService } from './invoice-ref.service';

@Component({
  selector: 'jhi-invoice-ref-update',
  templateUrl: './invoice-ref-update.component.html'
})
export class InvoiceRefUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    refName: []
  });

  constructor(protected invoiceRefService: InvoiceRefService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invoiceRef }) => {
      this.updateForm(invoiceRef);
    });
  }

  updateForm(invoiceRef: IInvoiceRef): void {
    this.editForm.patchValue({
      id: invoiceRef.id,
      refName: invoiceRef.refName
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invoiceRef = this.createFromForm();
    if (invoiceRef.id !== undefined) {
      this.subscribeToSaveResponse(this.invoiceRefService.update(invoiceRef));
    } else {
      this.subscribeToSaveResponse(this.invoiceRefService.create(invoiceRef));
    }
  }

  private createFromForm(): IInvoiceRef {
    return {
      ...new InvoiceRef(),
      id: this.editForm.get(['id'])!.value,
      refName: this.editForm.get(['refName'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvoiceRef>>): void {
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
}
