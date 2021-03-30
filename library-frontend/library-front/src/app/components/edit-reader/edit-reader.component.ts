import { Component, OnInit } from '@angular/core';
import {ErrorStateMatcher} from '@angular/material/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
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
  selector: 'app-edit-reader',
  templateUrl: './edit-reader.component.html',
  styleUrls: ['./edit-reader.component.css']
})
export class EditReaderComponent implements OnInit {

  readerId: number;
  readerEditForm: FormGroup;
  name: string;
  surname: string;
  pesel: string;
  telephone: string;
  readerFromDatabase: Reader;
  isReaderFromDataBaseLoaded: boolean = false;

  matcher = new MyErrorStateMatcher();

  FormControl = new FormControl('', [
    Validators.required,
  ]);

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private readerService: ReaderService,
    private toastrService: ToastrService
  ) { }

  ngOnInit() {
    this.readerId = +this.route.snapshot.paramMap.get('readerId');

    this.readerService.findByReaderId(this.readerId).subscribe(data => {
      this.readerFromDatabase = data;
      this.isReaderFromDataBaseLoaded = true;
      this.pesel = this.readerFromDatabase.pesel;
    });

    this.readerEditForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30), Validators.pattern('^[A-Za-z ]*$')]],
      surname: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(30), Validators.pattern('^[A-Za-z ]*$') ]],
      telephone: ['', [Validators.required, Validators.minLength(9), Validators.maxLength(9), Validators.pattern('^[0-9]*$')]],
    });
  }

  onSubmit() {
    const readerToUpdate: Reader = new Reader();

    readerToUpdate.id = this.readerId;
    readerToUpdate.name = this.readerEditForm.value.name;
    readerToUpdate.surname = this.readerEditForm.value.surname;
    readerToUpdate.telephone = this.readerEditForm.value.telephone;
    readerToUpdate.pesel = this.pesel;

    console.log(readerToUpdate);

    if (!this.readerEditForm.invalid) {
      this.readerService.updateReader(readerToUpdate);
    } else {
      this.toastrService.error('Error! Incorrect data. Reader cannot be edited.');
    }
  }

  cancel() {
    this.router.navigate(['/home/']);
  }
}


