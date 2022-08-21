import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CreateEmployeeComponent} from './create-employee/create-employee.component';
import {EditEmployeeComponent} from './edit-employee/edit-employee.component';
import {ListEmployeeComponent} from './list-employee/list-employee.component';
import {DetailEmployeeComponent} from './detail-employee/detail-employee.component';
import {AuthGuardService as AuthGuard} from '../login-module/service/auth-guard.service';


const routes: Routes = [
  {
    path: 'create',
    component: CreateEmployeeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'edit/:id',
    component: EditEmployeeComponent,
    canLoad: [AuthGuard]
  },
  {
    path: 'list',
    component: ListEmployeeComponent,
    canLoad: [AuthGuard]
  },
  {
    path: 'detail/:userRoutes',
    component: DetailEmployeeComponent,
    canLoad: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeModuleRoutingModule { }
