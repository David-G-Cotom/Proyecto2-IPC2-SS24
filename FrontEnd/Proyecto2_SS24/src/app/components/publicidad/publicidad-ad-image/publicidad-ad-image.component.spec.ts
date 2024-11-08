import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicidadAdImageComponent } from './publicidad-ad-image.component';

describe('PublicidadAdImageComponent', () => {
  let component: PublicidadAdImageComponent;
  let fixture: ComponentFixture<PublicidadAdImageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicidadAdImageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublicidadAdImageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
