import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuModuleRoutingModule } from './menu-module-routing.module';
import {FormatTimePipe, MenuOrderComponent} from './menu-order/create-menu-order.component';
import {HttpClient} from '@angular/common/http';
import {OderDetail} from '../model/oderDetail';
import {Observable} from "rxjs";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
    declarations: [
        MenuOrderComponent,
        FormatTimePipe
    ],
    imports: [
        CommonModule,
        MenuModuleRoutingModule,
        ReactiveFormsModule,
        FormsModule
    ]
})
export class MenuModuleModule {
}
