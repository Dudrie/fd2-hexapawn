import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrettComponent } from './brett.component';

describe('BrettComponent', () => {
  let component: BrettComponent;
  let fixture: ComponentFixture<BrettComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BrettComponent],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrettComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
