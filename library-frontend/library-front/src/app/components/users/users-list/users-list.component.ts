import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../../services/user.service';
import {UserData} from '../../../models/user-data';
import {ToastrService} from 'ngx-toastr';
import {MatDialog} from '@angular/material';
import {ConfirmDeleteUserComponent} from '../../confirm-delete-user/confirm-delete-user.component';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  users: UserData[];

  config: any;

  pageSize = 2;
  pageNumber = 0;

  constructor(private userService: UserService,
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
    this.userService.getAllUsers().subscribe(data => {
      this.users = data;
      this.config.totalItems = data.length;
    });
  }

  deleteUser(user: UserData) {
    return this.dialog.open(ConfirmDeleteUserComponent, {
      panelClass: 'dialog-container',
      disableClose: true,
      position: { top: '10px' },
      data : {
        user
      }
    });
  }

  goToDetails(username: string) {
    this.router.navigate(['/user-details/' + username]);
  }

  pageChanged(event) {
    this.config.currentPage = event;
  }

}
