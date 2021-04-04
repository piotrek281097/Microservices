import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {BookService} from '../../services/book.service';
import {Book} from '../../models/book';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})
export class BooksListComponent implements OnInit {

  @Input() books: Book[];

  config: any;

  pageSize = 2;
  pageNumber = 0;

  searchText: any = { title: '', author: '', identifier: '', bookKind: ''};

  constructor(private bookService: BookService,
              private toastrService: ToastrService,
              private router: Router) {
    this.config = {
      itemsPerPage: 10,
      currentPage: 1,
      totalItems: 0,
    };
  }

  ngOnInit() {
    // this.bookService.getPageableBooks(this.pageNumber, this.pageSize).subscribe(data => {
    //   this.books = data.books;
    //   this.config.totalItems = data.totalPages * this.pageSize;
    // });
    if (this.books !== undefined) {
      this.config.totalItems = this.books.length;
    }

  }

  onClickField(bookId: number) {
    console.log("bookId" + bookId);
  }

  reserveBook(bookId: number) {
    console.log("reserve " + bookId);
    this.router.navigate(['/make-reservation/' + bookId]);
  }

  deleteBook(id: number) {
    this.bookService.deleteBookById(id).toPromise()
      .then((res: Response) => {
          this.toastrService.success('Book deleted');
          setTimeout( () => {
            window.location.reload();
          }, 3000);
        }
      )
      .catch((res: Response) => {
        this.toastrService.error('Error! Unknown cause');
      });
  }

  pageChanged(event) {
    this.config.currentPage = event;
    console.log('current page' + this.config.currentPage);
    // this.bookService.getPageableBooks(this.config.currentPage - 1, this.pageSize).subscribe(data => {
    //   this.books = data.books;
    //   this.config.totalItems = data.totalPages * this.pageSize;
    // });
  }
}
