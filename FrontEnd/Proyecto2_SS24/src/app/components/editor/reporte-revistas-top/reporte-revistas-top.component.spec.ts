import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteRevistasTopComponent } from './reporte-revistas-top.component';

describe('ReporteRevistasTopComponent', () => {
  let component: ReporteRevistasTopComponent;
  let fixture: ComponentFixture<ReporteRevistasTopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteRevistasTopComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteRevistasTopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
