import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../models/User';
import { UserService } from '../../service/user.service';
import Swal from 'sweetalert2'
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  constructor(private userservice:UserService,private router:Router){}
  ngOnInit():void {}
  user: User = new User();
  register(){
    if(this.user.userid != null && this.user.password != null && this.user.email !=null && this.user != null){
      this.user.country
      this.userservice.addUser(this.user).subscribe(
      (data)=>{
        console.log("User registered");
        Swal.fire({
          title: 'Added!',
          text: 'User registered Succesfully',
          icon: 'success'
        })
        this.router.navigate(['/login']);
      },
      (error)=>{
        console.log(error);
        Swal.fire({
          title: 'Error Occured!',
          text: 'Cannot Register User, Try with Different Id',
          icon: 'warning'
        })
      }
    );
    }
    else{
      Swal.fire({
        title: 'Invalid!',
        text: 'Please Enter All the Details',
        icon: 'info'
      })
    }
  }
}
