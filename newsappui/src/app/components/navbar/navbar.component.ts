import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  constructor(private router:Router){}
  ngOnInit(){}
  login(){
    if(sessionStorage.getItem('status') == "active"){
      return true;
    }
    else{
      return false;
    }
  }
  logout(){
    sessionStorage.clear();
  }
}
