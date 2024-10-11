import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormRecargaCreditoComponent } from './form-recarga-credito.component';

describe('FormRecargaCreditoComponent', () => {
  let component: FormRecargaCreditoComponent;
  let fixture: ComponentFixture<FormRecargaCreditoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormRecargaCreditoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormRecargaCreditoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
