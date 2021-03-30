import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-rental-menu',
  templateUrl: './user-rental-menu.component.html',
  styleUrls: ['./user-rental-menu.component.css']
})
export class UserRentalMenuComponent implements OnInit {
  role: string;

  constructor() { }

  ngOnInit() {
    this.role = localStorage.getItem('role');
    console.log("user rental role " + this.role);
  }

  chooseOption(option: number) {
    console.log("option " + option)
  }
}
