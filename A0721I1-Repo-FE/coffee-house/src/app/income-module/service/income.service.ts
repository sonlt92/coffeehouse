import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IncomeService {
  URL_API = 'http://localhost:8080/manager/api/income';

  constructor(private httpClient: HttpClient) {
  }

  sumTotalOrderDay(): Observable<number> {
    return this.httpClient.get<number>(this.URL_API + '/day');
  }

  sumTotalOrderWeek(): Observable<number> {
    return this.httpClient.get<number>(this.URL_API + '/week');
  }

  sumTotalOrderMonth(): Observable<number> {
    return this.httpClient.get<number>(this.URL_API + '/month');
  }

  sumTotalOrderYear(): Observable<number> {
    return this.httpClient.get<number>(this.URL_API + '/year');
  }

  sumTotalOrderDayToDay(startDay: string, endDay: string): Observable<number> {
    return this.httpClient.get<number>(this.URL_API + '/daytoday?startDay=' + startDay + '&endDay=' + endDay);
  }

  countProductCafe(): Observable<number> {
    return this.httpClient.get<number>(this.URL_API + '/countCafe');
  }

  countProductTea(): Observable<number> {
    return this.httpClient.get<number>(this.URL_API + '/countTea');
  }

  countProductCake(): Observable<number> {
    return this.httpClient.get<number>(this.URL_API + '/countCake');
  }

  countProductOther(): Observable<number> {
    return this.httpClient.get<number>(this.URL_API + '/countOther');
  }
}
