import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {ForgotPasswordService} from '../service/forgot-password.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  token: string;

  password: string;
  confirmPassword: string;
  resetPasswordForm: FormGroup;

  constructor(private http: HttpClient,
              private forgotPasswordService: ForgotPasswordService,
              private route: Router) {
  }

  ngOnInit(): void {
    this.resetPasswordForm = new FormGroup({
      passwordGroup: new FormGroup({
        password: new FormControl('', [Validators.required, Validators.minLength(6)]),
        cfPassword: new FormControl('', [Validators.required])
      }, {validators: this.comparePassword})
    });
  }

  onSubmit() {
    this.token = window.localStorage.getItem('reset_token');
    this.password = this.resetPasswordForm.get(['passwordGroup', 'password']).value;
    this.forgotPasswordService.processResetPassword(this.token, this.password).subscribe(
      message => {
        console.log(message);
        window.localStorage.clear();
        this.route.navigateByUrl('/forgot-password/success-notification');
      }, error => {
        console.log(error);
        console.log('Lỗi không thể đổi mật khẩu thành công');
      }
    );
  }

  comparePassword(c: AbstractControl): { password_not_match: boolean } {
    if (c.get('password').value !== c.get('cfPassword').value) {
      return {password_not_match: true};
    }
  }
}




