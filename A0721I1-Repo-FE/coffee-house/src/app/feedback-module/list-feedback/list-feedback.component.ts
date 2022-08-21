import {Component, OnInit} from '@angular/core';

import {Feedback} from '../../model/feedback';
import {FeedbackService} from '../service/feedback.service';
import {ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup} from '@angular/forms';


@Component({
  selector: 'app-list-feedback',
  templateUrl: './list-feedback.component.html',
  styleUrls: ['./list-feedback.component.css']
})
export class ListFeedbackComponent implements OnInit {

  feedbackList: Feedback[] = [];
  feedbackDetail: Feedback = {
    idFeedback: 0,
    codeFeedback: '',
    dateFeedback: '',
    contentFeedback: '',
    namePeopleFeedback: '',
    emailPeopleFeedback: '',
    imageFeedback: '',
  };
  date: String;
  indexPagination = 1;
  totalPagination: number;
  listFeedbackNotPagination: Feedback[] = [];
  public searchFeedback: FormGroup;
  message = false;

  constructor(private feedbackService: FeedbackService,
              private activatedRoute: ActivatedRoute,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.getAll();
    this.getAllNotPagination();
    this.searchFeedback = this.fb.group({
      date: ['']
    });
    if (this.feedbackList == null) {
      this.message = true;
    }
  }

  getAll() {
    this.feedbackService.getAll(0).subscribe((feedbackList: Feedback[]) => {
      if (feedbackList !== null) {
        this.message = false;
        this.feedbackList = feedbackList['content'];
        console.log(feedbackList);
        console.log(this.feedbackList);
        console.log(typeof feedbackList);
        console.log(this.indexPagination);
      }
    });
  }

  getDetailFeedback(id: number) {
    this.feedbackService.findById(id).subscribe(feedback => {
      this.feedbackDetail = feedback;
    });

  }

  getAllNotPagination() {
    this.feedbackService.getAllNotPagination().subscribe(feedbackList => {
      this.listFeedbackNotPagination = feedbackList;
      if ((this.listFeedbackNotPagination.length % 10) != 0) {
        this.totalPagination = (Math.round(this.listFeedbackNotPagination.length / 10)) + 1;
      }
      console.log(this.totalPagination);
    });
  }

  search() {
    this.feedbackService.getFeedbackByDate(this.searchFeedback.value.date, 0).subscribe(feedbackList => {
      if (feedbackList == null) {
        this.message = true;
      } else {
        this.message = false;
        this.feedbackList = feedbackList['content'];
      }
    });
    this.feedbackService.getFeedbackByDateNotPagination(this.searchFeedback.value.date).subscribe(feedbackList => {
      if (feedbackList == null) {
        this.message = true;
      } else {
        this.message = false;
        this.listFeedbackNotPagination = feedbackList;
        // tslint:disable-next-line:triple-equals
        if ((this.listFeedbackNotPagination.length % 10) != 0) {
          this.totalPagination = (Math.round(this.listFeedbackNotPagination.length / 10)) + 1;
        }
        console.log(this.totalPagination);
      }
    });
  }

  findPagination() {
    this.feedbackService.getAll(this.indexPagination - 1).subscribe(feedbackList => {
      this.feedbackList = feedbackList['content'];
    });
  }

  indexPaginationChange(value) {
    this.indexPagination = value.target.value;
  }


  firstPage() {
    this.indexPagination = 1;
    this.ngOnInit();
  }

  nextPage() {
    this.indexPagination = this.indexPagination + 1;
    if (this.indexPagination > this.totalPagination) {
      this.indexPagination = this.indexPagination - 1;
    }
    this.feedbackService.getAll(this.indexPagination - 1).subscribe(feedbackList => {
      this.feedbackList = feedbackList['content'];
      console.log(this.indexPagination);
      console.log(this.feedbackList);
    });
  }

  previousPage() {
    this.indexPagination = this.indexPagination - 1;
    if (this.indexPagination == 0) {
      this.indexPagination = 1;
      this.ngOnInit();
    } else {
      this.feedbackService.getAll(this.indexPagination - 1).subscribe(feedbackList => {
        this.feedbackList = feedbackList['content'];
      });
    }
  }

  lastPage() {
    this.indexPagination = this.totalPagination;
    this.feedbackService.getAll(this.indexPagination - 1).subscribe(feedbackList => {
      this.feedbackList = feedbackList['content'];
    });
  }
}
