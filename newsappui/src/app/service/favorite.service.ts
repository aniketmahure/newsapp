import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FavArticles } from '../models/FavArticles';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class FavoriteService {

  constructor(private http:HttpClient) { }
  public getFavorite(userid:number){
    return this.http.get<FavArticles>('http://ec2-3-135-244-38.us-east-2.compute.amazonaws.com:7004/favorite/getFavorites/'+userid);
  }
  public addFavorite(favArticels:FavArticles){
    return this.http.post('http://ec2-3-135-244-38.us-east-2.compute.amazonaws.com:7004/favorite/addFavorite/'+favArticels.userid,favArticels);
  }
  public deleteFavorite(userid:any,title:any){
    let fav = new FavArticles();
    fav.userid = userid;
    fav.title = title;
    return this.http.post('http://ec2-3-135-244-38.us-east-2.compute.amazonaws.com:7004/favorite/deleteFavorite/'+userid,fav);
  }
}
