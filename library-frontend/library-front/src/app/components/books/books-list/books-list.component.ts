import {Component, Input, OnInit} from '@angular/core';
import {BookService} from '../../../services/book.service';
import {Book} from '../../../models/book';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {UserService} from '../../../services/user.service';
import {MatDialog} from '@angular/material';
import {RateBookComponent} from '../rate-book/rate-book.component';
import {Opinion} from '../../../models/opinion';
import {ConfirmDeleteBookComponent} from '../../confirm-delete-book/confirm-delete-book.component';

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})
export class BooksListComponent implements OnInit {

  @Input() books: Book[];
  @Input() libraryType: string;

  config: any;

  pageSize = 2;
  pageNumber = 0;

  searchText: any = { title: '', author: '', identifier: '', bookKind: ''};
  role: string;
  username: string;

  constructor(private bookService: BookService,
              private userService: UserService,
              private toastrService: ToastrService,
              private dialog: MatDialog,
              private router: Router) {
    this.config = {
      itemsPerPage: 10,
      currentPage: 1,
      totalItems: 0,
    };
  }

  ngOnInit() {
    this.role = localStorage.getItem('role');
    this.username = this.userService.getUserDetails().sub;
    if (this.books !== undefined) {
      this.config.totalItems = this.books.length;
    }
  }

  checkIfDeleteVisible(): boolean {
    return !(this.role === 'USER' && this.libraryType === 'classic-library')
      && !(this.role === 'ADMIN' && this.libraryType === 'rental-service');
  }

  checkIfReservePossible(book: Book): boolean {
    return book.bookStatus === 'RESERVED' || book.ownerUsername === this.username;
  }

  reserveBook(bookId: number) {
    this.router.navigate(['/make-reservation/' + bookId]);
  }

  deleteBook(book: Book) {
    return this.dialog.open(ConfirmDeleteBookComponent, {
      panelClass: 'dialog-container',
      disableClose: true,
      position: { top: '10px' },
      data : {
        book
      }
    });
  }

  pageChanged(event) {
    this.config.currentPage = event;
  }

  rateBook(book: Book): void {
    const dialogRef = this.dialog.open(RateBookComponent, {
      data: book,
    });

    dialogRef.afterClosed().subscribe((result: Opinion) => {
        result.book = book;
        this.bookService.addOpinion(result).toPromise()
          .then((res: Response) => {
              this.toastrService.success('Opinion added');
            }
          )
          .catch((res: Response) => {
            this.toastrService.error('Error! Opinion not added');
          });
    });
  }

  checkIfDeletePossible(book: Book) {
    return book.ownerUsername !== this.username || book.bookStatus !== 'AVAILABLE';
  }

  setOwnerName(book: Book): string {
    return book.ownerUsername !== 'admin' ? book.ownerUsername : 'Library';
  }
}
