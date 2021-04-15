import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserData} from '../models/user-data';
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

  findByUsername(username: string): Observable<UserData> {
    return this.http.get<UserData>(`${Api.USER_END_POINT}/users/` + username);
  }

  updateUser(userToUpdate: UserData): Observable<any> {
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

}
