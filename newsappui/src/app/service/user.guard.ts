import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { UserService } from './user.service';

export const userGuard: CanActivateFn = (route, state) => {
  const userService: UserService = inject(UserService);
  const router:Router = inject(Router);
  if(userService.getStatus())
    return true;
  alert("Login Required");
  router.navigate(['/login']);
  return false;
};
