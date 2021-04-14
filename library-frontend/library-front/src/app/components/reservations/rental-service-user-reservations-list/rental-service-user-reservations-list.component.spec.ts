import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentalServiceUserReservationsListComponent } from './rental-service-user-reservations-list.component';

describe('RentalServiceUserReservationsListComponent', () => {
  let component: RentalServiceUserReservationsListComponent;
  let fixture: ComponentFixture<RentalServiceUserReservationsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentalServiceUserReservationsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentalServiceUserReservationsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
