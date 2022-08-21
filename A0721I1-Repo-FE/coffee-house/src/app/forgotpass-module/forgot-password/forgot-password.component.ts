import {Component, Input, OnChanges, OnInit, SimpleChange, SimpleChanges} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ForgotPasswordService} from '../service/forgot-password.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  userEmail: string;
  check: any;
  token: string;
  forgotPasswordForm: FormGroup;

  constructor(private forgotPasswordService: ForgotPasswordService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.forgotPasswordForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email])
    });
  }

  submit() {
    this.userEmail = this.forgotPasswordForm.get('email').value;

    console.log('Email bạn vừa nhập: ' + this.userEmail);
    this.forgotPasswordService.processForgotPassword(this.userEmail).subscribe(result => {
        this.token = result;
        window.localStorage.setItem('reset_token', result);
        console.log('Truy cập thành công');
        this.check = true;
      },
      error => {
        this.check = false;
        console.log('Lỗi:');
        console.log(error);
      });
  }
}
