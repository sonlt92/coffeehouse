import { Component, OnInit } from '@angular/core';
import {ProductService} from '../service/product.service';
import {Product} from '../../model/product';
import {ToastrService} from 'ngx-toastr';


@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {
codeName = '';
nameProduct = '';
trangChu: boolean;
message: string;
page = 0;
pageSearch = 0;
totalPage: number;
totalPageSearch = 0;
// readMore = false;
  constructor(private service: ProductService, private remind: ToastrService) { }
  productList: Product[];
  ngOnInit(): void {
    this.page = 0;
    this.service.findByAll().subscribe( data => this.totalPage = Math.ceil(data.length / 8), () => {}, () => {}, );
    this.findByAllPage();
  }
  findByAllPage(){
    this.service.findByAllPaginng(0).subscribe(
      (data: Product[]) => { this.productList = data['content'] ; this.trangChu = true; },
      () => {},
      () => {},
    );
  }

  delete(idProduct: any) {
    console.log(idProduct);
    this.service.deleteById(idProduct).subscribe(()  => {
      this.ngOnInit();
      this.remind.success('Xoá thành công!', 'Thông báo:');
    }
    );
  }
  searchPage() {
    this.pageSearch = 0 ;
    this.service.search(this.codeName, this.nameProduct).subscribe(data => this.totalPageSearch = Math.ceil(data.length / 8) );
    this.service.searchPage(this.codeName, this.nameProduct, 0).subscribe(
      (data: Product[]) => {
        if (data != null){
          this.productList = data['content'] ;
          this.trangChu = false;
          this.message = ''; }
        else {
          this.ngOnInit();
          this.message = 'Không Tồn Tại Xin Vui Lòng Nhập Lại';
        }
      },
      () => {},
      () => {},
    );
  }
  trangchu() {
    this.ngOnInit();
    this.trangChu = true;
  }


  next() {
    this.page = this.page + 1;
    this.service.findByAllPaginng(this.page).subscribe(
      data => this.productList = data['content'],
      () => {},
      () => {},
    );

  }

  previod() {
    this.page = this.page - 1;
    if (this.page >= 0){
      this.service.findByAllPaginng(this.page).subscribe(
        data => this.productList = data['content'],
        () => {},
        () => {},
      );
    }
    }

  previodPage() {
    this.pageSearch = this.pageSearch - 1;

    if (this.page >= 0){
      this.service.searchPage(this.codeName, this.nameProduct, this.pageSearch).subscribe(
        (data: Product[]) => {
          if (data != null){
            this.productList = data['content'] ;
            this.trangChu = false;
          }
        },
        () => {},
        () => {},
      );
    }
  }

  nextPage() {
    this.pageSearch = this.pageSearch + 1;

    if (this.page >= 0){
      this.service.searchPage(this.codeName, this.nameProduct, this.pageSearch).subscribe(
        (data: Product[]) => {
          if (data != null){
            this.productList = data['content'] ;
            this.trangChu = false;
          }
        },
        () => {},
        () => {},
      );
    }
  }
  // showMore(idProduct: number) {
  //   this.service.findById(idProduct).subscribe(data=>) {
  //     this.readMore = !this.readMore;
  //     if (this.readMore) {
  //       document.getElementById('readMore').style.webkitLineClamp = 'initial';
  //     } else {
  //       document.getElementById('readMore').style.webkitLineClamp = '6';
  //     }
  //   }
  // }
}
