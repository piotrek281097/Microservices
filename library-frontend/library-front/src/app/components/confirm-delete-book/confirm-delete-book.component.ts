import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {UserService} from '../../services/user.service';
import {ToastrService} from 'ngx-toastr';
import {BookService} from '../../services/book.service';

@Component({
  selector: 'app-confirm-delete-book',
  templateUrl: './confirm-delete-book.component.html',
  styleUrls: ['./confirm-delete-book.component.css']
})
export class ConfirmDeleteBookComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data,
    private dialogRef: MatDialogRef<ConfirmDeleteBookComponent>,
    private bookService: BookService,
    private toastrService: ToastrService,
  ) { }

  ngOnInit() {
  }

  delete() {
    this.dialogRef.close();
    this.bookService.deleteBookById(this.data.book.id).toPromise()
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

  cancel() {
    this.dialogRef.close();
  }

}

