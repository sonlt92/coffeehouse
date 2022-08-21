import { Component, OnInit } from '@angular/core';
import {EmployeeService} from '../service/employee.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Employee} from '../../model/employee';

@Component({
  selector: 'app-detail-employee',
  templateUrl: './detail-employee.component.html',
  styleUrls: ['./detail-employee.component.css']
})
export class DetailEmployeeComponent implements OnInit {

  constructor(private employeeService: EmployeeService, private activatedRoute: ActivatedRoute, private router: Router ) { }
  idUser: number;
  employee: Employee;
  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((param) => {
      this.idUser = +param.get('userRoutes');
      // console.log(this.idUser);
      // @ts-ignore
      this.employee = this.employeeService.findByIdUser(this.idUser).subscribe(
        (data) => {
          this.employee = data;
          console.log(this.employee);
          // console.log(this.employee);
        },
        () => {},
        () => {
          // console.log(this.employee);
        }
      );
    });
  }

  back() {
    this.router.navigateByUrl('/home');
  }

  showPassword() {
    // console.log('success');
    const passField = document.querySelector('#inputPassword');
    const showBtn = document.querySelector('#iconPassword i');
    // @ts-ignore
    // tslint:disable-next-line:only-arrow-functions
    if (passField.type === 'password'){
      // @ts-ignore
      passField.type = 'text';
      showBtn.classList.remove('bx-show');
      showBtn.classList.add('bx-hide');
    }else{
      // @ts-ignore
      passField.type = 'password';
      showBtn.classList.remove('bx-hide');
      showBtn.classList.add('bx-show');
    }
    /*showBtn.addEventListener('click', function() {
      // @ts-ignore
      if (passField.type === 'password'){
        // @ts-ignore
        passField.type = 'text';
        showBtn.classList.add('hide-btn');
      }else{
        // @ts-ignore
        passField.type = 'password';
        showBtn.classList.remove('hide-btn');
      }
      // const currentType = passField.getAttribute('type');
      // passField.setAttribute('type', currentType === 'password' ? 'text' : 'password');
    });*/
  }
}
