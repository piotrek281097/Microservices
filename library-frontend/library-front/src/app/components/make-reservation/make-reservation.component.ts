import { Component, OnInit } from '@angular/core';
import {ErrorStateMatcher} from '@angular/material/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ReaderService} from '../../services/reader.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {ReservationService} from '../../services/reservation.service';
import {Book} from '../../models/book';
import {BookService} from '../../services/book.service';
import {ReservationDto} from '../../models/reservationDto';


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-make-reservation',
  templateUrl: './make-reservation.component.html',
  styleUrls: ['./make-reservation.component.css']
})
export class MakeReservationComponent implements OnInit {

  reservationForm: FormGroup;
  bookId: number;
  bookFromDatabase: Book;

  matcher = new MyErrorStateMatcher();

  FormControl = new FormControl('', [
    Validators.required,
  ]);

  constructor(
    private formBuilder: FormBuilder,
    private reservationService: ReservationService,
    private bookService: BookService,
    private readerService: ReaderService,
    private router: Router,
    private route: ActivatedRoute,
    private toastrService: ToastrService
  ) {
  }

  ngOnInit() {
    this.bookId = +this.route.snapshot.paramMap.get('bookId');

    this.bookService.findByBookId(+this.bookId).subscribe(data => {
      this.bookFromDatabase = data;
    });

    this.reservationForm = this.formBuilder.group({
      startDate: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50),
        Validators.pattern('^\\d{4}[/-]\\d{2}[/-]\\d{2}$')]],
      endDate: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50),
        Validators.pattern('^\\d{4}[/-]\\d{2}[/-]\\d{2}$')]]
    });
  }


  onSubmit() {
    const reservationToAdd: ReservationDto = new ReservationDto();

    reservationToAdd.startDate = this.reservationForm.value.startDate;
    reservationToAdd.endDate = this.reservationForm.value.endDate;
    reservationToAdd.reservationStatus = 'ACTIVE';
    this.bookFromDatabase.bookStatus = 'RESERVED';
    reservationToAdd.book = this.bookFromDatabase;
    reservationToAdd.reader = this.readerService.getReaderFromContext();
    console.log(reservationToAdd);

    if (!this.reservationForm.invalid && this.readerService.getReaderFromContext() != null) {
      this.reservationService.save(reservationToAdd);
    } else {
      this.toastrService.error('Error! Incorrect data. Reader not added.');
    }
  }

  cancel() {
    this.router.navigate(['/books/']);
  }
}



