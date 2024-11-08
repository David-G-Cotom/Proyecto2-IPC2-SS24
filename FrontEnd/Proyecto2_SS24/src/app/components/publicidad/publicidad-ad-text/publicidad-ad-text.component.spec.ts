import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicidadAdTextComponent } from './publicidad-ad-text.component';

describe('PublicidadAdTextComponent', () => {
  let component: PublicidadAdTextComponent;
  let fixture: ComponentFixture<PublicidadAdTextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicidadAdTextComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublicidadAdTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
