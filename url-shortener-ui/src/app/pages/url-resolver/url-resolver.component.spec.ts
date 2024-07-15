import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UrlResolverComponent } from './url-resolver.component';

describe('UrlResolverComponent', () => {
  let component: UrlResolverComponent;
  let fixture: ComponentFixture<UrlResolverComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UrlResolverComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UrlResolverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
