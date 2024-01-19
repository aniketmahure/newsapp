import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { By } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FooterComponent } from '../../components/footer/footer.component';
import { NavbarComponent } from '../../components/navbar/navbar.component';

import { ProfileComponent } from './profile.component';

describe('ProfileComponent', () => {
  let component: ProfileComponent;
  let fixture: ComponentFixture<ProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProfileComponent,NavbarComponent,FooterComponent],
      imports:[HttpClientTestingModule,MatCardModule,
        MatButtonModule,
        MatIconModule,
        MatToolbarModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule,
        FormsModule,
        BrowserAnimationsModule
      ]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should check existence of topcard style',()=>{
    let footerobj=fixture.debugElement.query(By.css('.topcard'));
    expect(footerobj).toBeDefined();
  });
  it('should check existence of login style',()=>{
    let footerobj=fixture.debugElement.query(By.css('.login'));
    expect(footerobj).toBeDefined();
  });
  it('should check existence of all card style',()=>{
    let footerobj=fixture.debugElement.query(By.css('.cards'));
    expect(footerobj).toBeTruthy();
  });
  it('should have method to get user getUser()', () => {
    spyOn(component,'getUser').and.callThrough();
        expect(component.getUser).toBeDefined()
   });
   it('should have method to update user details update()', () => {
    spyOn(component,'update').and.callThrough();
        expect(component.update).toBeDefined()
   });
});
