import { Component, OnInit } from '@angular/core';
import {Book} from '../../../models/book';
import {BookService} from '../../../services/book.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-ratings',
  templateUrl: './ratings.component.html',
  styleUrls: ['./ratings.component.css']
})
export class RatingsComponent implements OnInit {

  books: Book[];
  config: any;
  constructor(private bookService: BookService,
              private router: Router,
  ) {
    this.config = {
      itemsPerPage: 10,
      currentPage: 1,
      totalItems: 0,
    };
  }

  ngOnInit() {
    this.bookService.getBooksOrderedByAvgRate().subscribe(data => {
      this.books = data;
      this.config.totalItems = this.books.length;
    });
  }

  pageChanged(event) {
    this.config.currentPage = event;
  }

  showReviews(book: Book) {
    this.bookService.setBookContext(book);
    this.router.navigate(['/reviews-list']);
  }

  setOwnerName(book: Book): string {
    return book.ownerUsername !== 'admin' ? book.ownerUsername : 'Library';
  }
}
