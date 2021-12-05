import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { PageHeaderModule } from './page-header/page-header.module';
import { TranslateModule } from '@ngx-translate/core';
import { LoadingModule } from 'ngx-loading';
import { GrowlModule} from 'primeng/growl';
import { DialogModule} from 'primeng/dialog';
import { TableModule } from 'primeng/table';
import { ButtonModule} from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';

@NgModule({
    imports: [CommonModule],
    exports: [CommonModule, FormsModule, ReactiveFormsModule, LoadingModule, GrowlModule,TableModule,PageHeaderModule,
        TranslateModule, DialogModule, ButtonModule, DropdownModule]
})
export class SharedModule {}
