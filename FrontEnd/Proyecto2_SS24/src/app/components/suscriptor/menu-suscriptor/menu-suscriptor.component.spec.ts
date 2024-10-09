import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuSuscriptorComponent } from './menu-suscriptor.component';

describe('MenuSuscriptorComponent', () => {
  let component: MenuSuscriptorComponent;
  let fixture: ComponentFixture<MenuSuscriptorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MenuSuscriptorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuSuscriptorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
