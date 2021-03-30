import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserRentalMenuComponent } from './user-rental-menu.component';

describe('UserRentalMenuComponent', () => {
  let component: UserRentalMenuComponent;
  let fixture: ComponentFixture<UserRentalMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserRentalMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserRentalMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
