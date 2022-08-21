import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CreateFeedbackComponent} from './create-feedback/create-feedback.component';
import {ListFeedbackComponent} from './list-feedback/list-feedback.component';
import {DetailFeedbackComponent} from './detail-feedback/detail-feedback.component';


const routes: Routes = [
  {
    path: 'create',
    component: CreateFeedbackComponent
  },
  {
    path: 'list',
    component: ListFeedbackComponent
  },
  {
    path: 'detail/:id',
    component: DetailFeedbackComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FeedbackModuleRoutingModule { }
