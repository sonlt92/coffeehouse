import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {Product} from '../model/product';
import {ProductService} from '../product-module/service/product.service';
import {TokenStorageService} from '../login-module/service/token-storage.service';
import {Router} from '@angular/router';
import {Employee} from '../model/employee';
import {EmployeeService} from '../employee-module/service/employee.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private subscription: Subscription | undefined;
  products: Product[];
  productsCart: Product[];
  employee: Employee;
  idUser: number;
  isLogin = false;


  constructor(
    private service: ProductService,
    public tokenStorageService: TokenStorageService,
    private router: Router,
    private employeeService: EmployeeService,
  ) {
  }

  ngOnInit(): void {
    this.findAllNew();
    this.findAllCart();
    this.getPositionById();
  }

  findAllNew() {
    this.service.findAllNew().subscribe(
      (data) => {
        this.products = data;
        console.log(this.products);
      },
      () => {
      },
      () => {
      },
    );
  }

  findAllCart() {
    this.service.findAllCart().subscribe(
      (data) => {
        this.productsCart = data;
        console.log(this.productsCart);
      },
      () => {
      },
      () => {
      },
    );
  }

  logout() {
    this.tokenStorageService.signOut();
    this.router.navigateByUrl('/login/authentication');
  }

// HauLST - làm menu quản lí nhân viên
  getPositionById() {
    if (this.tokenStorageService.getUser()){
      this.idUser = this.tokenStorageService.getUser().id;
      console.log(this.idUser);
      this.employeeService.findByIdUser(this.idUser).subscribe(
        (data) => {
          this.employee = data;
        }
      );
    }
  }
}
