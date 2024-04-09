import { TestBed } from '@angular/core/testing';

import { SetSearchService } from './set-search.service';

describe('SetSearchService', () => {
  let service: SetSearchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SetSearchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
