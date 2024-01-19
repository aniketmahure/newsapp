import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { AuthService } from './auth.service';
const authUser  = 
    [{
      "userId":3,
      "password": "user123",
      "role":' ',
    }];
describe('AuthService', () => {
  let service: AuthService;
  let httpmock : HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientTestingModule
      ],
      providers:[
        AuthService
      ]
    });
    service = TestBed.inject(AuthService);
    httpmock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('login() method should login user return token', () => {
 
    const user  = 
    {
      "userId":3,
      "password": "user123",
      "role":' ',
    };
   // We call the service
   service.login(user).subscribe( (res:any) => {
    console.log("user "+user);
     console.log(res)
    //  expect(res.data.length).toContain();
   });
   // We set the expectations for the HttpClient mock
   const req = httpmock.expectOne('http://localhost:7002/auth/login');
   expect(req.request.method).toEqual('POST');
   // Then we set the fake data to be returned by the mock
   req.flush({data: authUser});
   });
});
