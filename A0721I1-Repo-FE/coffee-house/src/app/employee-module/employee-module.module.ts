import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeModuleRoutingModule } from './employee-module-routing.module';

import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { EditEmployeeComponent } from './edit-employee/edit-employee.component';
import { ListEmployeeComponent } from './list-employee/list-employee.component';
import { DetailEmployeeComponent } from './detail-employee/detail-employee.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgxPaginationModule} from 'ngx-pagination';


@NgModule({
  declarations: [
    CreateEmployeeComponent,
    EditEmployeeComponent,
    ListEmployeeComponent,
    DetailEmployeeComponent
  ],
    imports: [
        CommonModule,
        FormsModule,
        EmployeeModuleRoutingModule,
        // HttpClientModule,
        ReactiveFormsModule,
        NgxPaginationModule,
    ]
})
export class EmployeeModuleModule { }
