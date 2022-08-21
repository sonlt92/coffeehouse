import {AbstractControl, AsyncValidatorFn, ValidationErrors} from "@angular/forms";
import {TableService} from "../../service/table.service";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Table} from "../../../model/table";

export function checkCodeTable(userTable: TableService): AsyncValidatorFn {
  return (control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> => {
    return userTable.checkId(control.value).pipe( map (
      (table: Table[]) => {
        return (table && table.length > 0) ? {"checkCodeTable": true} : null;
      }
    ));
  };
}
