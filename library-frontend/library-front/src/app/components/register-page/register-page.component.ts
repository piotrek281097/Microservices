import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {MyErrorStateMatcher} from '../add-reader/add-reader.component';
import {UserService} from '../../services/user.service';
import {UserData} from '../../models/user-data';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  registerForm: FormGroup;
  matcher = new MyErrorStateMatcher();

  FormControl = new FormControl('', [
    Validators.required,
  ]);

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router,
    private toastrService: ToastrService
  ) {
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30), Validators.pattern('^[A-Za-z0-9 ]*$')]],
      password: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]],
      email: ['', [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$')]],      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30), Validators.pattern('^[A-Za-z ]*$')]],
      surname: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30), Validators.pattern('^[A-Za-z ]*$') ]],
      telephone: ['', [Validators.required, Validators.minLength(9), Validators.maxLength(9), Validators.pattern('^[0-9]*$')]],
      pesel: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(11),
        Validators.pattern('^[0-9]*$')]],
    });
  }


  onSubmit() {
    const userDataToRegister: UserData = new UserData();

    console.log(this.registerForm.value.name);
    userDataToRegister.username = this.registerForm.value.username;
    userDataToRegister.password = this.registerForm.value.password;
    userDataToRegister.email = this.registerForm.value.email;
    userDataToRegister.name = this.registerForm.value.name;
    userDataToRegister.surname = this.registerForm.value.surname;
    userDataToRegister.telephone = this.registerForm.value.telephone;
    userDataToRegister.pesel = this.registerForm.value.pesel;
    console.log(userDataToRegister);

    if (!this.registerForm.invalid) {
      this.userService.register(userDataToRegister).subscribe((response) => {
          this.toastrService.success('Your account has been created!');
          this.router.navigate(['/login']);
        },
        error => {
          this.toastrService.error(error.statusText);
        }
      );
    }
  }

  cancel() {
    this.router.navigate(['/login/']);
  }
}



