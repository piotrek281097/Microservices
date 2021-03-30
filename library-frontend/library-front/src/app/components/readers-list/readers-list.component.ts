import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Reader} from '../../models/reader';
import {ReaderService} from '../../services/reader.service';

@Component({
  selector: 'app-readers-list',
  templateUrl: './readers-list.component.html',
  styleUrls: ['./readers-list.component.css']
})
export class ReadersListComponent implements OnInit {

  readers: Reader[];

  readerFullName: string;

  config: any;

  pageSize = 2;
  pageNumber = 0;

  constructor(private readerService: ReaderService,
              private router: Router) {
    this.config = {
      itemsPerPage: 2,
      currentPage: 1,
      totalItems: 0,
    };
  }

  ngOnInit() {
    this.readerService.getPageableReaders(this.pageNumber, this.pageSize).subscribe(data => {
      this.readers = data.readers;
      this.config.totalItems = data.totalPages * this.pageSize;
    });

    const readerInContext = this.readerService.getReaderFromContext();
    if (readerInContext != null) {
      this.readerFullName = readerInContext.name + ' ' + readerInContext.surname;
    }
  }

  editReader(readerId: number) {
    console.log("delete " + readerId);
    // this.router.navigate(['/edit-reader/' + readerId]);
  }

  goToDetails(readerId: number) {
    this.router.navigate(['/reader-details/' + readerId]);
  }

  pageChanged(event) {
    this.config.currentPage = event;
    console.log('current page' + this.config.currentPage);
    this.readerService.getPageableReaders(this.config.currentPage - 1, this.pageSize).subscribe(data => {
      this.readers = data.readers;
      this.config.totalItems = data.totalPages * this.pageSize;
    });
  }
}
