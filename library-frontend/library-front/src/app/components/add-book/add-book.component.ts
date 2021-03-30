import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ErrorStateMatcher } from '@angular/material/core';
import { FormBuilder, NgForm, FormGroup, Validators, FormControl, FormGroupDirective } from '@angular/forms';
import {BookService} from '../../services/book.service';
import {Book} from '../../models/book';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
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
    private toastrService: ToastrService
  ) {
    this.bookKind = 'Drama';
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

    if (!this.bookForm.invalid) {
      this.bookService.save(bookToAdd);
    } else {
      this.toastrService.error('Error! Incorrect data.');
    }
  }

  cancel() {
    this.router.navigate(['/home/']);
  }

  addBookKind(bookKind: string) {
    this.bookKind = bookKind;
  }
}

