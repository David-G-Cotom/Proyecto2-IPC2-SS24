import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnuncioLateralComponent } from './anuncio-lateral.component';

describe('AnuncioLateralComponent', () => {
  let component: AnuncioLateralComponent;
  let fixture: ComponentFixture<AnuncioLateralComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnuncioLateralComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnuncioLateralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
