import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Articles } from '../models/Articles';
import {Collection} from '../models/Collection';
@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http:HttpClient) { }
  public viewAllArticles(country: string){
    return this.http.get<Articles[]>('http://ec2-18-190-28-194.us-east-2.compute.amazonaws.com:7003/news/viewAll/'+country);
  }
}
