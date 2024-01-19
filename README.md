Develop a News Application that allows users to search for news articles, read the news article in HTML format, and save news articles to favorites.
The application needs to fetch news article data by registering with the following API and get the API Key required to call the API.
https://newsapi.org/

# The responsibilities of the microservices in the above figure are as follows:

### User Profile Service: This Service is responsible for storing user registration details. The Service publishes the user credentials sent as part of registration to the message bus and stores the remaining user profile information in the database.
### Authentication Service: This Service is responsible for consuming user credentials from the message bus and storing them in the database. When a user logs in, this service validates the login credentials against the credentials stored in the database. If the credentials match, this service generates a JWT token and sends back as response, else an error message is sent.
### News Service: This Service is responsible for accessing an external News API to fetch news based on the search criteria coming in as a request and returning back the list of matching news as response.
### Wishlist Service: This Service is responsible for storing news articles bookmarked by users in the database.
### API Gateway: This Service acts as the entry point of the system. It intercepts all the requests and validates the JWT Token before routing it to the appropriate microservices.
### Eureka Server: This Service acts as a service registry where all the other microservices registers during startup for discoverability

<img width="605" alt="image" style="textalign:center;" src="https://github.com/aniketmahure/newsapp/assets/56632536/71b338b2-5120-42c5-b329-ae5aaa28c922">


> Ports that need to remember:
1. 7001 user service
2. 7002 auth service
3. 7003 App service
4. 7004 fav service
