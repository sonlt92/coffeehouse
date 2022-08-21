import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Oder} from '../../model/oder';
import {OderDetail} from '../../model/oderDetail';


@Injectable({
  providedIn: 'root'
})
export class OderService {
  public API_PAGE = 'http://localhost:8080/api/order/listOrder';
  public API_LIST = 'http://localhost:8080/api/order/list';
  public API_DETAIL = 'http://localhost:8080/api/order-detail/orderDetail/';

  constructor(private httpClient: HttpClient) {
  }


  getList(): Observable<Oder[]> {
    return this.httpClient.get<Oder[]>(this.API_LIST);
  }
  searchList(idOrder: string, dateOrder: string): Observable<Oder[]> {
    return this.httpClient.get<Oder[]>(this.API_LIST + '?idOrder=' + idOrder + '&dateOrder=' + dateOrder );
  }

  getPage(index: number): Observable<Oder[]> {
    return this.httpClient.get<Oder[]>(this.API_PAGE + '?page=' + index);
  }
  searchPage( idOrder: string, dateOrder: string, index1: number): Observable<Oder[]> {
    return this.httpClient.get<Oder[]>(this.API_PAGE + '?idOrder=' + idOrder + '&dateOrder=' + dateOrder + '&page=' + index1);
  }

  public getOrderDetailById(id: number): Observable<OderDetail[]> {
    return this.httpClient.get<OderDetail[]>(this.API_DETAIL + id);
  }


  // findByAllPaginng(page: number): Observable<Product[]>{
  //   return this.httpClient.get<Order[]>(this.URLPRODUCT + '/page' + '?page=' + page );
  // }


}
