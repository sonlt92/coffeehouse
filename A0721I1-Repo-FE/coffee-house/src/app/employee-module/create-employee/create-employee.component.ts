import { Component, OnInit } from '@angular/core';
import {EmployeeService} from '../service/employee.service';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Position} from '../../model/position';
import {Employee} from '../../model/employee';
import {checkAge} from './validate/checkAge';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {
  positions: Position[] = [];
  createEmployeeForm: FormGroup;
  employeeList: Employee[] = [];
  message: string;

  constructor(private employeeService: EmployeeService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.employeeService.getAllEmployee().subscribe(data => {
      this.employeeList = data;
    });
    this.createEmployeeForm = new FormGroup({
      nameEmployee: new FormControl('', [Validators.required, Validators.pattern(/^[A-Za-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ' ]+$/)]),
      addressEmployee: new FormControl('', Validators.required),
      phoneEmployee: new FormControl('', [Validators.required, Validators.pattern(/^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$/)]),
      genderEmployee: new FormControl('', Validators.required),
      dateOfBirthEmployee: new FormControl('', [Validators.required,checkAge]),
      salaryEmployee: new FormControl('', [Validators.required,Validators.min(100000)]),
      position: new FormControl('', Validators.required),
      user: new FormControl('', [Validators.required,Validators.minLength(6 ), Validators.pattern(/^(?!.*admin)+(?!.*root).*$/)]),
    });
    this.getAllPosition();
  }


  getAllPosition() {
    this.employeeService.getPosition().subscribe(
      next => {
        this.positions = next;
        console.log(this.positions);
      }
    );
  }


  createSubmit() {
    let check;
    for (let i = 0; i < this.employeeList.length; i++) {
      if (this.employeeList[i].user.username == this.createEmployeeForm.value.user) {
        this.message = "tên đăng nhập bị trùng vui lòng nhập lại!"
        check = true;
      }
    }
    if (!check) {
      console.log( 'aa');
      if (this.createEmployeeForm.valid) {
        console.log( 'b');
        const employee = this.createEmployeeForm.value;
            const user = {
              username: employee.user,
              password: '123456',
            };
            employee.user = user;
            console.log(employee);
            // tslint:disable-next-line:prefer-for-of
            for (let i = 0; i < this.positions.length; i++) {
              // tslint:disable-next-line:triple-equals
              if ((employee.position) == (this.positions[i].idPosition)) {
                employee.position = this.positions[i];
              }
            }
            this.employeeService.createEmployee(employee).subscribe(
              () => {

              },
              () => {

              },
              () => {
                this.employeeService.message = 'thêm mới thành công ' + employee.nameEmployee;
                this.router.navigateByUrl('/employee/list');
              },
            );
          }
    }
  }
}
