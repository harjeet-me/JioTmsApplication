import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IReference, Reference } from 'app/shared/model/reference.model';
import { ReferenceService } from './reference.service';

@Component({
  selector: 'jhi-reference-update',
  templateUrl: './reference-update.component.html'
})
export class ReferenceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    reference: []
  });

  constructor(protected referenceService: ReferenceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reference }) => {
      this.updateForm(reference);
    });
  }

  updateForm(reference: IReference): void {
    this.editForm.patchValue({
      id: reference.id,
      reference: reference.reference
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reference = this.createFromForm();
    if (reference.id !== undefined) {
      this.subscribeToSaveResponse(this.referenceService.update(reference));
    } else {
      this.subscribeToSaveResponse(this.referenceService.create(reference));
    }
  }

  private createFromForm(): IReference {
    return {
      ...new Reference(),
      id: this.editForm.get(['id'])!.value,
      reference: this.editForm.get(['reference'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReference>>): void {
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
