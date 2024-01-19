import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { UserService } from './user.service';
const userDetails = [
  {
    "userid":3,
    "name": "user3",
    "password": "user3",
    "email": "user3@mail",
    "country": "india"
}
];
describe('UserService', () => {
  let service: UserService;
  let httpmock : HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientTestingModule
      ],
      providers:[
        UserService
      ]

    });
    service = TestBed.inject(UserService);
    httpmock = TestBed.inject(HttpTestingController);
  });
  afterEach( () => {
      httpmock.verify();
  }); 

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('addUser() method should add user', () => {
 
    const user  = 
    {
      "userid":3,
      "name": "user3",
      "password": "user123",
      "email": "user3@mail",
      "country": "india"
    }
   // We call the service
   service.addUser(user).subscribe( (res:any) => {
     expect(res.data.length).toBeGreaterThan(0);
     expect(res.data).toEqual(userDetails);
   });
   // We set the expectations for the HttpClient mock
   const req = httpmock.expectOne('http://localhost:7001/user/addUser');
   expect(req.request.method).toEqual('POST');
   // Then we set the fake data to be returned by the mock
   req.flush({data: userDetails});
   });

   it('updateUser() method should update user', () => {
 
    const user  = 
    {
      "userid":3,
      "name": "user3",
      "password": "user123",
      "email": "user3@mail",
      "country": "india"
    }
   // We call the service
   service.updateUser(user).subscribe( (res:any) => {
     expect(res.data.length).toBeGreaterThan(0);
     expect(res.data).toEqual(userDetails);
   });
   // We set the expectations for the HttpClient mock
   const req = httpmock.expectOne('http://localhost:7001/user/updateUser/'+user.userid);
   expect(req.request.method).toEqual('POST');
   // Then we set the fake data to be returned by the mock
   req.flush({data: userDetails});
   });

   it('getUser() method should get user details', () => {
    // We call the service
    service.getUser(3).subscribe( (res:any) => {
      expect(res.data.length).toBeGreaterThan(0);
      expect(res.data).toEqual(userDetails);
    });

    // We set the expectations for the HttpClient mock
    const req = httpmock.expectOne('http://localhost:7001/user/viewUserById/'+3);
    expect(req.request.method).toEqual('GET');
    // Then we set the fake data to be returned by the mock
    req.flush({data: userDetails});
    });



});
