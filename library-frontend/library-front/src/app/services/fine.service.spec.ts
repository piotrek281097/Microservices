import { TestBed } from '@angular/core/testing';

import { FineService } from './fine.service';

describe('FineService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FineService = TestBed.get(FineService);
    expect(service).toBeTruthy();
  });
});
