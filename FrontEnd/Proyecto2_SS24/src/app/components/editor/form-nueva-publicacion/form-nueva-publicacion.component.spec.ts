import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormNuevaPublicacionComponent } from './form-nueva-publicacion.component';

describe('FormNuevaPublicacionComponent', () => {
  let component: FormNuevaPublicacionComponent;
  let fixture: ComponentFixture<FormNuevaPublicacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormNuevaPublicacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormNuevaPublicacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
