import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReference } from 'app/shared/model/reference.model';
import { ReferenceService } from './reference.service';

@Component({
  templateUrl: './reference-delete-dialog.component.html'
})
export class ReferenceDeleteDialogComponent {
  reference?: IReference;

  constructor(protected referenceService: ReferenceService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.referenceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('referenceListModification');
      this.activeModal.close();
    });
  }
}
