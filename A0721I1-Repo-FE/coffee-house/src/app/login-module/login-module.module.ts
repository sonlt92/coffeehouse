import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginModuleRoutingModule } from './login-module-routing.module';
import { LoginComponent } from './login/login.component';
import {RouterModule} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [
    LoginComponent,
  ],
  exports: [
    LoginComponent,
  ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        HttpClientModule,
        RouterModule,
        LoginModuleRoutingModule
    ]
})
export class LoginModuleModule { }
