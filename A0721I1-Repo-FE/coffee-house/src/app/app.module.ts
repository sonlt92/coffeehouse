import {RouterModule} from '@angular/router';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginModuleModule} from './login-module/login-module.module';
import {ToastrModule} from 'ngx-toastr';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {JWT_OPTIONS, JwtHelperService, JwtModule} from '@auth0/angular-jwt';
import {HomeComponent} from './home/home.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FeedbackModuleModule} from './feedback-module/feedback-module.module';
import {OrderModuleRoutingModule} from './order-module/order-module-routing.module';
import {HeaderComponent} from './header/header.component';
import { HeaderUnloginComponent } from './header-unlogin/header-unlogin.component';
import {IncomeModuleModule} from './income-module/income-module.module';
import {DatePipe} from '@angular/common';




@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    HeaderUnloginComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    OrderModuleRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FeedbackModuleModule,
    FormsModule,
    FeedbackModuleModule,
    FormsModule ,
    LoginModuleModule,
    ToastrModule.forRoot({
      positionClass: 'toast-top-right',
    }),
    BrowserAnimationsModule,
    JwtModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    FeedbackModuleModule,
    IncomeModuleModule,
  ],
  providers: [
    {provide: JWT_OPTIONS, useValue: JWT_OPTIONS},
    JwtHelperService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
