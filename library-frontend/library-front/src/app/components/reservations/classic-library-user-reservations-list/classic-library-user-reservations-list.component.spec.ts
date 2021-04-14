import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassicLibraryUserReservationsListComponent } from './classic-library-user-reservations-list.component';

describe('ClassicLibraryUserReservationsListComponent', () => {
  let component: ClassicLibraryUserReservationsListComponent;
  let fixture: ComponentFixture<ClassicLibraryUserReservationsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassicLibraryUserReservationsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassicLibraryUserReservationsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
