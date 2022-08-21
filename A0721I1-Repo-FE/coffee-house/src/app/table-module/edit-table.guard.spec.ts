import { TestBed } from '@angular/core/testing';

import { EditTableGuard } from './edit-table.guard';

describe('EditTableGuard', () => {
  let guard: EditTableGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(EditTableGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
