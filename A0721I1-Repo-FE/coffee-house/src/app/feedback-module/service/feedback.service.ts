import { Injectable } from '@angular/core';

import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {Feedback} from '../../model/feedback';
const API_URL = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  constructor(private http: HttpClient ) { }

  getAll(index: number): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(API_URL + '/manager/api/feedback?index=' + index);
  }

  getAllNotPagination(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(API_URL + '/manager/api/feedback-not-pagination/');
  }

  findById(id: number): Observable<Feedback> {
    return this.http.get<Feedback>(`${API_URL}/manager/api/feedback/view?id=` +id);
  }

  getFeedbackByDate(date: String, index: number): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${API_URL}/manager/api/feedback/search?date=` + date + '&index=' + index);
  }

  getFeedbackByDateNotPagination(date: String): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${API_URL}/manager/api/feedback/search-not-pagination?date=` + date);
  }

  saveFeedback(feedBack) {
    return this.http.post<Feedback>(API_URL + "/api/feedback/createFeedback", feedBack);

  }
}
