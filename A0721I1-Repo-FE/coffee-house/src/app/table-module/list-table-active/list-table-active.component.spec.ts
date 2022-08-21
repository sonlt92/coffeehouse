import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTableActiveComponent } from './list-table-active.component';

describe('ListTableActiveComponent', () => {
  let component: ListTableActiveComponent;
  let fixture: ComponentFixture<ListTableActiveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListTableActiveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListTableActiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
