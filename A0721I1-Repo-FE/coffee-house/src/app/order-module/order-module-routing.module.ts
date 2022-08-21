import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OrderComponent} from './order/order.component';
import {OrderDetailComponent} from './order-detail/order-detail.component';

const routes: Routes = [
  {
    path: 'order-child',
    component: OrderComponent
  }, {
    path: 'order-detail/:id',
    component: OrderDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderModuleRoutingModule {
}
