import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FavoriteComponent } from './pages/favorite/favorite.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { NewsComponent } from './pages/news/news.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { RegisterComponent } from './pages/register/register.component';
import { userGuard } from './service/user.guard';

const routes: Routes = [
    {
        path : '',
        redirectTo:'/home',
        pathMatch:'full'
    },
    {
        path : 'home',
        component:HomeComponent,
        pathMatch:'full'
    },
    {
        path : 'login',
        component:LoginComponent,
        pathMatch:'full'
    },
    {
        path : 'register',
        component:RegisterComponent,
        pathMatch:'full'
    },
    {
        path : 'viewAll',
        component:NewsComponent,
        pathMatch:'full'
    },
    {
        path : 'favorite',
        component:FavoriteComponent,
        pathMatch:'full',
        canActivate:[userGuard]
    },
    {
        path:'profile',
        component:ProfileComponent,
        pathMatch:'full',
        canActivate:[userGuard]
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
