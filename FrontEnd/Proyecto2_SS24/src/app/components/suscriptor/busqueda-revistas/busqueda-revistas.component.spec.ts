import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusquedaRevistasComponent } from './busqueda-revistas.component';

describe('BusquedaRevistasComponent', () => {
  let component: BusquedaRevistasComponent;
  let fixture: ComponentFixture<BusquedaRevistasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BusquedaRevistasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BusquedaRevistasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
