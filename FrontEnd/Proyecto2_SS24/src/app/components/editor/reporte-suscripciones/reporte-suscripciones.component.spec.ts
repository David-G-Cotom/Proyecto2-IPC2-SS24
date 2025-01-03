import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteSuscripcionesComponent } from './reporte-suscripciones.component';

describe('ReporteSuscripcionesComponent', () => {
  let component: ReporteSuscripcionesComponent;
  let fixture: ComponentFixture<ReporteSuscripcionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteSuscripcionesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteSuscripcionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
