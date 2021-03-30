import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-classic-library-menu',
  templateUrl: './classic-library-menu.component.html',
  styleUrls: ['./classic-library-menu.component.css']
})
export class ClassicLibraryMenuComponent implements OnInit {

  role: string;

  constructor() { }

  ngOnInit() {
    this.role = localStorage.getItem('role');
    console.log("classic role " + this.role);
  }

  chooseOption(option: number) {
    console.log("option " + option)
  }
}
