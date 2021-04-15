import {Component, Inject, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-confirm-delete-user',
  templateUrl: './confirm-delete-user.component.html',
  styleUrls: ['./confirm-delete-user.component.css']
})
export class ConfirmDeleteUserComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data,
    private dialogRef: MatDialogRef<ConfirmDeleteUserComponent>,
    private userService: UserService,
    private toastrService: ToastrService,
  ) { }

  ngOnInit() {
  }

  delete() {
    this.dialogRef.close();
    this.userService.deleteUserById(this.data.user.id).toPromise()
      .then((res: Response) => {
          this.toastrService.success('User deleted');
          setTimeout( () => {
            window.location.reload();
          }, 3000);
        }
      )
      .catch((res: Response) => {
        this.toastrService.error('Error! Unknown cause');
      });
  }

  cancel() {
    this.dialogRef.close();
  }

}
