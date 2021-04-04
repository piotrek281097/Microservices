import { Component, OnInit } from '@angular/core';
import {Reader} from '../../models/reader';
import {ReaderService} from '../../services/reader.service';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';
import {UserData} from '../../models/user-data';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  users: UserData[];

  readerFullName: string;

  config: any;

  pageSize = 2;
  pageNumber = 0;

  constructor(private userService: UserService,
              private toastrService: ToastrService,
              private router: Router) {
    this.config = {
      itemsPerPage: 10,
      currentPage: 1,
      totalItems: 0,
    };
  }

  ngOnInit() {
    this.userService.getAllUsers().subscribe(data => {
      this.users = data;
      this.config.totalItems = data.length;
    });
  }

  deleteUser(userId: number) {
    // zabezpieczyc popuem
    this.userService.deleteUserById(userId).toPromise()
      .then((res: Response) => {
          this.toastrService.success('User deleted');
          setTimeout( () => {
            window.location.reload();
          }, 3000);
        }
      )
      .catch((res: Response) => {
        this.toastrService.error('Error! Unknown cause');
      });
  }

  goToDetails(username: string) {
    this.router.navigate(['/user-details/' + username]);
  }

  pageChanged(event) {
    this.config.currentPage = event;
  }

  // pageChanged(event) {
  //   this.config.currentPage = event;
  //   console.log('current page' + this.config.currentPage);
  //   this.readerService.getPageableReaders(this.config.currentPage - 1, this.pageSize).subscribe(data => {
  //     this.readers = data.readers;
  //     this.config.totalItems = data.totalPages * this.pageSize;
  //   });
  // }
}
