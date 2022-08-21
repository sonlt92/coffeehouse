import {Component, OnDestroy, OnInit} from '@angular/core';
import {Employee} from '../../model/employee';
import {EmployeeService} from '../service/employee.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';


@Component({
  selector: 'app-list-employee',
  templateUrl: './list-employee.component.html',
  styleUrls: ['./list-employee.component.css']
})
export class ListEmployeeComponent implements OnInit {
  employeeList: Employee[] = [];
  employee: Employee;
  searchForm: FormGroup;
  emptyForm = false;
  allow = false;
  pageNumber = 0;
  totalPage: number[];
  message = this.employeeService.message;

  constructor(private employeeService: EmployeeService, private activatedRoute: ActivatedRoute, private router: Router) {
    this.searchForm = new FormGroup({
      username: new FormControl(''),
      name: new FormControl(''),
      phone: new FormControl('')
    });
  }

  ngOnInit(): void {
    this.showSearchEmployee(this.pageNumber=0);
    this.searchForm = new FormGroup({
      username: new FormControl(''),
      name: new FormControl(''),
      phone: new FormControl('')
    });
  }

  deleteEmployee(idEmployee: number) {
    this.employeeService.findByIdEmployee(idEmployee).subscribe(data => {
      if (data.idEmployee === idEmployee) {
        this.employeeService.deleteEmployee(idEmployee).subscribe(next => {
          this.ngOnInit();
        });
      }
    })
  }

  getEmployeeById(idEmployee: number) {
    this.employeeService.findByIdEmployee(idEmployee).subscribe(data => {
      this.employee = data;
    });
  }

  checkSearchEmployee() {
    let username = this.searchForm.value.username;
    let name = this.searchForm.value.name;
    let phone = this.searchForm.value.phone;
    if (username === '') {
      username = 'null';
    }
    if (name === '') {
      name = 'null';
    }
    if (phone === '') {
      phone = 'null';
    }
    if (username === 'null' && name === 'null' && phone === 'null') {
      this.emptyForm = true;
    }
    this.pageNumber =0;
    this.showSearchEmployee( this.pageNumber);
  }

  showSearchEmployee(page: number) {
    let username = this.searchForm.value.username;
    let name = this.searchForm.value.name;
    let phone = this.searchForm.value.phone;
    if (username === '') {
      username = 'null';
    }
    if (name === '') {
      name = 'null';
    }
    if (phone === '') {
      phone = 'null';
    }
    this.employeeService.showSearchEmployee(username, name, phone, page).subscribe((data: any) => {
      this.employeeList = data.content;
      this.setPage(data.totalPages);
    });
  }

  resetPage() {
    this.ngOnInit();
  }

  setPage(totalPage: number) {
    this.totalPage = new Array(totalPage);
  }

  changePageNumber(number: number) {
    this.pageNumber = number;
    this.showSearchEmployee(this.pageNumber);
  }

  perviousPage() {
    if (this.pageNumber <= 0) {
      alert('Không thể chuyển qua trang trước!');
    } else {
      this.pageNumber -= 1;
      this.showSearchEmployee(this.pageNumber);
    }
  }

  nextPage() {
    if (this.pageNumber == this.totalPage.length) {
      alert('Không thể chuyển qua trang sau!');
    } else {
      this.pageNumber += 1;
      this.showSearchEmployee(this.pageNumber);
    }
  }

}
