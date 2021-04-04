import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';
import {Reader} from '../../models/reader';
import {ActivatedRoute, Router} from '@angular/router';
import {ReaderService} from '../../services/reader.service';
import {ToastrService} from 'ngx-toastr';
import {MyErrorStateMatcher} from '../edit-reader/edit-reader.component';
import {UserData} from '../../models/user-data';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  username: string;
  userEditForm: FormGroup;
  name: string;
  surname: string;
  email: string;
  pesel: string;
  telephone: string;
  userFromDatabase: UserData;
  isUserFromDataBaseLoaded: boolean = false;

  matcher = new MyErrorStateMatcher();

  FormControl = new FormControl('', [
    Validators.required,
  ]);

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private toastrService: ToastrService
  ) { }

  ngOnInit() {
    this.username = this.route.snapshot.paramMap.get('username');

    this.userService.findByUsername(this.username).subscribe(data => {
      this.userFromDatabase = data;
      this.isUserFromDataBaseLoaded = true;
      this.pesel = this.userFromDatabase.pesel;
    });

    this.userEditForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30), Validators.pattern('^[A-Za-z ]*$')]],
      surname: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30), Validators.pattern('^[A-Za-z ]*$') ]],
      email: ['', [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$')]],
      telephone: ['', [Validators.required, Validators.minLength(9), Validators.maxLength(9), Validators.pattern('^[0-9]*$')]],
    });
  }

  onSubmit() {
    const userToUpdate: UserData = new UserData();

    userToUpdate.id = this.userFromDatabase.id;
    userToUpdate.username = this.username;
    userToUpdate.password = this.userFromDatabase.password;
    userToUpdate.name = this.userEditForm.value.name;
    userToUpdate.surname = this.userEditForm.value.surname;
    userToUpdate.email = this.userEditForm.value.email;
    userToUpdate.telephone = this.userEditForm.value.telephone;
    userToUpdate.pesel = this.pesel;

    console.log(userToUpdate);

    if (!this.userEditForm.invalid) {
      this.userService.updateUser(userToUpdate).toPromise()
        .then((res: Response) => {
            this.toastrService.success('User edited');
            setTimeout( () => {
              this.router.navigate(['/user-details/' + this.username]);
            }, 3000);
          }
        )
        .catch((res: Response) => {
          this.toastrService.error('Error! Unknown cause');
        });
    } else {
      this.toastrService.error('Error! Incorrect data. User cannot be edited.');
    }
  }

  cancel() {
    this.router.navigate(['/user-details/' + this.username]);
  }
}
