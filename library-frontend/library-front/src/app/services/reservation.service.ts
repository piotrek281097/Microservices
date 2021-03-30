import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {Observable} from 'rxjs';
import {Book} from '../models/book';
import {PageableBookResponse} from '../models/pageableBookResponse';
import {ReservationDto} from '../models/reservationDto';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private reservationsUrl: string;
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
    this.reservationsUrl = 'http://localhost:8081/api/reservations';
  }

  public getAllReservations(): Observable<Book[]> {
    this.prepareHeader();
    return this.http.get<Book[]>(this.reservationsUrl, {headers: this.headersObject});
  }

  public getPageableReservations(pageNumber: number, pageSize: number): Observable<PageableBookResponse> {
    this.prepareHeader();

    console.log("PAgable " + pageNumber + " size " + pageSize);

    // Initialize Params Object
    let params = new HttpParams();

    // Begin assigning parameters
    params = params.append('page', pageNumber.toString());
    params = params.append('size', pageSize.toString());

    const options = {params: params, headers: this.headersObject};

    return this.http.get<PageableBookResponse>(this.reservationsUrl + '/pageable', options);
  }

  public save(reservationDto: ReservationDto) {
    this.prepareHeader();

    return this.http.post(this.reservationsUrl + '/add', reservationDto, {headers: this.headersObject}).toPromise()
      .then((res: Response) => {
          this.toastrService.success('Reservation added');
          setTimeout(() => {
            this.router.navigate(['/books/']);
          }, 3000);
        }
      )
      .catch((res: Response) => {
        this.toastrService.success('Error! Reservation not added');
      });
  }

  public update(reservationDto: ReservationDto) {
    this.prepareHeader();

    return this.http.post(this.reservationsUrl + '/update', reservationDto, {headers: this.headersObject}).toPromise()
      .then((res: Response) => {
          this.toastrService.success('Reservation edited');
          setTimeout(() => {
            this.router.navigate(['/home/']);
          }, 3000);
        }
      )
      .catch((res: Response) => {
        this.toastrService.success('Error! Reservation not edited');
      });
  }
}
