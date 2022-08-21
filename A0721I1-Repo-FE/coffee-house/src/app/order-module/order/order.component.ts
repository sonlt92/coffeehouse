import {Component, OnInit} from '@angular/core';

import {OderService} from '../service/oder.service';
import {Oder} from '../../model/oder';
import {HttpClient} from '@angular/common/http';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {OderDetail} from '../../model/oderDetail';

declare function numPage(n): any;

@Component({
  selector: 'app-oder',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  orders: Oder[];
  order: Oder;
  public searchOrder: FormGroup;
  totalPage: number;
  page = 0;
  pageQuantity: number;
  message: string;
  idOrder: number;
  orderDetail: OderDetail[];

  constructor(private oderService: OderService, private http: HttpClient, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    // this.service.getTotalPage().subscribe((next) => this.totalPage = next);
    // console.log('day la tong page = ' + this.totalPage);
    // this.service.getList().subscribe(data => this.totalPage = Math.ceil(data.length / 5), () => {}, () => {});
    // @ts-ignore
    this.searchOrder = new FormGroup({
      // numPage: new FormControl(''),
      idOrder: new FormControl('', Validators.pattern('[1-9]+')),
      dateOrder: new FormControl('')
    });
    // this.getTotalPage();
    this.findAll();
  }

  getTotalPage() {
    if (this.searchOrder.value.idOrder === '' && this.searchOrder.value.dateOrder === '') {
      this.oderService.getList().subscribe(data => {
          this.totalPage = Math.ceil(data.length / 5);
          console.log('day la tong so trang' + this.totalPage);
        }
        ,
        () => {
        },
        () => {
        });
    } else {
      this.oderService.searchList(this.searchOrder.value.idOrder, this.searchOrder.value.dateOrder).subscribe(data => {
          this.totalPage = Math.ceil(data.length / 5);
          console.log('day la tong so trang' + this.totalPage);
        }
        ,
        () => {
        },
        () => {
        });
    }
  }

  findAll() {
    this.oderService.getPage(0).subscribe((data: Oder[]) => this.orders = data['content']);
    console.log(this.orders);
  }

  search() {
    this.oderService.searchPage(this.searchOrder.value.idOrder, this.searchOrder.value.dateOrder, 0).subscribe(
      data => {
        if (data != null) {
          this.orders = data['content'];
          this.message = '';
        } else {
          // this.ngOnInit();
          this.orders = null;
          this.message = 'Không Tìm Thấy Hoá Đơn, Xin Vui Lòng Nhập Lại';
        }
      }
    );
  }

  nextPage() {
    this.page = this.page + 1;
    if (this.searchOrder.value.idOrder === '' && this.searchOrder.value.dateOrder === '') {
      this.oderService.getPage(this.page).subscribe(
        (data) => {
          this.orders = data['content'];
          console.log('day la tong so trang' + this.totalPage);
        },
        () => {
        },
        () => {
        });
    } else {
      this.oderService.searchPage(this.searchOrder.value.idOrder, this.searchOrder.value.dateOrder, this.page).subscribe(
        (data) => {
          this.orders = data['content'];
          console.log('day la tong so trang' + this.totalPage);
        },
        () => {
        },
        () => {
        },
      );
    }
  }

  previousPage() {
    this.page = this.page - 1;
    if (this.page >= 0) {
      if (this.searchOrder.value.idOrder === '' && this.searchOrder.value.dateOrder === '') {
        this.oderService.getPage(this.page).subscribe(
          (data) => {
            this.orders = data['content'];
            console.log('day la tong so trang' + this.totalPage);
          },
          () => {
          },
        );
      } else {
        this.oderService.searchPage(this.searchOrder.value.idOrder, this.searchOrder.value.dateOrder, this.page).subscribe(
          (data) => {
            this.orders = data['content'];
            console.log('day la tong so trang' + this.totalPage);
          },
          () => {
          },
        );
      }
    }
  }

}

