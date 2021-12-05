import { NgModule } from '@angular/core';

import { WorkspacesRoutingModule } from './workspaces-routing.module';
import { WorkspacesComponent } from './workspaces.component';
import { SharedModule } from 'app/shared';
import { WorkspaceService } from 'app/shared';

@NgModule({
    imports: [SharedModule, WorkspacesRoutingModule],
    declarations: [WorkspacesComponent],
    providers: [WorkspaceService]
})
export class WorkspacesModule {}
