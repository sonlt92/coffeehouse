import {Injectable} from '@angular/core';
// @ts-ignore
import {JwtHelperService} from '@auth0/angular-jwt';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})

export class TokenStorageService {

  constructor(private jwtHelper: JwtHelperService) {}

  public isAuthenticated(): boolean {
    const TOKEN = localStorage.getItem(this.getUser().token);
    return this.jwtHelper.isTokenExpired(TOKEN);
  }

  signOut() {
    window.localStorage.clear();
    window.sessionStorage.clear();
  }

  public saveTokenLocal(token: string) {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }

  public saveTokenSession(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    if (localStorage.getItem(TOKEN_KEY) !== null){
      return localStorage.getItem(TOKEN_KEY);
    }else {
      return sessionStorage .getItem(TOKEN_KEY);
    }
  }

  public saveUserLocal(user) {
    window.localStorage.removeItem(USER_KEY);
    window.localStorage.setItem(USER_KEY, JSON.stringify(user));
  }
  public saveUserSession(user) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser() {
    if (localStorage.getItem(USER_KEY) !== null){
      return JSON.parse(localStorage.getItem(USER_KEY));
    }else {
      return JSON.parse(sessionStorage.getItem(USER_KEY));
    }
  }
}
