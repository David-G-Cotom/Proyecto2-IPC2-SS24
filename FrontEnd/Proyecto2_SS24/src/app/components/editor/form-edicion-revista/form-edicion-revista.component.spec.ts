import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormEdicionRevistaComponent } from './form-edicion-revista.component';

describe('FormEdicionRevistaComponent', () => {
  let component: FormEdicionRevistaComponent;
  let fixture: ComponentFixture<FormEdicionRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormEdicionRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormEdicionRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
