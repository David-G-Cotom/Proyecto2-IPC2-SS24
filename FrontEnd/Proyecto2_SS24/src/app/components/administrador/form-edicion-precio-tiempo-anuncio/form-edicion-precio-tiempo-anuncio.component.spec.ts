import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormEdicionPrecioTiempoAnuncioComponent } from './form-edicion-precio-tiempo-anuncio.component';

describe('FormEdicionPrecioTiempoAnuncioComponent', () => {
  let component: FormEdicionPrecioTiempoAnuncioComponent;
  let fixture: ComponentFixture<FormEdicionPrecioTiempoAnuncioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormEdicionPrecioTiempoAnuncioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormEdicionPrecioTiempoAnuncioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
