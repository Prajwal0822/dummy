import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { FormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthGuard  } from './shared';
import { LayoutModule } from './layout/layout.module';

// Services
import { AppConfig        } from './app-config';
import { UserInfoService  } from './shared/services/user-info.service';
import { ApiRequestService} from './shared/services/api-request.service';
import { LoginService     } from './shared/services/login.service';
import { NgxPermissionsModule } from 'ngx-permissions';

// AoT requires an exported function for factories
export function createTranslateLoader(http: HttpClient) {
    // for development
    // return new TranslateHttpLoader(http, '/start-angular/SB-Admin-BS4-Angular-5/master/dist/assets/i18n/', '.json');
    return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
		FormsModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: createTranslateLoader,
                deps: [HttpClient]
            }
        }),
        NgxPermissionsModule.forRoot(),
        AppRoutingModule
    ],
    declarations: [AppComponent],
    providers: [
		AuthGuard,
		UserInfoService,
		ApiRequestService,
		LoginService,
		AppConfig,
        LayoutModule],
    bootstrap: [AppComponent]
})
export class AppModule {}
