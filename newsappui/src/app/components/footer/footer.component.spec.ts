import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatIconModule } from '@angular/material/icon';
import { By } from '@angular/platform-browser';

import { FooterComponent } from './footer.component';

describe('FooterComponent', () => {
  let component: FooterComponent;
  let fixture: ComponentFixture<FooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FooterComponent],
      imports:[MatIconModule]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should check existence of footer style',()=>{
    let footerobj=fixture.debugElement.query(By.css('.footer'));
    expect(footerobj).toBeTruthy();
  });
  it('should check  h3 with some content',()=>{
  let headobj=fixture.debugElement.query(By.css('.footer'));
  expect(headobj.nativeElement.textContent).toEqual('About UsThis is News application to View News Articles.Stay Updated with the latest newsContact usReach out to us with newsApp@mail.comshare')
  });
});