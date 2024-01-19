import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthUser } from '../../models/AuthUser';
import { AuthService } from '../../service/auth.service';
import Swal from 'sweetalert2'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private authservice:AuthService,private router:Router){}
  ngOnInit():void {}
  user:AuthUser = new AuthUser();
  login(){
    if(this.user.userId != null && this.user.password != ""&& this.user.password != null && this.user != undefined){
      this.authservice.login(this.user).subscribe(
      (data:any)=>{
        console.log("login successfully")
        sessionStorage.setItem("activeid",this.user.userId+"");
        sessionStorage.setItem("token",data.token)
        sessionStorage.setItem("status","active");
        Swal.fire({
          title: 'Succesfull!',
          text: 'Login Successfull',
          icon: 'success'
        })
        this.router.navigate(["/viewAll"])
      },
      (error)=>{
        console.log(this.user)
        console.log("invalid userid or password");
        console.log(error);
        Swal.fire({
          title: 'Error!',
          text: 'Invalid userid or password',
          icon: 'warning'
        })
      });
    }
    else{
      console.log("please enter credentials");
      Swal.fire({
        title: 'Error!',
        text: 'Please Enter Credentials',
        icon: 'error'
      })
    }
  }
}
