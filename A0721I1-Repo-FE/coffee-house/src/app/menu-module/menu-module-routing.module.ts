import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MenuOrderComponent} from './menu-order/create-menu-order.component';
import {AuthGuardService as AuthGuard} from "../login-module/service/auth-guard.service";

const routes: Routes = [
  {
    path: 'menu-order-child/:idTable/:idOrder',
    component: MenuOrderComponent,
    canLoad: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MenuModuleRoutingModule { }
