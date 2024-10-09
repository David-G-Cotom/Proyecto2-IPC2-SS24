import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EdicionPreciosTipoAnuncioComponent } from './edicion-precios-tipo-anuncio.component';

describe('EdicionPreciosTipoAnuncioComponent', () => {
  let component: EdicionPreciosTipoAnuncioComponent;
  let fixture: ComponentFixture<EdicionPreciosTipoAnuncioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EdicionPreciosTipoAnuncioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EdicionPreciosTipoAnuncioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
