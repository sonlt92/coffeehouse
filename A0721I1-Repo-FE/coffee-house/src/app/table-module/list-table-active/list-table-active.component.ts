import {Component, OnInit} from '@angular/core';
import {Table} from '../../model/table';
import {TableService} from '../service/table.service';
import {Router} from '@angular/router';
import {Employee} from '../../model/employee';
import {TokenStorageService} from '../../login-module/service/token-storage.service';

@Component({
  selector: 'app-list-table-active',
  templateUrl: './list-table-active.component.html',
  styleUrls: ['./list-table-active.component.css']
})
export class ListTableActiveComponent implements OnInit {
  tables: Table[];
  employee: Employee;
  dateOrder: string;
  /* Lưu giá trị idOrder */
  idOrder: number;

  constructor(private tableService: TableService,
              private router: Router,
              private  tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.tableService.getAllStatusTable().subscribe((value) => {
      this.tables = value;
    });
  }

  addOrderBeNull(idTable: number) {
    this.tableService.addNewOrder(1, idTable).subscribe(data => {
      this.idOrder = data.idOrder;
      this.router.navigate(['../menu/menu-order-child', idTable, this.idOrder]);
    }, () => {
    }, () => {
    });
  }

}
