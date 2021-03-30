import {Component, OnInit, ViewChild} from '@angular/core';
import {BookService} from '../../services/book.service';
import {Book} from '../../models/book';
import {Router} from '@angular/router';

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})
export class BooksListComponent implements OnInit {

  books: Book[];

  config: any;

  pageSize = 2;
  pageNumber = 0;

  searchText: any = { title: '', author: '', identifier: '', bookKind: ''};

  constructor(private bookService: BookService,
              private router: Router) {
    this.config = {
      itemsPerPage: 2,
      currentPage: 1,
      totalItems: 0,
    };
  }

  ngOnInit() {
    this.bookService.getPageableBooks(this.pageNumber, this.pageSize).subscribe(data => {
      this.books = data.books;
      this.config.totalItems = data.totalPages * this.pageSize;
    });
  }

  onClickField(bookId: number) {
    console.log("bookId" + bookId);
  }

  reserveBook(bookId: number) {
    console.log("reserve " + bookId);
    this.router.navigate(['/make-reservation/' + bookId]);
  }

  deleteBook(id: number) {
    console.log("delete " + id);
  }

  pageChanged(event) {
    this.config.currentPage = event;
    console.log('current page' + this.config.currentPage);
    this.bookService.getPageableBooks(this.config.currentPage - 1, this.pageSize).subscribe(data => {
      this.books = data.books;
      this.config.totalItems = data.totalPages * this.pageSize;
    });
  }
}
