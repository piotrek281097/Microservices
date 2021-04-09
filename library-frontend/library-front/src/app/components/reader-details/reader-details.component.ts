import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Reader} from '../../models/reader';
import {ReaderService} from '../../services/reader.service';
import {ReservationService} from '../../services/reservation.service';
import {ReservationDto} from '../../models/reservationDto';
import {Reservation} from '../../models/reservation';
import {Fine} from '../../models/fine';
import {FineDto} from '../../models/fineDto';
import {FineService} from '../../services/fine.service';

@Component({
  selector: 'app-reader-details',
  templateUrl: './reader-details.component.html',
  styleUrls: ['./reader-details.component.css']
})
export class ReaderDetailsComponent implements OnInit {

  readerId: number;
  readerFromDatabase: Reader;

  configReservationsPage: any;
  configFinesPage: any;

  constructor(
    private route: ActivatedRoute,
    private readerService: ReaderService,
    private fineService: FineService,
    private reservationService: ReservationService,
    private router: Router
  ) {
    this.configReservationsPage = {
      itemsPerPage: 5,
      currentPage: 1,
      totalItems: 0,
      id: 'pagination1'
    };

    this.configFinesPage = {
      itemsPerPage: 5,
      currentPage: 1,
      totalItems: 0,
      id: 'pagination2'
    };
  }

  ngOnInit() {
    this.readerId = +this.route.snapshot.paramMap.get('readerId');

    this.readerService.findByReaderId(this.readerId).subscribe(data => {
      this.readerFromDatabase = data;
      this.readerService.setReaderInContext(this.readerFromDatabase);

      this.configReservationsPage.totalItems = this.readerFromDatabase.reservations.length;
      this.configFinesPage.totalItems = this.readerFromDatabase.fines.length;

      console.log("res " + this.configReservationsPage.totalItems)
      console.log("fine " + this.configFinesPage.totalItems)

    });
  }

  returnBook(reservation: Reservation) {
    console.log("return reservation" + reservation);
    const reservationToUpdate: ReservationDto = new ReservationDto();

    reservationToUpdate.id = reservation.id;
    reservationToUpdate.startDate = reservation.startDate;
    reservationToUpdate.endDate = reservation.endDate;
    reservationToUpdate.reservationStatus = 'FINISHED';
    reservation.book.bookStatus = 'AVAILABLE';
    // reservationToUpdate.book = reservation.book;
    // reservationToUpdate.reader = this.readerService.getReaderFromContext();
    console.log(reservationToUpdate);

    this.reservationService.update(reservationToUpdate);
  }

  onSubmit() {
    console.log("fine make")
    this.router.navigate(['/add-fine/']);
  }

  payForUser(fine: Fine) {
    console.log("pay fine" + fine);
    const fineToUpdate: FineDto = new FineDto();

    fineToUpdate.id = fine.id;
    fineToUpdate.text = fine.text;
    fineToUpdate.money = fine.money;
    fineToUpdate.reader = this.readerService.getReaderFromContext();
    console.log(fineToUpdate);

    this.fineService.update(fineToUpdate);
  }

  pageChangedReservations(event) {
    this.configReservationsPage.currentPage = event;
  }

  pageChangedFines(event) {
    this.configFinesPage.currentPage = event;

  }
}
