import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ReservationDto} from '../models/reservationDto';
import {Api} from '../../util/api';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(
    private http: HttpClient) {
  }

  public save(reservationDto: ReservationDto): Observable<any> {
    return this.http.post(`${Api.RESERVATIONS_END_POINT}/add`, reservationDto);
  }

  getClassicLibraryReservations(): Observable<ReservationDto[]> {
    return this.http.get<ReservationDto[]>(`${Api.RESERVATIONS_END_POINT}/classic-library`);
  }

  getUserRentalServiceReservations(): Observable<ReservationDto[]> {
    return this.http.get<ReservationDto[]>(`${Api.RESERVATIONS_END_POINT}/rental-service`);
  }

  getClassicLibraryReservationsForUser(username: string): Observable<ReservationDto[]> {
    return this.http.get<ReservationDto[]>(`${Api.RESERVATIONS_END_POINT}/classic-library/` + username);
  }

  getUserRentalServiceReservationsForUser(username: string): Observable<ReservationDto[]> {
    return this.http.get<ReservationDto[]>(`${Api.RESERVATIONS_END_POINT}/rental-service/` + username);
  }

  updateReservation(reservation: ReservationDto): Observable<any> {
    return this.http.post(`${Api.RESERVATIONS_END_POINT}/update`, reservation);
  }
}
