import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {IncomeComponent} from './income/income.component';


const routes: Routes = [
  {
    path: 'income-child',
    component: IncomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IncomeModuleRoutingModule { }
