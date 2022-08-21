import { Injectable } from '@angular/core';
import {
  Router,
  CanActivate,
  ActivatedRouteSnapshot
} from '@angular/router';
import decode from 'jwt-decode';
import {TokenStorageService} from './token-storage.service';

@Injectable({
  providedIn: 'root'
})

export class RoleGuardService implements CanActivate {
  constructor(public auth: TokenStorageService, public router: Router,
              private tokenStorageService: TokenStorageService) {}
  canActivate(route: ActivatedRouteSnapshot): boolean {
    // this will be passed from the route config
    // on the data property
    const expectedRole = route.data.expectedRole;
    const token = localStorage.getItem(this.tokenStorageService.getUser());
    // decode the token to get its payload
    const tokenPayload = decode(token);
    if (
      !this.auth.isAuthenticated() ||
      // @ts-ignore
      tokenPayload.roles !== expectedRole
    ) {
      this.router.navigate(['login/authentication']);
      return false;
    }
    return true;
  }
}
