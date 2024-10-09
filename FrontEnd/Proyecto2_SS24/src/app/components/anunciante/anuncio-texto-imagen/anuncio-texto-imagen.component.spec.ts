import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnuncioTextoImagenComponent } from './anuncio-texto-imagen.component';

describe('AnuncioTextoImagenComponent', () => {
  let component: AnuncioTextoImagenComponent;
  let fixture: ComponentFixture<AnuncioTextoImagenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnuncioTextoImagenComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnuncioTextoImagenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
