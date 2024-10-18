import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevistasNoSuscritasComponent } from './revistas-no-suscritas.component';

describe('RevistasNoSuscritasComponent', () => {
  let component: RevistasNoSuscritasComponent;
  let fixture: ComponentFixture<RevistasNoSuscritasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RevistasNoSuscritasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RevistasNoSuscritasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
