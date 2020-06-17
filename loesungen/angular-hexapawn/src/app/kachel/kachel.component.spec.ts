import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KachelComponent } from './kachel.component';

describe('KachelComponent', () => {
  let component: KachelComponent;
  let fixture: ComponentFixture<KachelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [KachelComponent],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KachelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
