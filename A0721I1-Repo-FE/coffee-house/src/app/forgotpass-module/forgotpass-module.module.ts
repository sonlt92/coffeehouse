import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ForgotpassModuleRoutingModule } from './forgotpass-module-routing.module';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { SuccessNotificationComponent } from './success-notification/success-notification.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';


@NgModule({
  declarations: [
    ForgotPasswordComponent,
    ResetPasswordComponent,
    SuccessNotificationComponent
  ],
  imports: [
    CommonModule,
    ForgotpassModuleRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class ForgotpassModuleModule { }
