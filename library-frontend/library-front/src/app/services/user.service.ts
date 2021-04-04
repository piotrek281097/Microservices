import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {Reader} from '../models/reader';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {Observable} from 'rxjs';
import {PageableReaderResponse} from '../models/pageableReaderResponse';
import {UserData} from '../models/user-data';
import {LoginData} from '../models/login-data';
import {Api} from '../../util/api';
import jwtDecode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) {
  }

  register(user: UserData): Observable<any> {
    return this.http.post(`${Api.USER_END_POINT}/register`, user);
  }

  login(user: UserData): Observable<any> {
    return this.http.post(`${Api.USER_END_POINT}/login`, user);
  }

  getAllUsers(): Observable<UserData[]> {
    return this.http.get<UserData[]>(`${Api.USER_END_POINT}/users`);
  }

  deleteUserById(userId: number): Observable<any> {
    return this.http.delete(`${Api.USER_END_POINT}/users/delete/` + userId);
  }

  findByUserId(userId: number): Observable<UserData> {
    console.log("userId find " + this.getUserDetails().sub);
    return this.http.get<UserData>(`${Api.USER_END_POINT}/users/` + userId);
  }

  findByUsername(username: string): Observable<UserData> {
    return this.http.get<UserData>(`${Api.USER_END_POINT}/users/` + username);
  }

  updateUser(userToUpdate: UserData) {
    return this.http.post(`${Api.USER_END_POINT}/users/update`, userToUpdate);
  }

  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  saveRole(role: string): void {
    localStorage.setItem('role', role);
  }

  getRole(): string {
    return localStorage.getItem('role');
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
  }

  isLogged(): boolean {
    return localStorage.getItem('token') !==  null;
  }

  getUserDetails(): any {
    return jwtDecode(localStorage.getItem('token'));
  }

  // private headersObject: HttpHeaders;
  //
  // prepareHeader() {
  //   this.headersObject = new HttpHeaders();
  //   this.headersObject = this.headersObject.append('Content-Type', 'application/json');
  //   this.headersObject = this.headersObject.append('Authorization', 'Basic ' + btoa('admin1:password1'));
  // }
  //
  // constructor(
  //   private http: HttpClient,
  //   private router: Router,
  //   private toastrService: ToastrService) {
  // }
  //
  // public register(user: UserData) {
  //   this.prepareHeader();
  //
  //   return this.http.post(`${Api.USER_END_POINT}/register`, user, {headers: this.headersObject}).toPromise()
  //     .then((res: Response) => {
  //         this.toastrService.success('Registration completed');
  //         setTimeout( () => {
  //           this.router.navigate(['/home/']);
  //         }, 3000);
  //       }
  //     )
  //     .catch(error => {
  //       if (error instanceof HttpErrorResponse && (error.status === 404 || error.status === 409 || error.status === 400)) {
  //         if (error.status === 409) {
  //           this.toastrService.error('Error! User with this username already exists! Reader not added');
  //         } else if (error.status === 400) {
  //           this.toastrService.error('Error! Unknown cause. Try again. Registration not completed');
  //         }
  //       }
  //     })
  //     .catch((res: Response) => {
  //       this.toastrService.success('Error! Registration not completed');
  //     });
  // }
  //
  // public login(loginData: LoginData) {
  //   this.prepareHeader();
  //
  //   return this.http.post(`${Api.USER_END_POINT}/login`, loginData, {headers: this.headersObject}).toPromise()
  //     .then((res: Response) => {
  //         this.toastrService.success('You are logged');
  //         setTimeout( () => {
  //           this.router.navigate(['/home/']);
  //         }, 3000);
  //       }
  //     )
  //     .catch(error => {
  //       if (error instanceof HttpErrorResponse && (error.status === 404 || error.status === 409 || error.status === 400)) {
  //         if (error.status === 409) {
  //           this.toastrService.error('Error! User with this username already exists! Reader not added');
  //         } else if (error.status === 400) {
  //           this.toastrService.error('Error! Unknown cause. Try again. Login not successful');
  //         }
  //       }
  //     })
  //     .catch((res: Response) => {
  //       this.toastrService.success('Error! Unknown cause. Try again. Login not successful');
  //     });
  // }



}
