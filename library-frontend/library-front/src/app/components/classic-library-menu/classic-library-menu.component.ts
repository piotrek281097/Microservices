import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-classic-library-menu',
  templateUrl: './classic-library-menu.component.html',
  styleUrls: ['./classic-library-menu.component.css']
})
export class ClassicLibraryMenuComponent implements OnInit {

  role: string;

  constructor(public userService: UserService) { }

  ngOnInit() {
    this.role = localStorage.getItem('role');
  }
}
