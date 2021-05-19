import { Component, OnInit } from '@angular/core';
import {BookService} from '../../../services/book.service';
import {Book} from '../../../models/book';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-classic-library-books-list',
  templateUrl: './classic-library-books-list.component.html',
  styleUrls: ['./classic-library-books-list.component.css']
})
export class ClassicLibraryBooksListComponent implements OnInit {

  libraryType: string;

  constructor(private bookService: BookService) { }

  public dataSource = new MatTableDataSource<Book>();


  ngOnInit() {
    this.bookService.getClassicLibraryBooks().subscribe(data => {
      this.libraryType = 'classic-library';
      if (data.length > 0) {
        this.dataSource.data = data;
      }
    });
  }

}
