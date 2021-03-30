import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {Observable} from 'rxjs';
import {Reader} from '../models/reader';
import {PageableReaderResponse} from '../models/pageableReaderResponse';
import {Book} from '../models/book';
import {UserData} from '../models/user-data';

@Injectable({
  providedIn: 'root'
})
export class ReaderService {

  private readersUrl: string;
  private headersObject: HttpHeaders;

  private readerInContext: Reader;

  prepareHeader() {
    this.headersObject = new HttpHeaders();
    this.headersObject = this.headersObject.append('Content-Type', 'application/json');
    this.headersObject = this.headersObject.append('Authorization', 'Basic ' + btoa('admin1:password1'));
  }

  constructor(
    private http: HttpClient,
    private router: Router,
    private toastrService: ToastrService) {
    this.readersUrl = 'http://localhost:8081/api/readers';
  }


  public getReaderFromContext(): Reader {
    return this.readerInContext;
  }

  public setReaderInContext(reader: Reader) {
    this.readerInContext = reader;
  }

  public getPageableReaders(pageNumber: number, pageSize: number): Observable<PageableReaderResponse> {
    this.prepareHeader();
    let params = new HttpParams();

    params = params.append('page', pageNumber.toString());
    params = params.append('size', pageSize.toString());

    const options = { params: params, headers: this.headersObject };

    return this.http.get<PageableReaderResponse>(this.readersUrl + '/pageable', options);
  }

  public save(reader: Reader) {
    this.prepareHeader();

    return this.http.post(this.readersUrl + '/add', reader, {headers: this.headersObject}).toPromise()
      .then((res: Response) => {
          this.toastrService.success('Reader added');
          setTimeout( () => {
            this.router.navigate(['/home/']);
          }, 3000);
        }
      )
      .catch(error => {
        if (error instanceof HttpErrorResponse && (error.status === 404 || error.status === 409 || error.status === 400)) {
          if (error.status === 409) {
            this.toastrService.error('Error! Reader with this pesel already exists! Reader not added');
          } else if (error.status === 400) {
            this.toastrService.error('Error! Unknown cause. Try again. Reader not added');
          }
        }
      })
      .catch((res: Response) => {
        this.toastrService.success('Error! Reader not added');
      });
  }

  public findByReaderId(readerId: number): Observable<Reader> {
    this.prepareHeader();

    return this.http.get<Reader>(this.readersUrl + '/find-reader/' + readerId, {headers: this.headersObject});
  }

  public updateReader(reader: Reader) {
    this.prepareHeader();

    return this.http.post(this.readersUrl + '/update', reader, {headers: this.headersObject}).toPromise()
      .then((res: Response) => {
          this.toastrService.success('Reader edited');
          setTimeout( () => {
            this.router.navigate(['/home/']);
          }, 3000);
        }
      )
      .catch((res: Response) => {
        this.toastrService.success('Error! Unknown cause');
      });
  }
}
