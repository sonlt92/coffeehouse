import {Component, OnInit} from '@angular/core';
import {TableService} from '../service/table.service';
import {FormControl, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-list-table',
  templateUrl: './list-table.component.html',
  styleUrls: ['./list-table.component.css']
})
export class ListTableComponent implements OnInit {
  private subscription: Subscription | undefined;
  tables: any;
  formSearch: FormGroup;
  message: String = null;
  pageNumber = 0;
  totalPage: number[];

  constructor(private tableService: TableService, private router: Router,
  private _service: TableService,
  private _router: Router,
  private _activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.findAllTable(this.pageNumber);
    this.formSearch = new FormGroup({
      tableNumber: new FormControl(),
      status: new FormControl(),
      emptyTable: new FormControl()
    })
    this.message = this.tableService.message;
    if (this.message != null) {
      document.getElementById('noti').hidden = false;
    }
  }

  findAllTable(pageNumber) {
    this.tableService.findAllTable(pageNumber).subscribe((tables: any) => {
      this.tables = tables.content;
      this.setPage(tables.totalPages);
    });
  }

  deleteTable(id: number) {
    if (this.formSearch.value.tableNumber != '' && this.formSearch.value.tableNumber != null) {
      this.formSearch.controls.status.enable();
      this.formSearch.controls.emptyTable.enable();
    }

    this.tableService.deleteTable(id).subscribe(() => {}, () => {}, () => {
      this.tableService.message = 'Xoá bàn thành công!';

      this.ngOnInit();
    });
  }

  changeTableNumber(value) {
    if (value != '') {
      this.formSearch.controls.status.disable();
      this.formSearch.controls.emptyTable.disable();
    } else {
      this.formSearch.controls.status.enable();
      this.formSearch.controls.emptyTable.enable();
    }
  }

  submitFormSearch(pageNumber: number) {
    this.pageNumber = pageNumber;
    const codeTable = this.formSearch.value.tableNumber;
    const idStatus = this.formSearch.value.status;
    const emptyTable = this.formSearch.value.emptyTable;

    if (codeTable != '' && codeTable !== null) {
      this.tableService.findAllTableByCodeTable(codeTable, this.pageNumber).subscribe((tables: any) => {
        if (tables == null) {
          this.message = 'Không tìm thấy Số bàn ' + codeTable;
          document.getElementById('noti').hidden = false;
        } else {
          this.tables = tables.content;
          this.setPage(tables.totalPages)
          this.message = null;
        }
      });
    } else if (idStatus !== null && idStatus !== 'null' && emptyTable !== null && emptyTable !== 'null') {
      this.tables = this.tableService.findAllTableByIdStatusAndEmptyTable(idStatus, emptyTable, this.pageNumber).subscribe((tables: any) => {
        if (tables == null) {
          this.message = 'Không tìm thấy Trạng thái hoặc Trống!';
          document.getElementById('noti').hidden = false;
        } else {
          this.tables = tables.content;
          this.setPage(tables.totalPages)
          this.message = null;
        }
      });
    } else if (idStatus !== null && idStatus !== 'null') {
      this.tables = this.tableService.findAllTableByIdStatus(idStatus, this.pageNumber).subscribe((tables: any) => {
        if (tables == null) {
          if (idStatus == 1) {
            this.message = 'Không tìm thấy Trạng thái Tốt!';
            document.getElementById('noti').hidden = false;
          } else {
            this.message = 'Không tìm thấy Trạng thái Lỗi!';
            document.getElementById('noti').hidden = false;
          }
        } else {
          this.tables = tables.content;
          this.setPage(tables.totalPages)
          this.message = null;
        }
      });
    } else if (emptyTable !== null && emptyTable !== 'null') {
      this.tables = this.tableService.findAllTableByEmptyTable(emptyTable, this.pageNumber).subscribe((tables: any) => {
        if (tables == null) {
          if (emptyTable) {
            this.message = 'Không tìm thấy bàn Còn trống!';
            document.getElementById('noti').hidden = false;
          } else {
            this.message = 'Không tìm thấy bàn Đã đặt!';
            document.getElementById('noti').hidden = false;
          }
        } else {
          this.tables = tables.content;
          this.setPage(tables.totalPages)
          this.message = null;
        }
      });
    } else {
      this.tableService.message = null;
      this.ngOnInit();
    }
    this.tableService.message = null;
  }
  hide() {
    document.getElementById('noti').hidden = true;
  }

  returnTableDetail(idTable: number) {
    this.tableService.message = null;
    this.router.navigateByUrl('/table/detail/' + idTable);
  }

  setPage(totalPage: number) {
    this.totalPage = new Array(totalPage);
  }

  changePageNumber(number: number) {
    const codeTable = this.formSearch.value.tableNumber;
    const idStatus = this.formSearch.value.status;
    const emptyTable = this.formSearch.value.emptyTable;
    this.pageNumber = number;
    if ((codeTable != '' && codeTable !== null) || (idStatus !== null && idStatus !== 'null') || (emptyTable !== null && emptyTable !== 'null')) {
      this.submitFormSearch(number);
    } else {
      this.findAllTable(number);
    }
  }

  perviousPage() {
    if (this.pageNumber <= 0) {
      this.message = 'Không thể chuyển qua trang trước!';
      document.getElementById('noti').hidden = false;
    } else {
      this.pageNumber -= 1;
      this.submitFormSearch(this.pageNumber);
    }
  }

  nextPage() {
    if (this.pageNumber == this.totalPage.length) {
      this.message = 'Không thể chuyển qua trang sau!';
      document.getElementById('noti').hidden = false;
    } else {
      this.pageNumber += 1;
      this.submitFormSearch(this.pageNumber);
    }
  }

  checkEdit(codeTable: string) {
    this.message = 'Không thể chỉnh sửa ' + codeTable;
    document.getElementById('noti').hidden = false;
  }
}
