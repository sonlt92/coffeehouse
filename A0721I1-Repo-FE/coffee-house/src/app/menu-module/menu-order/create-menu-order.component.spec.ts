import { ComponentFixture, TestBed } from '@angular/core/testing';


class CreateMenuOrderComponent {
}

describe('CreateMenuOderComponent', () => {
  let component: CreateMenuOrderComponent;
  let fixture: ComponentFixture<CreateMenuOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateMenuOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMenuOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
