import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EdicionPreciosTiempoAnuncioComponent } from './edicion-precios-tiempo-anuncio.component';

describe('EdicionPreciosTiempoAnuncioComponent', () => {
  let component: EdicionPreciosTiempoAnuncioComponent;
  let fixture: ComponentFixture<EdicionPreciosTiempoAnuncioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EdicionPreciosTiempoAnuncioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EdicionPreciosTiempoAnuncioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
