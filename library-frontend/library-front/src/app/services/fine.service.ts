import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {FineDto} from '../models/fineDto';

@Injectable({
  providedIn: 'root'
})
export class FineService {

  private finesUrl: string;
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
    this.finesUrl = 'http://localhost:8081/api/fines';
  }

  public save(fine: FineDto) {
    this.prepareHeader();

    return this.http.post(this.finesUrl + '/add', fine, {headers: this.headersObject}).toPromise()
      .then((res: Response) => {
          this.toastrService.success('Fine added');
          setTimeout(() => {
            this.router.navigate(['/home/']);
          }, 3000);
        }
      )
      .catch((res: Response) => {
        this.toastrService.success('Error! Fine not added');
      });
  }

  public update(fineDto: FineDto) {
    this.prepareHeader();

    return this.http.post(this.finesUrl + '/update', fineDto, {headers: this.headersObject}).toPromise()
      .then((res: Response) => {
          this.toastrService.success('Fine edited');
          setTimeout(() => {
            this.router.navigate(['/home/']);
          }, 3000);
        }
      )
      .catch((res: Response) => {
        this.toastrService.success('Error! Fine not edited');
      });
  }
}
