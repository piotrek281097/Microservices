import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassicLibraryBooksListComponent } from './classic-library-books-list.component';

describe('ClassicLibraryBooksListComponent', () => {
  let component: ClassicLibraryBooksListComponent;
  let fixture: ComponentFixture<ClassicLibraryBooksListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassicLibraryBooksListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassicLibraryBooksListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
