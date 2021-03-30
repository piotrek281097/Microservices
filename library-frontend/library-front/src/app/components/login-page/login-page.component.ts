import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {MyErrorStateMatcher} from '../add-reader/add-reader.component';
import {UserService} from '../../services/user.service';
import {UserData} from '../../models/user-data';
import {LoginData} from '../../models/login-data';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  loginForm: FormGroup;
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
    localStorage.clear();
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30), Validators.pattern('^[A-Za-z0-9 ]*$')]],
      password: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]],
    });
  }


  onSubmit() {
    const loginData: UserData = new UserData();

    console.log(this.loginForm.value.name);
    loginData.username = this.loginForm.value.username;
    loginData.password = this.loginForm.value.password;
    console.log(loginData);

    if (!this.loginForm.invalid) {
      this.userService.login(loginData).subscribe((response) => {
          console.log(response.access_token);
          console.log("ROLES" + response.roles[0]);

          this.userService.saveToken(response.access_token);
          this.userService.saveRole(response.roles[0]);
          this.router.navigate(['/menu']);
          this.toastrService.success('Login successful');
        },
        error => {
          this.toastrService.error('Wrong data passed. Check your password');
        }
      );
    }
  }

  cancel() {
    this.router.navigate(['/register/']);
  }
}



