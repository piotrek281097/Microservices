import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-user-rental-menu',
  templateUrl: './user-rental-menu.component.html',
  styleUrls: ['./user-rental-menu.component.css']
})
export class UserRentalMenuComponent implements OnInit {
  role: string;

  constructor(public userService: UserService) { }

  ngOnInit() {
    this.role = localStorage.getItem('role');
  }
}
