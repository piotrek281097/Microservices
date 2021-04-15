import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmDeleteBookComponent } from './confirm-delete-book.component';

describe('ConfirmDeleteBookComponent', () => {
  let component: ConfirmDeleteBookComponent;
  let fixture: ComponentFixture<ConfirmDeleteBookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmDeleteBookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmDeleteBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
