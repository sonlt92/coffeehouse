import { Injectable } from '@angular/core';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor(private remaid: ToastrService) { }
  showMessage(message){
    this.remaid.success(message, 'Thông báo:');
  }

  showMessageErrors(message){
    this.remaid.error(message, 'Thông báo:');
  }
}
