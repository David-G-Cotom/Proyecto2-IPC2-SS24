import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteRevistasComentadasComponent } from './reporte-revistas-comentadas.component';

describe('ReporteRevistasComentadasComponent', () => {
  let component: ReporteRevistasComentadasComponent;
  let fixture: ComponentFixture<ReporteRevistasComentadasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteRevistasComentadasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteRevistasComentadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
