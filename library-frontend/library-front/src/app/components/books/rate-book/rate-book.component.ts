import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {UserService} from '../../../services/user.service';
import {Book} from '../../../models/book';
import {Opinion} from '../../../models/opinion';

@Component({
  selector: 'app-rate-book',
  templateUrl: './rate-book.component.html',
  styleUrls: ['./rate-book.component.css']
})
export class RateBookComponent implements OnInit {

  currentRate = 5;
  opinion: Opinion = new Opinion();

  constructor(public dialogRef: MatDialogRef<RateBookComponent>) {
    this.opinion.grade = 5;
  }

  ngOnInit(): void {
  }

  cancel(): void {
    this.dialogRef.close();
  }

}
