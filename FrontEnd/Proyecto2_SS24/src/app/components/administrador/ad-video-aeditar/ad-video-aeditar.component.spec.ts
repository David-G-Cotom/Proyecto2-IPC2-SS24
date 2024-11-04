import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdVideoAEditarComponent } from './ad-video-aeditar.component';

describe('AdVideoAEditarComponent', () => {
  let component: AdVideoAEditarComponent;
  let fixture: ComponentFixture<AdVideoAEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdVideoAEditarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdVideoAEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
