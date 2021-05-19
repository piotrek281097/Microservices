import {Component, Input, OnInit} from '@angular/core';
import {BookService} from '../../../services/book.service';
import {Book} from '../../../models/book';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {UserService} from '../../../services/user.service';
import {MatDialog, MatTableDataSource} from '@angular/material';
import {RateBookComponent} from '../rate-book/rate-book.component';
import {Opinion} from '../../../models/opinion';
import {ConfirmDeleteBookComponent} from '../../confirm-delete-book/confirm-delete-book.component';
import {UserData} from '../../../models/user-data';

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})
export class BooksListComponent implements OnInit {

  @Input() dataSource: MatTableDataSource<Book>;
  @Input() libraryType: string;

  displayedColumns: string[] = [
    'No',
    'Title',
    'Author',
    'Identifier',
    'BookKind',
    'ReleaseDate',
    'Owner',
    'Reserve',
    'Rate',
    'Delete'
  ];

  // public dataSource = new MatTableDataSource<Book>();

  config: any;

  pageSize = 2;
  pageNumber = 0;

  rowNumberStart = 1;

  searchText: any = { title: '', author: '', identifier: '', bookKind: ''};
  role: string;
  username: string;

  constructor(private bookService: BookService,
              private userService: UserService,
              private toastrService: ToastrService,
              private dialog: MatDialog,
              private router: Router) {
    // this.dataSource.data = this.books;

    this.config = {
      itemsPerPage: 10,
      currentPage: 1,
      totalItems: 0,
    };
  }

  ngOnInit() {
    this.role = localStorage.getItem('role');
    this.username = this.userService.getUserDetails().sub;
    this.rowNumberStart = 1;
    // this.dataSource.data = this.books;
    // console.log(this.books[0].title)

    if (this.dataSource.data !== undefined) {
      // console.log(this.books[0].title)
      // this.dataSource.data = this.books;
      this.config.totalItems = this.dataSource.data.length;
    }
  }

  checkIfDeleteVisible(): boolean {
    return !(this.role === 'ROLE_USER' && this.libraryType === 'classic-library')
      && !(this.role === 'ROLE_ADMIN' && this.libraryType === 'rental-service');
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
    this.config.currentPage = event.pageIndex;
    this.config.itemsPerPage = event.pageSize;
    this.pageSize = event.pageSize;
  }

  rateBook(book: Book): void {
    const dialogRef = this.dialog.open(RateBookComponent, {
      data: book,
    });

    dialogRef.afterClosed().subscribe((result: Opinion) => {
        result.book = book;
        console.log("book " + book.id)
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
