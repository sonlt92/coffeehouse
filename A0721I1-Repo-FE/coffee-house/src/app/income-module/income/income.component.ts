import {Component, OnInit} from '@angular/core';
import {IncomeService} from '../service/income.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrls: ['./income.component.css']
})
export class IncomeComponent implements OnInit {
  constructor(
    private incomeService: IncomeService,
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private datePipe: DatePipe,
    private router: Router) {
  }

  totalOrderToDay: number;
  totalOrderToWeek: number;
  totalOrderToMonth: number;
  totalOrderToYear: number;
  totalOrderDayToDay: number;
  countCafe: number;
  countTea: number;
  countCake: number;
  countOther: number;
  formSumInCome: FormGroup;

  startDay: any;
  endDay: any;
  sumDayToDay: number;

  message: string = null;


  ngOnInit(): void {
    this.getAll();

    /* Define form sum day today */
    this.formSumInCome = this.fb.group({
      startDayForm: ['', Validators.required],
      endDayForm: ['', Validators.required]
    });
  }


  /* Get all */
  getAll() {
    this.getIncomeToDay();
    this.getIncomeToWeek();
    this.getIncomeToMonth();
    this.getIncomeToYear();
    this.getCountCafe();
    this.getCountTea();
    this.getCountCake();
    this.getCountOther();
  }

  /* Get income today */
  getIncomeToDay() {
    this.incomeService.sumTotalOrderDay().subscribe(data => {
      this.totalOrderToDay = data;
    });
  }

  /* Get income Week */
  getIncomeToWeek() {
    this.incomeService.sumTotalOrderWeek().subscribe(data => {
      this.totalOrderToWeek = data;
    });
  }

  /* Get income Month */
  getIncomeToMonth() {
    this.incomeService.sumTotalOrderMonth().subscribe(data => {
      this.totalOrderToMonth = data;
    });
  }

  /* Get income Year */
  getIncomeToYear() {
    this.incomeService.sumTotalOrderYear().subscribe(data => {
      this.totalOrderToYear = data;
    });
  }

  /* Get income day to day */
  submitSumIncome() {
    this.startDay = new Date(this.formSumInCome.get('startDayForm').value);
    this.endDay = new Date(this.formSumInCome.get('endDayForm').value);
    console.log(this.datePipe.transform(this.startDay, 'yyyy-MM-dd'));
    console.log(this.datePipe.transform(this.endDay, 'yyyy-MM-dd'));
    if (this.datePipe.transform(this.startDay, 'yyyy-MM-dd') <= this.datePipe.transform(this.endDay, 'yyyy-MM-dd')) {
      this.incomeService.sumTotalOrderDayToDay(this.datePipe.transform(this.startDay, 'yyyy-MM-dd'),
        this.datePipe.transform(this.endDay, 'yyyy-MM-dd')).subscribe(data => {
        this.totalOrderDayToDay = data;
        console.log(data);
      });
    } else {
      this.message = 'Ngày bắt đầu phải nhỏ hơn ngày kết thúc!';
    }
  }

  hide() {
    document.getElementById('noti').hidden = true;
    this.message = null;
  }

  /* Get count cafe */
  getCountCafe() {
    this.incomeService.countProductCafe().subscribe(data => {
      this.countCafe = data;
    });
  }

  /* Get count tea*/
  getCountTea() {
    this.incomeService.countProductTea().subscribe(data => {
      this.countTea = data;
    });
  }

  /* Get count cake */
  getCountCake() {
    this.incomeService.countProductCafe().subscribe(data => {
      this.countCake = data;
    });
  }

  /* Get count other */
  getCountOther() {
    this.incomeService.countProductOther().subscribe(data => {
      this.countOther = data;
    });
  }
}
