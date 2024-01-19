import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  public addUser(user : User){
    return this.http.post('http://ec2-18-117-132-91.us-east-2.compute.amazonaws.com:7001/user/addUser',user);
  }
  public updateUser(user:User){
    return this.http.post('http://ec2-18-117-132-91.us-east-2.compute.amazonaws.com:7001/user/updateUser/'+user.userid,user);
  }
  public getUser(userid : number){
    return this.http.get('http://ec2-18-117-132-91.us-east-2.compute.amazonaws.com:7001/user/viewUserById/'+userid);
  }
  public getToken(){
    return sessionStorage.getItem("token");
  }
  public getActiveId(){
    return sessionStorage.getItem("activeid");
  }
  public getStatus(){
    if(sessionStorage.getItem("status") === "active"){
      return true;
    }
    else{
      return false;
    }
  }
}
