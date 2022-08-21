import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderUnloginComponent } from './header-unlogin.component';

describe('HeaderUnloginComponent', () => {
  let component: HeaderUnloginComponent;
  let fixture: ComponentFixture<HeaderUnloginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeaderUnloginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderUnloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
