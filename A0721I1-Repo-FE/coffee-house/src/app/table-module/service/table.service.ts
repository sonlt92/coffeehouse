import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Status} from '../../model/status';
// @ts-ignore
import {Table} from '../../model/table';
import {OrderDetailMenuDTO} from '../../model/OrderDetailMenuDTO';
import {Oder} from '../../model/oder';
import {environment} from '../../../environments/environment';

const API_URL1 = `${environment.apiBaseUrl}`;
const API_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})

export class TableService {

  constructor(private httpClient: HttpClient) {
  }

  // tslint:disable-next-line:variable-name ban-types
  private _message: String;


  // tslint:disable-next-line:ban-types
  get message(): String {
    return this._message;
  }

  // tslint:disable-next-line:ban-types
  set message(value: String) {
    this._message = value;
  }

  /*
  HuyNN findAllTableWithSearch, updateEmptyTable, deleteTable, findTableById method
  */
  findAllTable(pageNumber: number): Observable<Table[]> {
    return this.httpClient.get<Table[]>(API_URL + '/manager/findAllTableWithSearchAndPaging?page=' + pageNumber);
  }

  findAllTableByCodeTable(codeTable: string, pageNumber: number): Observable<Table[]> {
    // tslint:disable-next-line:max-line-length
    return this.httpClient.get<Table[]>(API_URL + '/manager/findAllTableWithSearchAndPaging?codeTable=' + codeTable + '&page=' + pageNumber);
  }

  findAllTableByIdStatusAndEmptyTable(idStatus: string, emptyTable: string, pageNumber: number): Observable<Table[]> {
    // tslint:disable-next-line:max-line-length
    return this.httpClient.get<Table[]>(API_URL + '/manager/findAllTableWithSearchAndPaging?idStatus=' + idStatus + '&emptyTable=' + emptyTable + '&page=' + pageNumber);
  }

  findAllTableByIdStatus(idStatus: string, pageNumber: number): Observable<Table[]> {
    return this.httpClient.get<Table[]>(API_URL + '/manager/findAllTableWithSearchAndPaging?idStatus=' + idStatus + '&page=' + pageNumber);
  }

  findAllTableByEmptyTable(emptyTable: string, pageNumber: number): Observable<Table[]> {
    // tslint:disable-next-line:max-line-length
    return this.httpClient.get<Table[]>(API_URL + '/manager/findAllTableWithSearchAndPaging?emptyTable=' + emptyTable + '&page=' + pageNumber);
  }

  deleteTable(id: number): Observable<Table> {
    return this.httpClient.delete<Table>(API_URL + '/manager/deleteTable/' + id);
  }

  /*
    //Bin code all Method of Empty Table
  */
  getAllStatusTable(): Observable<Table[]> {
    return this.httpClient.get<Table[]>(API_URL + '/manager/emptyTable');
  }

  getOrderDetailMenuDTO(id: number): Observable<OrderDetailMenuDTO[]> {
    return this.httpClient.get<OrderDetailMenuDTO[]>(`${API_URL}/manager/emptyTable/detailTable/${id}`);
  }

  addNewOrder(idEmployee: number, idTable: number): Observable<Oder> {
    return this.httpClient.post<Oder>(`${API_URL}/manager/emptyTable/saveOrderInTable/${idEmployee}/${idTable}` , {});
  }

  cancelTable(idTable: number): Observable<Oder> {
    return this.httpClient.delete<Oder>(API_URL + '/manager/emptyTable/deleteOrderInTable/' + idTable);
  }

  /* Bin TK */
  payment(idTable: number): Observable<any> {
    // @ts-ignore
    return this.httpClient.patch<any>(`${API_URL}/menu/table/${idTable}/payment`);
  }

  findOrderByTableId(idTable: number): Observable<Oder> {
    return this.httpClient.get<Oder>(`${API_URL}/manager/order/${idTable}`);
  }
  /* //Quang code getAllStatus*/
  getAllStatus(): Observable<Status[]> {
    return this.httpClient.get<Status[]>(API_URL + '/manager/findAllStatus');
  }

  getTableById(id): Observable<Table> {
    return this.httpClient.get<Table>(`${API_URL}/manager/findTableById/${id}`);
  }

  updateTable(id, table: Table): Observable<Table> {
    return this.httpClient.put<Table>(`${API_URL}/manager/updateTable/${id}`, table);
  }

  createTable(table: Table): Observable<Table> {
    return this.httpClient.post<Table>(API_URL + '/manager/createTable', table);
  }

  // tslint:disable-next-line:ban-types
  checkId(id: String): Observable<Table[]> {
    return this.httpClient.get<Table[]>(API_URL + '/manager/checkId?id=' + id);
  }
}
