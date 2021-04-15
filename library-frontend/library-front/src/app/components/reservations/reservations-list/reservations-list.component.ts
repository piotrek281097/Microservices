import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ReservationService} from '../../../services/reservation.service';
import {ReservationDto} from '../../../models/reservationDto';
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
    this.reservationToUpdate = reservation;
    this.reservationToUpdate.reservationStatus = 'FINISHED';

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
      });
  }

  pageChangedReservations(event) {
    this.configReservationsPage.currentPage = event;
  }
}
