import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormEdicionPrecioTipoAnuncioComponent } from './form-edicion-precio-tipo-anuncio.component';

describe('FormEdicionPrecioTipoAnuncioComponent', () => {
  let component: FormEdicionPrecioTipoAnuncioComponent;
  let fixture: ComponentFixture<FormEdicionPrecioTipoAnuncioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormEdicionPrecioTipoAnuncioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormEdicionPrecioTipoAnuncioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
