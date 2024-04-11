import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EasyCardListingComponent } from './easy-card-listing.component';

describe('EasyCardListingComponent', () => {
  let component: EasyCardListingComponent;
  let fixture: ComponentFixture<EasyCardListingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EasyCardListingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EasyCardListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
