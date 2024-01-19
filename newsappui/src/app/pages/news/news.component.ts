import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Articles } from '../../models/Articles';
import { FavArticles } from '../../models/FavArticles';
import { FavoriteService } from '../../service/favorite.service';
import { NewsService } from '../../service/news.service';
import Swal from 'sweetalert2'
@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrl: './news.component.css'
})
export class NewsComponent {
  constructor(private newsService:NewsService,private favService:FavoriteService,private router:Router){
    this.country
  }
  ngOnInit(){
    this.getArticle();
  };
  public articles: Articles[]=[];
  public favArticles : FavArticles[]= [];
  public country:string='';
  public addFavByUserid:number = 0;
  public myMap = new Map<string, string>([
      ["india", "in"],
      ["us", "us"],
      ["uae","ae"],
      ["argentina","ar"],
      ["mexico","mx"],
      ["arab","sa"],
      ["italy","it"],
  ]);
  getArticle(){
    // var art:Articles[];
    // let countrySearch="";
    // let storedCountry = localStorage.setItem("country","us")
    // if(this.country !== ""){
    //   console.log("--")
    //   console.log(this.country);
    // }
    this.newsService.viewAllArticles("us").subscribe(
      (data:any)=>{
        console.log("data retrived 3rd party")
        // this.articles = data.articles;      
        // this.articles.push(data);
        this.articles = data;
        console.log(this.articles);
        
      },
      (error:any)=>{
        console.log(error)
      }
    );
  }
  getSearched(){
    let search = this.myMap.get(this.country) || "";
    if(search !== "" && search != undefined && search.length !=0){
      console.log(search+" is searched");
      this.newsService.viewAllArticles(search).subscribe(
        (data:any)=>{
          console.log(data);
          console.log("data retrived after search")
          this.articles = data;
        },
        (error:any)=>{
          console.log(error);
          Swal.fire({
            title: 'Error!',
            text: 'Data Not Available for given Country',
            icon: 'error'
          })
        }
      );
    }
    else{
      Swal.fire({
        title: 'Invalid!',
        text: 'Enter Country To search',
        icon: 'info'
      })
    }
  }
  addToFavorite(favouriteId:number,author :string,title :string){
    // let articleidParse:number = parseInt(articleId);
    //let useridParse :number = parseInt(userid);
    let userid = sessionStorage.getItem("activeid");
    if(userid != null && userid != undefined){
      this.favArticles.push({"favouriteId":favouriteId,"userid":userid,"author":author,"title":title});
      this.favService.addFavorite(this.favArticles[0]).subscribe(
        (data:any)=>{
          console.log("data in fav");
          console.log(data);
          Swal.fire({
            title: 'Added!',
            text: 'Data added to fav',
            icon: 'success'
          })
          this.favArticles = [];
        },(error)=>{
          console.log("error occured in fav");
          Swal.fire({
            title: 'Error!',
            text: 'Cannot added to fav',
            icon: 'error'
          })
        }
      )
    }
  }
  login(){
    if(sessionStorage.getItem('status') == "active"){
      return true;
    }
    else{
      return false;
    }
  }
}
