import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JioTmsApplicationSharedModule } from 'app/shared/shared.module';
import { ReferenceComponent } from './reference.component';
import { ReferenceDetailComponent } from './reference-detail.component';
import { ReferenceUpdateComponent } from './reference-update.component';
import { ReferenceDeleteDialogComponent } from './reference-delete-dialog.component';
import { referenceRoute } from './reference.route';

@NgModule({
  imports: [JioTmsApplicationSharedModule, RouterModule.forChild(referenceRoute)],
  declarations: [ReferenceComponent, ReferenceDetailComponent, ReferenceUpdateComponent, ReferenceDeleteDialogComponent],
  entryComponents: [ReferenceDeleteDialogComponent]
})
export class JioTmsApplicationReferenceModule {}
