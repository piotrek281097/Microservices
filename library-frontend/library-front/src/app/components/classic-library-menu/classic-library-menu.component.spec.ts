import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassicLibraryMenuComponent } from './classic-library-menu.component';

describe('ClassicLibraryMenuComponent', () => {
  let component: ClassicLibraryMenuComponent;
  let fixture: ComponentFixture<ClassicLibraryMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassicLibraryMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassicLibraryMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
