import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormSuscripcionComponent } from './form-suscripcion.component';

describe('FormSuscripcionComponent', () => {
  let component: FormSuscripcionComponent;
  let fixture: ComponentFixture<FormSuscripcionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormSuscripcionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormSuscripcionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
