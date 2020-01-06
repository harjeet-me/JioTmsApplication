import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JioTmsApplicationSharedModule } from 'app/shared/shared.module';
import { AccountsComponent } from './accounts.component';
import { AccountsDetailComponent } from './accounts-detail.component';
import { AccountsUpdateComponent } from './accounts-update.component';
import { AccountsDeleteDialogComponent } from './accounts-delete-dialog.component';
import { accountsRoute } from './accounts.route';

@NgModule({
  imports: [JioTmsApplicationSharedModule, RouterModule.forChild(accountsRoute)],
  declarations: [AccountsComponent, AccountsDetailComponent, AccountsUpdateComponent, AccountsDeleteDialogComponent],
  entryComponents: [AccountsDeleteDialogComponent]
})
export class JioTmsApplicationAccountsModule {}
