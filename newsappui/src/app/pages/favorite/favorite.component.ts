import { Component } from '@angular/core';
import { Articles } from '../../models/Articles';
import { FavArticles } from '../../models/FavArticles';
import { FavoriteService } from '../../service/favorite.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrl: './favorite.component.css'
})
export class FavoriteComponent {
  constructor(private favService:FavoriteService){}
  ngOnInit(){
    if(sessionStorage.getItem("activeid")){
      this.getFavArticle(parseInt(sessionStorage.getItem("activeid") || "0"));
    }
  };
  public articles: FavArticles[]=[];
  public favArtice:FavArticles[]=[];
  public flag=false;
  getFavArticle(userid:number){
    this.favService.getFavorite(userid).subscribe(
      (data:any)=>{
        if(data.length === 0){
          this.articles = [];
          this.flag = true;
        }
        else{
          this.articles = data;
        }       
      },
      (error:any)=>{
        console.log("error occured in Fav")
        console.log(error);
      }
    );
  }
  deleteFavArticle(title :string){
    let userid = sessionStorage.getItem("activeid");
    console.log("title"+title.length);
    
    this.favService.deleteFavorite(userid,title).subscribe(
      (error:any)=>{
        console.log(error);
        this.ngOnInit();
      },
      (data:any)=>{
        Swal.fire({
          title: 'Deleted!',
          text: 'Data deleted from fav',
          icon: 'success'
        })
        this.ngOnInit();
      }
    )
  }
}
