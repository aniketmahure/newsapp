import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule,HttpTestingController}  from '@angular/common/http/testing'
import { NewsService } from './news.service';
import { Articles } from '../models/Articles';

const articles=[
  {
      "author": "Jesse Sarles",
      "title": "Elijah McClain death: Paramedics who injected Colorado Black man with ketamine found guilty - CBS News",
      "description": "A Colorado jury on Friday found two paramedics guilty of criminally negligent homicide in the death of Elijah McClain near his home in Aurora.",
      "url": "https://www.cbsnews.com/colorado/news/paramedics-elijah-mcclain-death-ketamine-colorado-jury-ruling/",
      "publishedAt": "2023-12-23T05:56:00Z",
  }];
describe('NewsService', () => {
  let service: NewsService;
  let httpmock : HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientTestingModule
      ],
      providers:[
        NewsService
      ]

    });
    service = TestBed.inject(NewsService);
    httpmock = TestBed.inject(HttpTestingController);
  });

  afterEach( () => {
      httpmock.verify();
  });  

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('viewAllArticles() method should return articles', () => {
   // We call the service
   service.viewAllArticles("us").subscribe( (res:any) => {
     expect(res.data.length).toBeGreaterThan(0);
   });
    // We set the expectations for the HttpClient mock
    const req = httpmock.expectOne('http://localhost:7003/news/viewAll/us');
    expect(req.request.method).toEqual('GET');
    // console.log(req.request)
    //expect(req.request.headers.get('Content-Type')).toEqual('application/json');
    // Then we set the fake data to be returned by the mock
    req.flush({data: articles});
    });

});
