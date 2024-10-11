import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EdicionPreciosRevistaComponent } from './edicion-precios-revista.component';

describe('EdicionPreciosRevistaComponent', () => {
  let component: EdicionPreciosRevistaComponent;
  let fixture: ComponentFixture<EdicionPreciosRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EdicionPreciosRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EdicionPreciosRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
