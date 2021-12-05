import { Component, OnInit } from '@angular/core';
import { LoginService } from 'app/shared';
import { Router } from '@angular/router';
import { BasePageComponent} from "app/layout/base.page.component";
import { TranslateService } from "@ngx-translate/core";
import { PrimeMessageType } from "app/shared";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent extends BasePageComponent implements OnInit {
    model: any = {};
    errMsg:string = '';
    loading:boolean = false;

    constructor(public router: Router, private loginService: LoginService, translateService: TranslateService,) {
        super(translateService);

        translateService.addLangs(['en', 'tr']);
        translateService.setDefaultLang('en');
        //const browserLang = this.translate.getBrowserLang();
        //this.translate.use(browserLang.match(/en|fr|ur|es|it|fa|de|zh-CHS/) ? browserLang : 'en');
        translateService.use('en');
    }

    ngOnInit() {}

    onLoggedin() {
        this.loading = true;
        this.loginService.getToken(this.model.username, this.model.password)
            .subscribe(
                resp => {
                    this.loading = false;
                    if (resp === undefined || resp.success === undefined) {
                        return false;
                    } else {
                        if(resp.success === true) {
                            localStorage.setItem('isLoggedin', 'true');
                            this.router.navigate(["/workspace"]);
                        } else {
                            this.pushMessage(PrimeMessageType.error, 'login.failed.summary', 'login.failed.message');
                            return false;
                        }
                    }
                },
                errResponse => {
                    this.loading = false;
                    switch(errResponse.status){
                        case 401:
                            this.errMsg = 'Username or password is incorrect';
                            break;
                        case 404:
                            this.errMsg = 'Service not found';
                        case 408:
                            this.errMsg = 'Request Timedout';
                        case 500:
                            this.errMsg = 'Internal Server Error';
                        default:
                            this.errMsg = 'Server Error';
                    }
                }
            );
    }
}
