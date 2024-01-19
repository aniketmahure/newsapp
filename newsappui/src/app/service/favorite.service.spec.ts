import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { FavoriteService } from './favorite.service';
const favArticle = [
  {
    "favouriteId":1,
    "userid":1,
    "title":"abc",
    "author":"abc"
}
]
describe('FavoriteService', () => {
  let service: FavoriteService;
  let httpmock : HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientTestingModule
      ],
      providers:[
        FavoriteService
      ]

    });
    service = TestBed.inject(FavoriteService);
    httpmock = TestBed.inject(HttpTestingController);
  });
  afterEach( () => {
      httpmock.verify();
  }); 

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('addFavorite() method should add Favorite', () => {
 
    const fav  = 
    {
      "favouriteId":2,
      "userid":3,
      "title":"abc",
      "author":"abc"
  }
   // We call the service
   service.addFavorite(fav).subscribe( (res:any) => {
     expect(res.data.length).toBeGreaterThan(0);
     expect(res.data).toEqual(favArticle);
   });
   // We set the expectations for the HttpClient mock
   const req = httpmock.expectOne('http://localhost:7004/favorite/addFavorite/'+fav.userid);
   expect(req.request.method).toEqual('POST');
   // Then we set the fake data to be returned by the mock
   req.flush({data: favArticle});
   });

   it('deleteFavorite() method should delete favorite article', () => {
 
    const fav  = 
    {
      "favouriteId":2,
      "userid":3,
      "title":"abc",
      "author":"abc"
  }
   // We call the service
   service.deleteFavorite(fav.userid,fav.title).subscribe( (res:any) => {
     expect(res.data.length).toBeGreaterThan(0);
   });
   // We set the expectations for the HttpClient mock
   const req = httpmock.expectOne('http://localhost:7004/favorite/deleteFavorite/'+fav.userid+'/'+fav.title);
   expect(req.request.method).toEqual('GET');
   // Then we set the fake data to be returned by the mock
   req.flush({data: favArticle});
   });

   it('getFavorite() method should get favorite articles', () => {
    // We call the service
    service.getFavorite(1).subscribe( (res:any) => {
      expect(res.data.length).toBeGreaterThan(0);
      expect(res.data).toEqual(favArticle);
    });

    // We set the expectations for the HttpClient mock
    const req = httpmock.expectOne('http://localhost:7004/favorite/getFavorites/'+1);
    expect(req.request.method).toEqual('GET');
    // Then we set the fake data to be returned by the mock
    req.flush({data: favArticle});
    });
});
