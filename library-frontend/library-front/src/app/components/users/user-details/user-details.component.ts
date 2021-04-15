import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../services/user.service';
import {UserData} from '../../../models/user-data';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {


  username: string;
  userFromDatabase: UserData;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.username = this.route.snapshot.paramMap.get('username');

    this.userService.findByUsername(this.username).subscribe(data => {
      this.userFromDatabase = data;
    });
  }

  editUser(username: string) {
    this.router.navigate(['/edit-user/' + username]);
  }
}
