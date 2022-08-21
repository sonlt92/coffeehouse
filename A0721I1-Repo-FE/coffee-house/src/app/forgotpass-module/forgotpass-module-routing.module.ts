import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ForgotPasswordComponent} from './forgot-password/forgot-password.component';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {SuccessNotificationComponent} from './success-notification/success-notification.component';


const routes: Routes = [
  {
    path: 'forgot-password-child',
    component: ForgotPasswordComponent
  } ,
  {
    path: 'reset-password/:token', component: ResetPasswordComponent
  },
  {
    path: 'success-notification', component: SuccessNotificationComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ForgotpassModuleRoutingModule { }
