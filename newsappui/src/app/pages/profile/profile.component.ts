import { Component } from '@angular/core';
import { User } from '../../models/User';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  constructor(private userservice:UserService){}
  ngOnInit():void {
    if(sessionStorage.getItem("activeid")){
      this.getUser(parseInt(sessionStorage.getItem("activeid") || "0"));
    }
  }
  user: User = new User();
  getUser(userid:number){
    console.log("called")
    this.userservice.getUser(userid).subscribe(
      (data:any)=>{
        this.user = data;
      },
      (error:any)=>{
        console.log(error);
      }
    )
  }
  update(){
    if(this.user.userid != null && this.user.password != null && this.user.email !=null && this.user != null){
      this.user.country
      this.userservice.updateUser(this.user).subscribe(
      (data)=>{
        alert("data updated succesfully")
      },
      (error)=>{
        console.log(error);
        alert(error.error);
      }
    );
    console.log(this.user);
    }
    else{
      alert("Please Enter All the Details")
    }
  }
}
