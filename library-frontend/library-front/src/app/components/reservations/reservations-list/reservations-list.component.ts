import {Component, Input, OnInit} from '@angular/core';
import {Reader} from '../../../models/reader';
import {ActivatedRoute, Router} from '@angular/router';
import {ReaderService} from '../../../services/reader.service';
import {FineService} from '../../../services/fine.service';
import {ReservationService} from '../../../services/reservation.service';
import {Reservation} from '../../../models/reservation';
import {ReservationDto} from '../../../models/reservationDto';
import {Fine} from '../../../models/fine';
import {FineDto} from '../../../models/fineDto';
import {Book} from '../../../models/book';
import {UserService} from '../../../services/user.service';
import {ToastrService} from 'ngx-toastr';
import {BookService} from '../../../services/book.service';

@Component({
  selector: 'app-reservations-list',
  templateUrl: './reservations-list.component.html',
  styleUrls: ['./reservations-list.component.css']
})
export class ReservationsListComponent implements OnInit {

  @Input() reservations: ReservationDto[];

  configReservationsPage: any;
  role: string;
  username: string;

  reservationToUpdate: ReservationDto;
  bookToUpdate: Book;

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private userService: UserService,
    private fineService: FineService,
    private toastrService: ToastrService,
    private reservationService: ReservationService,
    private router: Router
  ) {
    this.configReservationsPage = {
      itemsPerPage: 5,
      currentPage: 1,
      totalItems: 0,
      id: 'pagination1'
    };
  }

  ngOnInit() {
    this.role = localStorage.getItem('role');
    this.username = this.userService.getUserDetails().sub;

    if (this.reservations !== undefined) {
      this.configReservationsPage.totalItems = this.reservations.length;
    }
  }

  returnBook(reservation: ReservationDto) {
    console.log("return reservation" + reservation);

    this.reservationToUpdate = reservation;
    this.reservationToUpdate.reservationStatus = 'FINISHED';

    this.bookService.changeBookStatus(this.reservationToUpdate.bookIdentifier).toPromise()
      .then((res: Response) => {
          this.updateReservation();
        }
      )
      .catch((res: Response) => {
        this.toastrService.error('Error! Book not returned');
      });
  }

  private updateReservation() {
    this.reservationService.updateReservation(this.reservationToUpdate).toPromise()
      .then((res: Response) => {
          this.toastrService.success('Book returned');
          setTimeout(() => {
            if (this.reservationToUpdate.ownerUsername === 'admin') {
              this.router.navigate(['reservations/classic-library']);
            } else {
              this.router.navigate(['reservations/rental-service']);
            }
          }, 3000);
        }
      )
      .catch((res: Response) => {
        this.toastrService.error('Error! Book not returned');
        this.rollbackBookUpdate();
      });

  }

  private rollbackBookUpdate() {
    this.bookToUpdate.bookStatus = 'AVAILABLE';
    this.bookService.changeBookStatus(this.reservationToUpdate.bookIdentifier).toPromise()
      .then((res: Response) => { })
      .catch((res: Response) => { });
  }

  // onSubmit() {
  //   console.log("fine make")
  //   this.router.navigate(['/add-fine/']);
  // }
  //
  // payForUser(fine: Fine) {
  //   console.log("pay fine" + fine);
  //   const fineToUpdate: FineDto = new FineDto();
  //
  //   fineToUpdate.id = fine.id;
  //   fineToUpdate.text = fine.text;
  //   fineToUpdate.money = fine.money;
  //   fineToUpdate.reader = this.readerService.getReaderFromContext();
  //   console.log(fineToUpdate);
  //
  //   this.fineService.update(fineToUpdate);
  // }

  pageChangedReservations(event) {
    this.configReservationsPage.currentPage = event;
  }

  private updateBook() {

  }
}
