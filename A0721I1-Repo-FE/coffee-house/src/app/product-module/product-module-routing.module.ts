import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CreateProductComponent} from './create-product/create-product.component';
import {EditProductComponent} from './edit-product/edit-product.component';
import {ListProductComponent} from './list-product/list-product.component';

const routes: Routes = [
  {
    path: 'create',
    component: CreateProductComponent
  },
  {
    path: 'edit/:id',
    component: EditProductComponent
  },
  {
    path: 'list',
    component: ListProductComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductModuleRoutingModule { }
