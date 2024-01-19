import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Observable } from "rxjs";
import {Injectable} from "@angular/core"
import { UserService } from "./user.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{
    constructor(private userService: UserService){}
    intercept(
        req: HttpRequest<any>, 
        next: HttpHandler
        ): Observable<HttpEvent<any>> {
        //add JWT Token
        let authReq = req;
        const token=this.userService.getToken();
        
        if(token!=null){
            console.log('Inside Interceptor with Token');
            authReq = authReq.clone({
                setHeaders : {Authorization : 'Bearer '+token},
            });
        }
        return next.handle(authReq);
    }
    
}
//configurations
export const authInterceptorProvider=[
    {
        provide : HTTP_INTERCEPTORS,//constant
        useClass : AuthInterceptor,//useClass is a key
        multi : true,
    }
];