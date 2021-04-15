import { Component, OnInit } from '@angular/core';
import {Book} from '../../../models/book';
import {BookService} from '../../../services/book.service';

@Component({
  selector: 'app-rental-service-books-list',
  templateUrl: './rental-service-books-list.component.html',
  styleUrls: ['./rental-service-books-list.component.css']
})
export class RentalServiceBooksListComponent implements OnInit {

  private books: Book[];
  private libraryType: string;

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.bookService.getUserRentalServiceBooks().subscribe(data => {
      this.books = data;
      this.libraryType = 'rental-service';
    });
  }

}
