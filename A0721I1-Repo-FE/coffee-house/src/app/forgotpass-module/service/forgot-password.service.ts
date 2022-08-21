import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {take} from 'rxjs/operators';

const ApiURL = 'http://localhost:8080/api/account';

@Injectable({
  providedIn: 'root'
})
export class ForgotPasswordService{

  constructor(private http: HttpClient) {
  }

  processForgotPassword(email): Observable<any> {
    return this.http.post(ApiURL + '/forgot_password', email, {responseType: 'text'});
  }

  processResetPassword(token: string, newPassword: string): Observable<any> {
    return this.http.post(ApiURL + '/reset_password?token=' + token + '&password=' + newPassword, {
      token,
      newPassword
    }, {responseType: 'text'});
  }
}
