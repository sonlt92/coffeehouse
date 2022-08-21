import {Component, OnInit} from '@angular/core';
import {OderService} from '../service/oder.service';
import {HttpClient} from '@angular/common/http';
import {OderDetail} from '../../model/oderDetail';
import {FormControl, FormGroup} from '@angular/forms';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {Product} from '../../model/product';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {
  orderDetail: OderDetail[];
  idOrder: number;

  constructor(private service: OderService, private http: HttpClient, public activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((data) => {
      this.idOrder = +data.get('id');
      console.log('day la id = ' + this.idOrder);
      // tslint:disable-next-line:no-shadowed-variable
      this.service.getOrderDetailById(this.idOrder).subscribe(
        // tslint:disable-next-line:no-shadowed-variable
        (data) => {
          this.orderDetail = data;
          console.log(this.orderDetail);
        }
        );

      // this.getOrderDetail(this.idOrder).subscribe((next: OderDetail) => this.orderDetail = next);

    });
  }

  getOrderDetail(id: number) {
    return this.service.getOrderDetailById(id);
  }
}


