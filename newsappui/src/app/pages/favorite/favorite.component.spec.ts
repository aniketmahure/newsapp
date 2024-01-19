import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { By } from '@angular/platform-browser';
import { FooterComponent } from '../../components/footer/footer.component';
import { NavbarComponent } from '../../components/navbar/navbar.component';

import { FavoriteComponent } from './favorite.component';

describe('FavoriteComponent', () => {
  let component: FavoriteComponent;
  let fixture: ComponentFixture<FavoriteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FavoriteComponent,NavbarComponent,FooterComponent],
      imports:[HttpClientTestingModule,MatCardModule,
        MatButtonModule,
        MatIconModule,
        MatToolbarModule,]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FavoriteComponent);
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
    let footerobj=fixture.nativeElement.querySelector('.topcard');  //query(By.css('.topcard'));
    expect(footerobj).toBeDefined();
  });
  it('should have view all method to route', () => {
    spyOn(component,'getFavArticle').and.callThrough();
        expect(component.getFavArticle).toBeDefined()
   });
   it('should have view all method to route', () => {
    spyOn(component,'deleteFavArticle').and.callThrough();
        expect(component.deleteFavArticle).toBeDefined()
   });
});
