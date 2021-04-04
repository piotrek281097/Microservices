import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentalServiceBooksListComponent } from './rental-service-books-list.component';

describe('RentalServiceBooksListComponent', () => {
  let component: RentalServiceBooksListComponent;
  let fixture: ComponentFixture<RentalServiceBooksListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentalServiceBooksListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentalServiceBooksListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
