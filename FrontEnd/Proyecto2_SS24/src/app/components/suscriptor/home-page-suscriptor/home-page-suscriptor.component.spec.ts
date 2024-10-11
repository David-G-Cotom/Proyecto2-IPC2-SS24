import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePageSuscriptorComponent } from './home-page-suscriptor.component';

describe('HomePageSuscriptorComponent', () => {
  let component: HomePageSuscriptorComponent;
  let fixture: ComponentFixture<HomePageSuscriptorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomePageSuscriptorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomePageSuscriptorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
