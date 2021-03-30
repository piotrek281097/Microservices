import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material';
import {Router} from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(public dialog: MatDialog,
              private router: Router) { }

  ngOnInit(): void {
  }

  playBattleships(): void {
    // this.router.navigate(['/battleships/']);
  }

  chooseGame(): void {
    // const dialogRef = this.dialog.open(NewGameComponent, {
    //   data: 'Reversi'
    // });
    //
    // dialogRef.afterClosed().subscribe((result: GameRequest) => {
    //   if (result) {
    //     this.router.navigate(['/reversi/' + result.level + '/' + result.playerColor]);
    //   }
    // });
  }

}
