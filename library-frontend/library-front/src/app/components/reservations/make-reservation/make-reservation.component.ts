import { Component, OnInit } from '@angular/core';
import {ErrorStateMatcher} from '@angular/material/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ReaderService} from '../../../services/reader.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {ReservationService} from '../../../services/reservation.service';
import {Book} from '../../../models/book';
import {BookService} from '../../../services/book.service';
import {ReservationDto} from '../../../models/reservationDto';
import {formatDate} from '@angular/common';
import {UserService} from '../../../services/user.service';
import {MyErrorStateMatcher} from '../../../../util/my-error-state-matcher';

@Component({
  selector: 'app-make-reservation',
  templateUrl: './make-reservation.component.html',
  styleUrls: ['./make-reservation.component.css']
})
export class MakeReservationComponent implements OnInit {

  reservationForm: FormGroup;
  bookId: number;
  bookFromDatabase: Book;
  reservationToAdd: ReservationDto;
  bookToUpdate: Book;
  matcher = new MyErrorStateMatcher();

  FormControl = new FormControl('', [
    Validators.required,
  ]);
  monthsToEndReservationString: string;
  private startDate: string;
  private endDate: string;


  constructor(
    private formBuilder: FormBuilder,
    private reservationService: ReservationService,
    private bookService: BookService,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute,
    private toastrService: ToastrService
  ) {
    this.monthsToEndReservationString = 'Choose duration';
    this.startDate = formatDate(new Date(), 'yyyy-MM-dd', 'en');
    this.endDate = formatDate(new Date().setMonth(new Date().getMonth() + 1) , 'yyyy-MM-dd', 'en');
  }

  ngOnInit() {
    this.bookId = +this.route.snapshot.paramMap.get('bookId');

    this.bookService.findBookById(this.bookId).subscribe(data => {
      this.bookFromDatabase = data;
    });

  }


  onSubmit() {
    this.reservationToAdd = new ReservationDto();

    this.reservationToAdd.startDate = this.startDate;
    this.reservationToAdd.endDate = this.endDate;
    this.reservationToAdd.reservationStatus = 'ACTIVE';
    this.reservationToAdd.bookTitle = this.bookFromDatabase.title;
    this.reservationToAdd.bookIdentifier = this.bookFromDatabase.identifier;
    this.reservationToAdd.ownerUsername = this.bookFromDatabase.ownerUsername;
    this.reservationToAdd.borrowerUsername = this.userService.getUserDetails().sub;
    console.log(this.reservationToAdd);


    this.bookToUpdate = this.bookFromDatabase;
    this.bookToUpdate.bookStatus = 'RESERVED';

    this.updateBook();

  }

  private updateBook() {
    this.bookService.updateBook(this.bookToUpdate).toPromise()
      .then((res: Response) => {
          this.makeReservation();
        }
      )
      .catch((res: Response) => {
        this.toastrService.error('Error! Reservation not added');
      });
  }

  private makeReservation() {
    this.reservationService.save(this.reservationToAdd).toPromise()
      .then((res: Response) => {
          this.toastrService.success('Reservation added');
          setTimeout(() => {
            if (this.reservationToAdd.ownerUsername === 'admin') {
              this.router.navigate(['books/classic-library']);
            } else {
              this.router.navigate(['books/rental-service']);
            }
          }, 3000);
        }
      )
      .catch((res: Response) => {
        this.rollbackBookUpdate();
        this.toastrService.error('Error! Reservation not added');
      });

  }

  private rollbackBookUpdate() {
    this.bookToUpdate.bookStatus = 'AVAILABLE';
    this.bookService.updateBook(this.bookToUpdate).toPromise()
      .catch((res: Response) => {
        this.toastrService.error('Error! Reservation not added');
      });
  }

  cancel() {
    this.router.navigate(['/books/']);
  }

  setEndDate(numberMonths: number) {
    if (numberMonths === 1) {
      this.monthsToEndReservationString = '1 month';
    } else {
      this.monthsToEndReservationString = numberMonths + ' months';
    }
    this.endDate = formatDate(new Date().setMonth(new Date().getMonth() + numberMonths) , 'yyyy-MM-dd', 'en');
  }
}



