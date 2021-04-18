import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {UserService} from '../../../services/user.service';
import {UserData} from '../../../models/user-data';
import {MyErrorStateMatcher} from '../../../../util/my-error-state-matcher';

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

    loginData.username = this.loginForm.value.username;
    loginData.password = this.loginForm.value.password;

    if (!this.loginForm.invalid) {
      console.log("przed");
      this.userService.login(loginData).subscribe((response) => {
        console.log("token " + response.access_token);
          console.log("roles " + response.roles[0]);

          this.userService.saveToken(response.access_token);
          this.userService.saveRole(response.roles[0]);
          this.router.navigate(['/classic-library-menu']);
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



