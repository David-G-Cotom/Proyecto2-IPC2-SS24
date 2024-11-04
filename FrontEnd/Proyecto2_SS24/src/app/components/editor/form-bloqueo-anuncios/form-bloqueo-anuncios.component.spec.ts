import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormBloqueoAnunciosComponent } from './form-bloqueo-anuncios.component';

describe('FormBloqueoAnunciosComponent', () => {
  let component: FormBloqueoAnunciosComponent;
  let fixture: ComponentFixture<FormBloqueoAnunciosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormBloqueoAnunciosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormBloqueoAnunciosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
