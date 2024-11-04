import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdImageAEditarComponent } from './ad-image-aeditar.component';

describe('AdImageAEditarComponent', () => {
  let component: AdImageAEditarComponent;
  let fixture: ComponentFixture<AdImageAEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdImageAEditarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdImageAEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
