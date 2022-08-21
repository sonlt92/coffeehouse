import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListTableComponent} from './list-table/list-table.component';
import {CreateTableComponent} from './create-table/create-table.component';
import {DetailTableComponent} from './detail-table/detail-table.component';
import {AuthGuardService as AuthGuard} from '../login-module/service/auth-guard.service';

import {EditTableComponent} from './edit-table/edit-table.component';
import {ListTableActiveComponent} from './list-table-active/list-table-active.component';

const routes: Routes = [
  {
    path: 'list',
    component: ListTableComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'create',
    component: CreateTableComponent
  },
  {
    path: 'detail/:id',
    component: DetailTableComponent
  },
  {

    path: 'edit/:id',
    component: EditTableComponent
  },
  {
    path: 'active',
    component: ListTableActiveComponent

  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TableModuleRoutingModule {
}
