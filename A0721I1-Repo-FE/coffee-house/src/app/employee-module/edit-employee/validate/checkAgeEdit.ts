import {AbstractControl, ValidationErrors} from "@angular/forms";

export function checkAgeEdit(control: AbstractControl): ValidationErrors | null {
  var parts = control.value.split("-");
  var dtDOB = new Date(parts[0],parts[1], parts[2]);
  var dtCurrent = new Date();

  if (dtCurrent.getFullYear() - dtDOB.getFullYear()  <  18){
    return {'checkage' : true, 'requiredValue': 18}
  }
  return null;
}
