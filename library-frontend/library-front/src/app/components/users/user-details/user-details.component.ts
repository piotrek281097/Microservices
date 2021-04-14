import { Component, OnInit } from '@angular/core';
import {Reader} from '../../../models/reader';
import {ActivatedRoute, Router} from '@angular/router';
import {ReaderService} from '../../../services/reader.service';
import {FineService} from '../../../services/fine.service';
import {ReservationService} from '../../../services/reservation.service';
import {Reservation} from '../../../models/reservation';
import {ReservationDto} from '../../../models/reservationDto';
import {Fine} from '../../../models/fine';
import {FineDto} from '../../../models/fineDto';
import {UserService} from '../../../services/user.service';
import {UserData} from '../../../models/user-data';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {


  username: string;
  userFromDatabase: UserData;

  // configReservationsPage: any;
  // configFinesPage: any;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    // private fineService: FineService,
    // private reservationService: ReservationService,
    private router: Router
  ) {
    // this.configReservationsPage = {
    //   itemsPerPage: 5,
    //   currentPage: 1,
    //   totalItems: 0,
    //   id: 'pagination1'
    // };

    // this.configFinesPage = {
    //   itemsPerPage: 5,
    //   currentPage: 1,
    //   totalItems: 0,
    //   id: 'pagination2'
    // };
  }

  ngOnInit() {
    this.username = this.route.snapshot.paramMap.get('username');

    this.userService.findByUsername(this.username).subscribe(data => {
      this.userFromDatabase = data;

      // this.configReservationsPage.totalItems = this.userrFromDatabase.reservations.length;
      // this.configFinesPage.totalItems = this.userrFromDatabase.fines.length;
    });
  }

  // returnBook(reservation: Reservation) {
  //   console.log("return reservation" + reservation);
  //   const reservationToUpdate: ReservationDto = new ReservationDto();
  //
  //   reservationToUpdate.id = reservation.id;
  //   reservationToUpdate.startDate = reservation.startDate;
  //   reservationToUpdate.endDate = reservation.endDate;
  //   reservationToUpdate.reservationStatus = 'FINISHED';
  //   reservation.book.bookStatus = 'AVAILABLE';
  //   reservationToUpdate.book = reservation.book;
  //   reservationToUpdate.reader = this.userService.getReaderFromContext();
  //   console.log(reservationToUpdate);
  //
  //   this.reservationService.update(reservationToUpdate);
  // }

  onSubmit() {
    console.log("fine make")
    this.router.navigate(['/add-fine/']);
  }

  // payForUser(fine: Fine) {
  //   console.log("pay fine" + fine);
  //   const fineToUpdate: FineDto = new FineDto();
  //
  //   fineToUpdate.id = fine.id;
  //   fineToUpdate.text = fine.text;
  //   fineToUpdate.money = fine.money;
  //   fineToUpdate.reader = this.userService.getReaderFromContext();
  //   console.log(fineToUpdate);
  //
  //   this.fineService.update(fineToUpdate);
  // }

  // pageChangedReservations(event) {
  //   this.configReservationsPage.currentPage = event;
  // }
  //
  // pageChangedFines(event) {
  //   this.configFinesPage.currentPage = event;
  //
  // }
  editUser(username: string) {
    this.router.navigate(['/edit-user/' + username]);
  }
}
