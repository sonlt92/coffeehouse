import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {FeedbackService} from '../service/feedback.service';
import {Router} from '@angular/router';
import {formatDate} from '@angular/common';
import {AngularFireStorage} from '@angular/fire/storage';
import {finalize} from 'rxjs/operators';
import {ToastrService} from 'ngx-toastr';


@Component({
  selector: 'app-create-feedback',
  templateUrl: './create-feedback.component.html',
  styleUrls: ['./create-feedback.component.css']
})
export class CreateFeedbackComponent implements OnInit {



  selectImg: any;
  imgVip = 'https://accounts.viblo.asia/assets/webpack/profile_default.0bca52a.png';

  createFeedbackForm: FormGroup = new FormGroup({
    contentFeedback: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]),
    namePeopleFeedback: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50), Validators.pattern('^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\W|_]+')]),
    emailPeopleFeedback: new FormControl('', [Validators.required, Validators.pattern('^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$')]),
    imageFeedback: new FormControl('', [Validators.required])
  });

  constructor(private feedBackService: FeedbackService,
              private router: Router,
              @Inject(AngularFireStorage) private storage: AngularFireStorage,
              private toast: ToastrService) { }

  VALIDATE_MESSAGES = {
    contentFeedback: [
      {type: 'required', message: 'Phản hồi không được để trống'},
      {type: 'minlength', message: 'Phản hồi có it nhất 3 kí tự'},
      {type: 'maxlength', message: 'Phản hồi có nhiều nhất 100 kí tự'}
    ],
    namePeopleFeedback: [
      {type: 'required', message: 'Tên không được để trống'},
      {type: 'pattern', message: 'Tên không đúng định dạng'},
      {type: 'minlength', message: 'Tên có it nhất 3 kí tự'},
      {type: 'maxlength', message: 'Tên có nhiều nhất 50 kí tự'}
    ],
    emailPeopleFeedback: [
      {type: 'required', message: 'Email không được để trống'},
      {type: 'pattern', message: 'Email không đúng định dạng'}
    ]
  };

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.createFeedbackForm.value);
    const nameImg = this.getCurrentDateTime() + this.selectImg.name;
    const fileRef = this.storage.ref(nameImg);
    this.storage.upload(nameImg, this.selectImg).snapshotChanges().pipe(
      finalize(() => {
        fileRef.getDownloadURL().subscribe((url) => {
          this.createFeedbackForm.patchValue({imageFeedback: url});


          this.feedBackService.saveFeedback(this.createFeedbackForm.value).subscribe(() => {
            this.router.navigateByUrl('/home').then(r => this.toast.success(
              'Thêm mới phản hồi thành công', 'Thông báo'
            ));
          });
        });
      })
    ).subscribe();
  }

  showPreview(event: any) {
    this.selectImg = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(this.selectImg);
    reader.onload = e => {
      console.log(e);
      this.imgVip = reader.result as string;
    };
  }
  getCurrentDateTime(): string {
    return formatDate(new Date(), 'dd-MM-yyyy-hh-mm-ssa', 'en-US');
  }
}
