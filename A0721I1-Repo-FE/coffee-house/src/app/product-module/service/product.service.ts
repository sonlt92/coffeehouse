import {Product} from '../../model/product';
import {TypeProduct} from '../../model/typeProduct';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http';
const API_URL = 'http://localhost:8080/find';
const API_URL1 = 'http://localhost:8080/cart';


@Injectable({
  providedIn: 'root'
})
export class ProductService {
  URLPRODUCT = 'http://localhost:8080/product';
  // tslint:disable-next-line:variable-name
  constructor(private http: HttpClient, private _httpClient: HttpClient) {
  }


  findByAll(): Observable<Product[]> {
    return this.http.get<Product[]>(this.URLPRODUCT);
  }

  findByAllPaginng(page: number): Observable<Product[]> {
    return this.http.get<Product[]>(this.URLPRODUCT + '/page' + '?page=' + page);
  }

  deleteById(id: any): Observable<any> {
    return this.http.delete<any>(this.URLPRODUCT + '/' + id);
  }

  search(code: string, name: string): Observable<Product[]> {
    return this.http.get<Product[]>(this.URLPRODUCT + '/search?code=' + code + '&name=' + name);
  }

  searchPage(code: string, name: string, page1: number): Observable<Product[]> {
    return this.http.get<Product[]>(this.URLPRODUCT + '/searchPage?code=' + code + '&name=' + name + '&page1=' + page1);
  }

  findById(id: any): Observable<Product>{
    return this.http.get<Product>(this.URLPRODUCT + '/find/' + id );
  }

  findType(): Observable<TypeProduct[]> {
    return this.http.get<TypeProduct[]>(this.URLPRODUCT + '/type');
  }

  updateProduct(product1: Product): Observable<void> {
    return this.http.patch<void>(this.URLPRODUCT + '/edit', product1);
  }

  createProduct(product: Product): Observable<Product> {
    console.log(product);
    return this.http.post<Product>(this.URLPRODUCT + '/create', product);
  }

  findAllNew(): Observable<Product[]> {
    return this._httpClient.get<Product[]>(API_URL);
  }
  findAllCart(): Observable<Product[]> {
    return this._httpClient.get<Product[]>(API_URL1);


  }
}

