import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Table} from '../../model/table';
import {Status} from '../../model/status';
import {TableService} from '../service/table.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-edit-table',
  templateUrl: './edit-table.component.html',
  styleUrls: ['./edit-table.component.css']
})
export class EditTableComponent implements OnInit {

  private subscription: Subscription | undefined;
  updateForm: FormGroup;
  table: Table;
  statusTableDefault: string;
  status: Status[] = [];

  // @Output('message') massage = new EventEmitter<String>();
  constructor(
    private _service: TableService,
    private _router: Router,
    private _activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    const id = this._activatedRoute.snapshot.params.id;
    this._service.getAllStatus().subscribe(
      data => {
        this.status = data;
        console.log(this.status);
      }
    );
    this.subscription = this._service.getTableById(id).subscribe(data => {
      this.table = data;
      console.log(data);
      this.statusTableDefault = data.status.nameStatus;
      this.updateForm = new FormGroup(
        {
          idTable: new FormControl(this.table.idTable, [Validators.required]),
          codeTable: new FormControl(data.codeTable, [Validators.required]),
          emptyTable: new FormControl(this.table.emptyTable),
          status: new FormControl(this.table.status.idStatus, [Validators.required]),
        }
      );
      this.updateForm.get('codeTable').setValue(data.codeTable , {onlySelf: true});
    });
  }

  update() {
    // console.log(this.updateForm.value);
    console.log(this.updateForm.value);

    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.status.length; i++) {
      // console.log(this.updateForm.value.status);
      if ((this.updateForm.value.status) == (this.status[i].idStatus)) {
        this.updateForm.value.status = this.status[i];
      }
    }
    // if( this.updateForm.value == null){
    //   this._service.message = 'Khong tim thay trang thai';
    //   document.getElementById('noti').hidden = false;
    // }else {
    console.log(this.updateForm.value);
    this._service.updateTable(this.table.idTable, this.updateForm.value).subscribe(() => {
      this._service.message = 'Cập nhật bàn thành công!';
      this._router.navigateByUrl('/table/list');
    }, error => {
      console.log('error');
    });
  }

  return() {
    this._service.message = 'Cập nhật bàn thất bại!';
    this._router.navigateByUrl('/table/list');
  }

  check() {
    const id = this._activatedRoute.snapshot.params.id;
    this.subscription = this._service.getTableById(id).subscribe(data => {
      this.table = data;
      if (this.table.emptyTable === true) {
        this._service.message = 'Không đươc phép!';
      }
    });
  }
}
