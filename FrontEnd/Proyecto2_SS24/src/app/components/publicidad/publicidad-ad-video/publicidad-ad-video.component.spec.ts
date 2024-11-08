import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicidadAdVideoComponent } from './publicidad-ad-video.component';

describe('PublicidadAdVideoComponent', () => {
  let component: PublicidadAdVideoComponent;
  let fixture: ComponentFixture<PublicidadAdVideoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicidadAdVideoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublicidadAdVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
