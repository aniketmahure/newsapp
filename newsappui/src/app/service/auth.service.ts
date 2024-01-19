import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthUser } from '../models/AuthUser';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { 
    console.log("service called")
  }
  public login(authUser: AuthUser){
    authUser.role = null;
    return this.http.post('http://ec2-18-117-132-91.us-east-2.compute.amazonaws.com:7002/auth/login',authUser)
  }
}
