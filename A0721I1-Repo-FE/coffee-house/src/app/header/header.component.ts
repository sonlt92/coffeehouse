import {Component, OnInit} from '@angular/core';
import {Router, RouterLinkActive} from '@angular/router';
import {Employee} from '../model/employee';
import {ProductService} from '../product-module/service/product.service';
import {TokenStorageService} from '../login-module/service/token-storage.service';
import {EmployeeService} from '../employee-module/service/employee.service';
import {ShareService} from '../login-module/service/share.service';
import swal from 'sweetalert';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  employee: Employee;
  idUser: number;
  isLogin = false;
  roles: [];
  username: string;
  token: string;


  constructor(
    private service: ProductService,
    public tokenStorageService: TokenStorageService,
    private router: Router,
    private employeeService: EmployeeService,
    private shareService: ShareService,
  ) {
    this.shareService.getClickEvent().subscribe(() => {
      this.loadHeader();
    });
  }

  loadHeader(): void {
    if (this.tokenStorageService.getToken()) {
      this.token = this.tokenStorageService.getUser().token;
      this.roles = this.tokenStorageService.getUser().roles;
      this.username = this.tokenStorageService.getUser().username;
    }
    this.isLogin = this.token != null;
    console.log(this.isLogin);
    console.log(this.token);
    this.getPositionById();
  }

  ngOnInit(): void {
    this.loadHeader();
  }

  logout() {
    swal({
      title: 'Đăng xuất',
      text: 'Bạn có chắc là muốn đăng xuất khỏi hệ thống không ?',
      icon: 'warning',
      buttons: ['Hủy', true],
      dangerMode: true,
    })
      .then((willSignOut) => {
        if (willSignOut) {
          swal('Bạn đã đăng xuất khỏi hệ thống', {
            icon: 'success',
          });
          setTimeout(() => {
            this.tokenStorageService.signOut();
            this.ngOnInit();
            this.router.navigateByUrl('/');
            setTimeout(() => {
              window.location.reload();
            }, 50);
          }, 700);
        } else {}
      });
    // if (window.confirm('Bạn có chắc là muốn đăng xuất ra khỏi hệ thống ?')){
    //   this.tokenStorageService.signOut();
    //   this.ngOnInit();
    // }
  }

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
