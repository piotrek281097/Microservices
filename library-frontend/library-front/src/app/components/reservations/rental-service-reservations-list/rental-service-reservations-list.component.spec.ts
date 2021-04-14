import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentalServiceReservationsListComponent } from './rental-service-reservations-list.component';

describe('RentalServiceReservationsListComponent', () => {
  let component: RentalServiceReservationsListComponent;
  let fixture: ComponentFixture<RentalServiceReservationsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentalServiceReservationsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentalServiceReservationsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
