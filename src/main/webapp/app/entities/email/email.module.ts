import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JioTmsApplicationSharedModule } from 'app/shared/shared.module';
import { EmailComponent } from './email.component';
import { EmailDetailComponent } from './email-detail.component';
import { EmailUpdateComponent } from './email-update.component';
import { EmailDeleteDialogComponent } from './email-delete-dialog.component';
import { emailRoute } from './email.route';

@NgModule({
  imports: [JioTmsApplicationSharedModule, RouterModule.forChild(emailRoute)],
  declarations: [EmailComponent, EmailDetailComponent, EmailUpdateComponent, EmailDeleteDialogComponent],
  entryComponents: [EmailDeleteDialogComponent]
})
export class JioTmsApplicationEmailModule {}
