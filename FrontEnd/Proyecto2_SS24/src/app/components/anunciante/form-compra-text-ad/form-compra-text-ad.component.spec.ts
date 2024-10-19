import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormCompraTextAdComponent } from './form-compra-text-ad.component';

describe('FormCompraTextAdComponent', () => {
  let component: FormCompraTextAdComponent;
  let fixture: ComponentFixture<FormCompraTextAdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormCompraTextAdComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormCompraTextAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
