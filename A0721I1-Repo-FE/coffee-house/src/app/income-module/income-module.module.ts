import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IncomeModuleRoutingModule } from './income-module-routing.module';
import { IncomeComponent } from './income/income.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


@NgModule({
    declarations: [
        IncomeComponent
    ],
    exports: [
        IncomeComponent
    ],
    imports: [
        CommonModule,
        IncomeModuleRoutingModule,
        HttpClientModule,
        ReactiveFormsModule,
        FormsModule
    ]
})
export class IncomeModuleModule { }
