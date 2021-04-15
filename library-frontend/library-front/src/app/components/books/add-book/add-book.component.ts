import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import {BookService} from '../../../services/book.service';
import {Book} from '../../../models/book';
import {UserService} from '../../../services/user.service';
import {HttpErrorResponse} from '@angular/common/http';
import {MyErrorStateMatcher} from '../../../../util/my-error-state-matcher';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  bookForm: FormGroup;
  bookKind: string;

  matcher = new MyErrorStateMatcher();

  FormControl = new FormControl('', [
    Validators.required,
  ]);

  constructor(
    private formBuilder: FormBuilder,
    private bookService: BookService,
    private router: Router,
    private toastrService: ToastrService,
    private userService: UserService
  ) {
    this.bookKind = 'DRAMA';
  }

  ngOnInit() {
    this.bookForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]],
      author: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30), Validators.pattern('^[A-Za-z ]*$') ]],
      identifier: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern('^[A-Za-z0-9]*$')]],
      releaseDate: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50),
        Validators.pattern('^\\d{4}[/-]\\d{2}[/-]\\d{2}$')]]
    });
  }


  onSubmit() {
    const bookToAdd: Book = new Book();

    bookToAdd.title = this.bookForm.value.title;
    bookToAdd.author = this.bookForm.value.author;
    bookToAdd.identifier = this.bookForm.value.identifier;
    bookToAdd.bookKind = this.bookKind;
    bookToAdd.releaseDate = this.bookForm.value.releaseDate;
    bookToAdd.bookStatus = 'AVAILABLE';
    bookToAdd.ownerUsername = this.userService.getUserDetails().sub;
    bookToAdd.avgRate = 0;
    bookToAdd.opinions = [];

    if (!this.bookForm.invalid) {
      this.bookService.save(bookToAdd).toPromise()
        .then((res: Response) => {
            this.toastrService.success('Book added');
            setTimeout( () => {
              if (this.userService.getUserDetails().sub === 'admin') {
                this.router.navigate(['books/classic-library']);
              } else {
                this.router.navigate(['books/rental-service']);
              }
            }, 3000);
          }
        )
        .catch(error => {
          // do poprawy, moze error ze bookIdentifier istnieje
          if (error instanceof HttpErrorResponse && (error.status === 409 || error.status === 400)) {
            if (error.status === 409) {
              this.toastrService.error('Error! Book with this identifier already exists!');
            } else if (error.status === 400) {
              this.toastrService.error('Error! Unknown cause. Try again data.');
            }
          }
        });
    } else {
      this.toastrService.error('Error! Incorrect data.');
    }
  }

  cancel() {
    if (this.userService.getUserDetails().sub === 'admin') {
      this.router.navigate(['books/classic-library']);
    } else {
      this.router.navigate(['books/rental-service']);
    }
  }

  addBookKind(bookKind: string) {
    this.bookKind = bookKind;
  }
}

