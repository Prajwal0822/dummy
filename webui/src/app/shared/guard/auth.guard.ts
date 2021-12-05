import { Injectable } from '@angular/core';
import { Router, CanActivate, CanActivateChild,ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserInfoService } from '../services/user-info.service';
import {NgxPermissionsService} from "ngx-permissions";

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild {

    constructor(
        private router: Router,
        private userInfoService: UserInfoService,
        private permissionsService: NgxPermissionsService
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        return this.checkLogin();     
    }

    canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        return this.canActivate(route, state);
    }

    checkLogin(): boolean {
        if (this.userInfoService.isLoggedIn()) {

            this.permissionsService.flushPermissions();
            this.permissionsService.loadPermissions(this.userInfoService.getUserRoles());

			console.log("User logged in successfully.");
            console.log("Permissions loaded successfully.");
            return true;
        }
        console.log("User is not logged - This routing guard prvents redirection to any routes that needs logging.");
        this.router.navigate(['/login']);
        return false;
    }
}
