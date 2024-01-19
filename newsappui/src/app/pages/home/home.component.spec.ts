import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { By } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FooterComponent } from '../../components/footer/footer.component';
import { NavbarComponent } from '../../components/navbar/navbar.component';

import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomeComponent,NavbarComponent,FooterComponent],
      imports:[HttpClientTestingModule,MatCardModule,
        MatButtonModule,
        MatIconModule,
        MatToolbarModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule,
        FormsModule,
        BrowserAnimationsModule,
        MatGridListModule]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should check existence of top div style',()=>{
    let footerobj=fixture.debugElement.query(By.css('.topdiv'));
    expect(footerobj).toBeTruthy();
  });
  it('should check existence of bottomcard style',()=>{
    let footerobj=fixture.debugElement.query(By.css('.bottomcard'));
    expect(footerobj).toBeTruthy();
  });
  it('should check existence of all news style',()=>{
    let footerobj=fixture.debugElement.query(By.css('.allnews'));
    expect(footerobj).toBeTruthy();
  });
  it('should have login method to route', () => {
    spyOn(component,'login').and.callThrough();
        component.login();
        expect(component.login).toHaveBeenCalled()
   });
   it('should have register to route', () => {
    spyOn(component,'register').and.callThrough();
        component.register();
        expect(component.register).toBeDefined();
   });
   it('should have view all method to route', () => {
    spyOn(component,'viewArticles').and.callThrough();
        component.viewArticles();
        expect(component.viewArticles).toHaveBeenCalled()
   });
});
