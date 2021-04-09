import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {BookService} from '../../services/book.service';
import {Book} from '../../models/book';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {UserService} from '../../services/user.service';
import {MatDialog} from '@angular/material';
import {RateBookComponent} from '../rate-book/rate-book.component';
import {Opinion} from '../../models/opinion';
import {HttpErrorResponse} from '@angular/common/http';

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
    // this.bookService.getPageableBooks(this.pageNumber, this.pageSize).subscribe(data => {
    //   this.books = data.books;
    //   this.config.totalItems = data.totalPages * this.pageSize;
    // });
    this.role = localStorage.getItem('role');
    this.username = this.userService.getUserDetails().sub;
    if (this.books !== undefined) {
      this.config.totalItems = this.books.length;
    }

    console.log("username " + this.username);
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

      //   if (result) {
    //     this.preparePlayerBoardToSend();
    //     const battleshipGameRequest = new BattleshipGameRequest(
    //       result.level, result.startPlayer, this.playerBoard, this.userService.getUserDetails().sub
    //     );
    //     this.battleshipsService.setBattleshipGameRequest(battleshipGameRequest);
    //     this.route.navigate(['/battleships-game']);
    //   }
    });
  }
}
