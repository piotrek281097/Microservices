import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {Observable, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import {Book} from '../models/book';
import {PageableBookResponse} from '../models/pageableBookResponse';
import {UserData} from '../models/user-data';
import {Api} from '../../util/api';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private booksUrl: string;
  private headersObject: HttpHeaders;

  prepareHeader() {
    this.headersObject = new HttpHeaders();
    this.headersObject = this.headersObject.append('Content-Type', 'application/json');
    this.headersObject = this.headersObject.append('Authorization', 'Basic ' + btoa('admin1:password1'));
  }

  constructor(
    private http: HttpClient,
    private router: Router,
    private toastrService: ToastrService) {
    this.booksUrl = 'http://localhost:8081/api/books';
  }

  public getAllBooks(): Observable<Book[]> {
    this.prepareHeader();
    return this.http.get<Book[]>(this.booksUrl, {headers: this.headersObject});
  }

  public getPageableBooks(pageNumber: number, pageSize: number): Observable<PageableBookResponse> {
    this.prepareHeader();

    console.log("PAgable " + pageNumber + " size " + pageSize);

    // Initialize Params Object
    let params = new HttpParams();

    // Begin assigning parameters
    params = params.append('page', pageNumber.toString());
    params = params.append('size', pageSize.toString());

    const options = { params: params, headers: this.headersObject };

    return this.http.get<PageableBookResponse>(this.booksUrl + '/pageable', options);
  }

  public save(book: Book): Observable<any> {
    return this.http.post(`${Api.BOOKS_END_POINT}/add`, book);
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

  // public save(book: Book) {
  //   this.prepareHeader();
  //
  //   return this.http.post(this.booksUrl + '/add', book, {headers: this.headersObject}).toPromise()
  //     .then((res: Response) => {
  //         this.toastrService.success('Book added');
  //         setTimeout( () => {
  //           this.router.navigate(['/books/']);
  //         }, 3000);
  //       }
  //     )
  //     .catch(error => {
  //       // do poprawy, moze error ze bookIdentifier istnieje
  //       if (error instanceof HttpErrorResponse && (error.status === 409 || error.status === 400)) {
  //         if (error.status === 409) {
  //           this.toastrService.error('Error! Book with this identifier already exists! Error! Book not added');
  //         } else if (error.status === 400) {
  //           this.toastrService.error('Error! Unknown cause. Try again data. Error! Book not added');
  //         }
  //       }
  //     })
  //     .catch((res: Response) => {
  //       this.toastrService.success('Error! Book not added');
  //     });
  // }

  public delete(bookId: number) {
    this.prepareHeader();

    this.http.put(this.booksUrl + '/delete/' + bookId, {headers: this.headersObject}).subscribe(a => a);
  }

  public findByBookId(bookId: number): Observable<Book> {
    this.prepareHeader();
    return this.http.get<Book>(this.booksUrl + '/find-book/' + bookId, {headers: this.headersObject});
  }

  public updateBook(bookId: number, book: Book) {
    this.prepareHeader();

    return this.http.put(this.booksUrl + '/update/' + bookId, book, {headers: this.headersObject}).toPromise()
      .then((res: Response) => {
          this.toastrService.success('Book edited');
          setTimeout( () => {
            this.router.navigate(['/books/']);
          }, 3000);
        }
      )
      .catch((res: Response) => {
        this.toastrService.success('Error! Book not edited');
      });
  }

  public findBookByAuthor(author: string): Observable<Book[]> {
    this.prepareHeader();

    return this.http.get<Book[]>(this.booksUrl + '/findByOwnerName/' + author, {headers: this.headersObject})
      .pipe(
        catchError(err => {
          if (err.status === 404) {
            this.toastrService.error('No books for this author');
            return throwError(err);
          }
        })
      );
  }
}
