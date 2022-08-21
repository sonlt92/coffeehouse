import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductModuleRoutingModule } from './product-module-routing.module';
import { CreateProductComponent } from './create-product/create-product.component';
import { ListProductComponent } from './list-product/list-product.component';
import { EditProductComponent } from './edit-product/edit-product.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AngularFireModule} from '@angular/fire';
import {environment} from '../../app/product-module/environments/environment';



@NgModule({
  declarations: [
    CreateProductComponent,
    ListProductComponent,
    EditProductComponent
  ],
    imports: [
        CommonModule,
        ProductModuleRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        AngularFireModule.initializeApp(environment.firebaseConfig)
    ]
})
export class ProductModuleModule { }
