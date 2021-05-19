import { Component, OnInit } from '@angular/core';
import {Book} from '../../../models/book';
import {BookService} from '../../../services/book.service';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-rental-service-books-list',
  templateUrl: './rental-service-books-list.component.html',
  styleUrls: ['./rental-service-books-list.component.css']
})
export class RentalServiceBooksListComponent implements OnInit {

  private libraryType: string;

  public dataSource = new MatTableDataSource<Book>();


  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.bookService.getUserRentalServiceBooks().subscribe(data => {
      this.libraryType = 'rental-service';
      if (data.length > 0) {
        this.dataSource.data = data;
      }
    });
  }

}
