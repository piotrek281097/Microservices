import { Component, OnInit } from '@angular/core';
import {BookService} from '../../../services/book.service';
import {Book} from '../../../models/book';

@Component({
  selector: 'app-reviews-list',
  templateUrl: './reviews-list.component.html',
  styleUrls: ['./reviews-list.component.css']
})
export class ReviewsListComponent implements OnInit {

  book: Book;
  config: any;

  constructor(private bookService: BookService) {
    this.config = {
      itemsPerPage: 2,
      currentPage: 1,
      totalItems: 0,
    };
  }

  ngOnInit() {
    this.book = this.bookService.getBookContext();
    if (this.book !== undefined) {
      this.config.totalItems = this.book.opinions.length;
    }
  }

  pageChanged(event) {
    this.config.currentPage = event;
  }

}
