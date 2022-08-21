import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrderModuleRoutingModule } from './order-module-routing.module';
import { OrderComponent } from './order/order.component';
import {HttpClientModule} from '@angular/common/http';
import { OrderDetailComponent } from './order-detail/order-detail.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [
    OrderComponent,
    OrderDetailComponent
  ],
    imports: [
        // HttpClientModule,
        CommonModule,
        OrderModuleRoutingModule,
        ReactiveFormsModule,
        FormsModule
    ]
})
export class OrderModuleModule { }
