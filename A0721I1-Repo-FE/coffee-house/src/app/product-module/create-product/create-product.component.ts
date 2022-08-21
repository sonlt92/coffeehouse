import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ProductService} from '../service/product.service';
import {Router} from '@angular/router';
import {TypeProduct} from '../../model/typeProduct';
import {finalize} from 'rxjs/operators';
import {AngularFireStorage} from '@angular/fire/storage';
import {ToastrService} from 'ngx-toastr';
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {
  typeProduct: TypeProduct[];
  selectedImage: any;
  errorImage: string;
  imgVip = 'https://accounts.viblo.asia/assets/webpack/profile_default.0bca52a.png';
  submitted = false;



  VALIDATION_MESSAGE = {
    codeProduct: [
      {type: 'required', message: 'Vui lòng nhập mã món'},
      // {type: 'minlength', message: 'Vui lòng nhập tên có ít nhất 4 kí tự'},
      // {type: 'pattern', message: 'Vui lòng nhập tên đúng'}
    ],
    nameProduct: [
      {type: 'required', message: 'Vui lòng nhập tên món'},
      {type: 'maxlength', message: 'Vui lòng nhập tên món dài dưới 50 kí tự'},
      // {type: 'pattern', message: 'Vui lòng nhập loại vắc-xin đúng'}
    ],
    priceProduct: [
      {type: 'required', message: 'Vui lòng nhập giá'},
    ],
    descriptionProduct: [
      {type: 'required', message: 'Vui lòng nhập mô tả'},
    ],
    amountProduct: [
      {type: 'required', message: 'Vui lòng nhập số lượng'},
    ],
  };

  createForm: FormGroup = new FormGroup({
      idProduct: new FormControl('', Validators.required),
      codeProduct: new FormControl('', Validators.required),
      nameProduct: new FormControl('', [Validators.required, Validators.maxLength(50)]),
      priceProduct: new FormControl('', Validators.required),
      imageProduct: new FormControl('', Validators.required),
      descriptionProduct: new FormControl('', Validators.required),
      quatityProduct: new FormControl('', Validators.required),
      createAt: new FormControl('', Validators.required),
      typeProduct: new FormControl('', Validators.required),
    });



  constructor(private fb: FormBuilder, private service: ProductService,
              private router: Router, private remind: ToastrService,
              @Inject(AngularFireStorage) private storage: AngularFireStorage) {
  }

  ngOnInit(): void {
    this.service.findType().subscribe((data => this.typeProduct = data));
  }


  submit() {
    console.log(this.createForm.value);
    const nameImg = '/A0721I1-' + this.selectedImage.name;
    const fileRef = this.storage.ref(nameImg);
    this.storage.upload(nameImg, this.selectedImage).snapshotChanges().pipe(
      finalize
      (() => {
        fileRef.getDownloadURL().subscribe((url) => {
          this.createForm.patchValue({imageProduct: url});
          this.service.createProduct(this.createForm.value).subscribe(
            () => {},
            error => {
            // this.errorImage = error.error.errorMap.image;
          }, () => {
              this.remind.success('Thêm món mới thành công!', 'Thông báo:');
              this.router.navigateByUrl('/product/list');
            });
        });
      })
    ).subscribe();
    this.submitted = true;
    // stop here if form is invalid
    if (this.createForm.invalid) {
      return;
    }
    console.log(this.createForm.value);
  }

  numberCheck(args) {
    if (args.key === 'e' || args.key === '+' || args.key === '-') {
      return false;
    } else {
      return true;
    }
  }

  checkValidatePrice(input: any) {
    // tslint:disable-next-line:triple-equals
    if (input.target.value != '') {
      this.createForm.controls.price.setErrors({empty: null});
      this.createForm.controls.categoryDto.updateValueAndValidity();
    } else {
      this.createForm.controls.price.setErrors({empty: 'Trống! Xin hãy nhập!'});
    }
  }

    showPreview(event: any) {
    this.selectedImage = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(this.selectedImage);
    reader.onload = e => {
      console.log(e);
      this.imgVip = reader.result as string;
    };
  }


  getDateTime(): string {
    return formatDate(new Date(), 'dd-MM-yyyyhhmmssa', 'en-US');
  }

  get nameProduct() {
    return this.createForm.get('nameProduct');
  }
  get valueSelect() {
    return this.createForm.controls;
  }


  back() {
    this.remind.error('Huỷ thêm mới', 'Thông báo:');
  }
}
