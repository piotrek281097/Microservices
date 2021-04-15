import { Component, OnInit } from '@angular/core';
import {BookService} from '../../../services/book.service';
import {Book} from '../../../models/book';

@Component({
  selector: 'app-classic-library-books-list',
  templateUrl: './classic-library-books-list.component.html',
  styleUrls: ['./classic-library-books-list.component.css']
})
export class ClassicLibraryBooksListComponent implements OnInit {

  books: Book[];
  libraryType: string;

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.bookService.getClassicLibraryBooks().subscribe(data => {
      this.books = data;
      this.libraryType = 'classic-library';
    });
  }

}
