import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../model/user';

// @ts-ignore
import {JwtHelperService} from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  isLoggedIn: boolean;

  readonly URL_LOGIN = 'http://localhost:8080/authenticate';

  constructor(private httpClient: HttpClient) {}

  get(): Observable<User[]>{
    return this.httpClient.get<User[]>(this.URL_LOGIN);
  }

  login(user: User): Observable<User>{
    return this.httpClient.post<User>(this.URL_LOGIN, user);
  }

}
