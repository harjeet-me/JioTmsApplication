import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JioTmsApplicationSharedModule } from 'app/shared/shared.module';
import { OwnerOperatorComponent } from './owner-operator.component';
import { OwnerOperatorDetailComponent } from './owner-operator-detail.component';
import { OwnerOperatorUpdateComponent } from './owner-operator-update.component';
import { OwnerOperatorDeleteDialogComponent } from './owner-operator-delete-dialog.component';
import { ownerOperatorRoute } from './owner-operator.route';

@NgModule({
  imports: [JioTmsApplicationSharedModule, RouterModule.forChild(ownerOperatorRoute)],
  declarations: [OwnerOperatorComponent, OwnerOperatorDetailComponent, OwnerOperatorUpdateComponent, OwnerOperatorDeleteDialogComponent],
  entryComponents: [OwnerOperatorDeleteDialogComponent]
})
export class JioTmsApplicationOwnerOperatorModule {}
