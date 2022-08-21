import { Component, OnInit } from '@angular/core';

import {Position} from '../../model/position';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {EmployeeService} from '../service/employee.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {Employee} from '../../model/employee';
import {User} from '../../model/user';
import {checkAgeEdit} from './validate/checkAgeEdit';


@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.css']
})
export class EditEmployeeComponent implements OnInit {
  positions: Position[] = [];
  editEmployeeForm: FormGroup;
  employee: Employee;
  user: User;

  constructor(private employeeService: EmployeeService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      const id = Number(paramMap.get('id'));
      this.employeeService.findByIdEmployee(id).subscribe(next => {
        this.employee = next;
        this.user = next.user;
        console.log(this.employee);
        this.editEmployeeForm.patchValue({
          idEmployee: this.employee.idEmployee,
          nameEmployee: this.employee.nameEmployee,
          addressEmployee:  this.employee.addressEmployee,
          phoneEmployee:  this.employee.phoneEmployee,
          genderEmployee:  this.employee.genderEmployee,
          position: this.employee.position.idPosition,
          dateOfBirthEmployee:  this.employee.dateOfBirthEmployee,
          salaryEmployee: this.employee.salaryEmployee,
          user:  this.employee.user.username,
        })
        this.editEmployeeForm.get('position').setValue(this.employee.position.idPosition , {onlySelf: true});

      });
    });

    this.editEmployeeForm = this.formBuilder.group({
      idEmployee: ['', ],

      nameEmployee: ['', [Validators.required,Validators.pattern(/^[A-Za-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ' ]+$/)]],
      addressEmployee:  ['', Validators.required],
      phoneEmployee: ['', [Validators.required,Validators.pattern(/^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$/)]],
      genderEmployee:  ['', Validators.required],
      dateOfBirthEmployee:  ['', [Validators.required,checkAgeEdit]],
      salaryEmployee: ['', [Validators.required]],
      position:  ['', Validators.required],
      user:  ['', [Validators.required,Validators.minLength(6 ), Validators.pattern(/^(?!.*admin)+(?!.*root).*$/)]],
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

  editSubmit() {
    console.log("aaa")
    if (this.editEmployeeForm.valid) {
      console.log("bbbb")

      this.employee = this.editEmployeeForm.value;
      this.user.username = this.employee.user;
      this.employee.user = this.user;
      console.log(this.employee);
      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < this.positions.length; i++) {
        // tslint:disable-next-line:triple-equals
        if ((this.employee.position) == (this.positions[i].idPosition)) {
          this.employee.position = this.positions[i];
        }
      }
      this.employeeService.updateEmployee(this.employee).subscribe(
        () => {
        },
        () => {
        },
        () => {
          this.employeeService.message = 'chỉnh sửa thành công ' + this.employee.nameEmployee;
          this.router.navigateByUrl('/employee/list');
        },
      );
    }
  }

}
