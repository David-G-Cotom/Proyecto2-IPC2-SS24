import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnunciosRegistradosComponent } from './anuncios-registrados.component';

describe('AnunciosRegistradosComponent', () => {
  let component: AnunciosRegistradosComponent;
  let fixture: ComponentFixture<AnunciosRegistradosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnunciosRegistradosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnunciosRegistradosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
