import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  appTitle = 'BookOnDemand';

  constructor(
    public userService: UserService,
  ) { }


  ngOnInit(): void {
  }

  logout(): void {
    this.userService.logout();
  }
}
