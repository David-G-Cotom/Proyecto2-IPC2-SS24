import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContentAnuncianteComponent } from './content-anunciante.component';

describe('ContentAnuncianteComponent', () => {
  let component: ContentAnuncianteComponent;
  let fixture: ComponentFixture<ContentAnuncianteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContentAnuncianteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContentAnuncianteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
