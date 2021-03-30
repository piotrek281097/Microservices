import { Component, OnInit } from '@angular/core';
import {ErrorStateMatcher} from '@angular/material/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {FineService} from '../../services/fine.service';
import {ReaderService} from '../../services/reader.service';
import {FineDto} from '../../models/fineDto';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-add-fine',
  templateUrl: './add-fine.component.html',
  styleUrls: ['./add-fine.component.css']
})
export class AddFineComponent implements OnInit {

  fineForm: FormGroup;
  accountNumber: string;
  submitted = false;
  bookKind: string;
  books: Account[];

  matcher = new MyErrorStateMatcher();

  FormControl = new FormControl('', [
    Validators.required,
  ]);

  constructor(
    private formBuilder: FormBuilder,
    private fineService: FineService,
    private readerService: ReaderService,
    private router: Router,
    private toastrService: ToastrService
  ) {
  }

  ngOnInit() {
    this.fineForm = this.formBuilder.group({
      text: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30)]],
      money: ['', [Validators.required, Validators.pattern('^[0-9]*([.][0-9]{1,2})?$') ]],
    });
  }


  onSubmit() {
    const fineToAdd: FineDto = new FineDto();

    fineToAdd.text = this.fineForm.value.text;
    fineToAdd.money = this.fineForm.value.money;
    fineToAdd.reader = this.readerService.getReaderFromContext();

    console.log(fineToAdd);

    if (!this.fineForm.invalid) {
      this.fineService.save(fineToAdd);
    } else {
      this.toastrService.error('BŁĄD! Nieprawidłowe dane. Nie można dodać takiego rachunku.');
    }
  }

  cancel() {
    this.router.navigate(['/home/']);
  }
}
