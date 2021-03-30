import { Component, OnInit } from '@angular/core';
import {ErrorStateMatcher} from '@angular/material/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {ReaderService} from '../../services/reader.service';
import {Reader} from '../../models/reader';



export class MyErrorStateMatcher implements ErrorStateMatcher {
isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
  const isSubmitted = form && form.submitted;
  return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
}

}
@Component({
  selector: 'app-add-reader',
  templateUrl: './add-reader.component.html',
  styleUrls: ['./add-reader.component.css']
})
export class AddReaderComponent implements OnInit {

  readerForm: FormGroup;
  matcher = new MyErrorStateMatcher();

  FormControl = new FormControl('', [
    Validators.required,
  ]);

  constructor(
    private formBuilder: FormBuilder,
    private readerService: ReaderService,
    private router: Router,
    private toastrService: ToastrService
  ) {
  }

  ngOnInit() {
    this.readerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30), Validators.pattern('^[A-Za-z ]*$')]],
      surname: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30), Validators.pattern('^[A-Za-z ]*$') ]],
      telephone: ['', [Validators.required, Validators.minLength(9), Validators.maxLength(9), Validators.pattern('^[0-9]*$')]],
      pesel: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(11),
        Validators.pattern('^[0-9]*$')]],
    });
  }


  onSubmit() {
    const readerToAdd: Reader = new Reader();

    console.log(this.readerForm.value.name);
    readerToAdd.name = this.readerForm.value.name;
    readerToAdd.surname = this.readerForm.value.surname;
    readerToAdd.telephone = this.readerForm.value.telephone;
    readerToAdd.pesel = this.readerForm.value.pesel;
    console.log(readerToAdd);

    if (!this.readerForm.invalid) {
      this.readerService.save(readerToAdd);
    } else {
      this.toastrService.error('Error! Incorrect data. Reader not added.');
    }
  }

  cancel() {
    this.router.navigate(['/home/']);
  }
}


