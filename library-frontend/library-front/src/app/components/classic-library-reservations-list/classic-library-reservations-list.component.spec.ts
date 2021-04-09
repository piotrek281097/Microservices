import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassicLibraryReservationsListComponent } from './classic-library-reservations-list.component';

describe('ClassicLibraryReservationsListComponent', () => {
  let component: ClassicLibraryReservationsListComponent;
  let fixture: ComponentFixture<ClassicLibraryReservationsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassicLibraryReservationsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassicLibraryReservationsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
