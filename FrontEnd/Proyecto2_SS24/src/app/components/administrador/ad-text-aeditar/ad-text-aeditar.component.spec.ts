import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdTextAEditarComponent } from './ad-text-aeditar.component';

describe('AdTextAEditarComponent', () => {
  let component: AdTextAEditarComponent;
  let fixture: ComponentFixture<AdTextAEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdTextAEditarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdTextAEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
