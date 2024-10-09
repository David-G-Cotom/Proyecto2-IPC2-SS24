import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContentSuscriptorComponent } from './content-suscriptor.component';

describe('ContentSuscriptorComponent', () => {
  let component: ContentSuscriptorComponent;
  let fixture: ComponentFixture<ContentSuscriptorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContentSuscriptorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContentSuscriptorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
