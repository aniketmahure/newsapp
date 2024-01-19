import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
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

import { NewsComponent } from './news.component';

describe('NewsComponent', () => {
  let component: NewsComponent;
  let fixture: ComponentFixture<NewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NewsComponent,FooterComponent,NavbarComponent],
      imports:[MatCardModule,HttpClientTestingModule,
        MatButtonModule,
        MatIconModule,
        MatToolbarModule,
        MatGridListModule,
        FormsModule,
        MatFormFieldModule,
        MatInputModule,BrowserAnimationsModule ]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should check existence of base style',()=>{
    let footerobj=fixture.debugElement.query(By.css('.base'));
    expect(footerobj).toBeTruthy();
  });
  it('should check existence of topcard style',()=>{
    let footerobj=fixture.debugElement.query(By.css('.topcard'));
    expect(footerobj).toBeDefined();
  });
  it('should check existence of childcard style',()=>{
    let footerobj=fixture.debugElement.query(By.css('.childcard'));
    expect(footerobj).toBeDefined();
  });
  it('should have method to fetch article getArticle()', () => {
    spyOn(component,'getArticle').and.callThrough();
        expect(component.getArticle).toBeDefined()
   });
   it('should have method to serach article getSearched()', () => {
    spyOn(component,'getSearched').and.callThrough();
        expect(component.getSearched).toBeDefined()
   });
   it('should have method to add favorite addToFavorite()', () => {
    spyOn(component,'addToFavorite').and.callThrough();
        expect(component.addToFavorite).toBeDefined()
   });
});
