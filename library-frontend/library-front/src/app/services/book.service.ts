import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Book} from '../models/book';
import {Api} from '../../util/api';
import {Opinion} from '../models/opinion';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private book: Book;

  constructor(
    private http: HttpClient) {
  }

  setBookContext(book: Book) {
    this.book = book;
  }

  getBookContext() {
    return this.book;
  }

  public save(book: Book): Observable<any> {
    return this.http.post(`${Api.BOOKS_END_POINT}/add`, book);
  }

  getBooksOrderedByAvgRate(): Observable<Book[]> {
    return this.http.get<Book[]>(`${Api.BOOKS_END_POINT}/ratings`);
  }

  getClassicLibraryBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${Api.BOOKS_END_POINT}/classic-library`);
  }

  getUserRentalServiceBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${Api.BOOKS_END_POINT}/rental-service`);
  }

  deleteBookById(bookId: number): Observable<any> {
    return this.http.delete(`${Api.BOOKS_END_POINT}/delete/` + bookId);
  }

  addOpinion(opinion: Opinion) {
    return this.http.post(`${Api.BOOKS_END_POINT}/add-opinion/`, opinion);

  }

  findBookById(bookId: number): Observable<Book> {
    return this.http.get<Book>(`${Api.BOOKS_END_POINT}/` + bookId);
  }

}
