import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEmail, Email } from 'app/shared/model/email.model';
import { EmailService } from './email.service';

@Component({
  selector: 'jhi-email-update',
  templateUrl: './email-update.component.html'
})
export class EmailUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    userto: [],
    usercc: [],
    userbcc: [],
    message: []
  });

  constructor(protected emailService: EmailService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ email }) => {
      this.updateForm(email);
    });
  }

  updateForm(email: IEmail): void {
    this.editForm.patchValue({
      id: email.id,
      userto: email.userto,
      usercc: email.usercc,
      userbcc: email.userbcc,
      message: email.message
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const email = this.createFromForm();
    if (email.id !== undefined) {
      this.subscribeToSaveResponse(this.emailService.update(email));
    } else {
      this.subscribeToSaveResponse(this.emailService.create(email));
    }
  }

  private createFromForm(): IEmail {
    return {
      ...new Email(),
      id: this.editForm.get(['id'])!.value,
      userto: this.editForm.get(['userto'])!.value,
      usercc: this.editForm.get(['usercc'])!.value,
      userbcc: this.editForm.get(['userbcc'])!.value,
      message: this.editForm.get(['message'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmail>>): void {
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
