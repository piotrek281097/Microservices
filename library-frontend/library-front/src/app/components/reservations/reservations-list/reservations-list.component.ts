import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ReservationService} from '../../../services/reservation.service';
import {ReservationDto} from '../../../models/reservationDto';
import {Book} from '../../../models/book';
import {UserService} from '../../../services/user.service';
import {ToastrService} from 'ngx-toastr';
import {BookService} from '../../../services/book.service';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-reservations-list',
  templateUrl: './reservations-list.component.html',
  styleUrls: ['./reservations-list.component.css']
})
export class ReservationsListComponent implements OnInit {

  @Input() dataSource: MatTableDataSource<ReservationDto>;
  configReservationsPage: any;
  role: string;
  username: string;

  displayedColumns: string[] = [
    'No',
    'Identifier',
    'BookTitle',
    'StartDate',
    'EndDate',
    'From',
    'To',
    'Status',
    'Return',
  ];

  reservationToUpdate: ReservationDto;
  bookToUpdate: Book;
  rowNumberStart = 1;


  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private userService: UserService,
    private toastrService: ToastrService,
    private reservationService: ReservationService,
    private router: Router
  ) {
    this.configReservationsPage = {
      itemsPerPage: 10,
      currentPage: 1,
      totalItems: 0,
      id: 'pagination1'
    };
  }

  ngOnInit() {
    this.role = localStorage.getItem('role');
    this.username = this.userService.getUserDetails().sub;
    this.rowNumberStart = 1;

    if (this.dataSource.data !== undefined) {
      this.configReservationsPage.totalItems = this.dataSource.data.length;
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
    this.configReservationsPage.currentPage = event.pageIndex;
    this.configReservationsPage.itemsPerPage = event.pageSize;
  }
}
