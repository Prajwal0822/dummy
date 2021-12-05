import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';

const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            { path: '', redirectTo: 'homepage' },
            { path: 'homepage', loadChildren: './blank-page/blank-page.module#BlankPageModule' },
            { path: 'workspace', loadChildren: './workspaces/workspaces.module#WorkspacesModule' },
            { path: 'httpMock', loadChildren: './blank-page/blank-page.module#BlankPageModule' },
            { path: 'sshTelnetMock', loadChildren: './blank-page/blank-page.module#BlankPageModule' },
            { path: 'databaseMock', loadChildren: './blank-page/blank-page.module#BlankPageModule' },
            { path: 'testCases', loadChildren: './blank-page/blank-page.module#BlankPageModule' },
            { path: 'settings', loadChildren: './blank-page/blank-page.module#BlankPageModule' }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule {}
