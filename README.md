# spring-efk


1. cd ENV  
2. docker-compose up -d  
Three containers are launched: fuentd + elasticsearch + kibana  
3. Wait 30-60 sec. and run the spring application.
4. Add a few quotas (API below), get qoutes and etc...
5. Go to the kibana address: _[http://localhost:5601](http://localhost:5601)_
6. Go to the tab "Discover" and create index
7. Co to the tab "Discover" and work with logs.  

To view console output:
`docker logs fluentd`

#
###API
#####Get all quotes:
**GET** _[http://localhost:8080/quotes](http://localhost:8080/quotes)_

#####Get quote by id:
**GET** _[http://localhost:8080/quotes/1/](http://localhost:8080/quotes/1)_

#####Add qoute:
**POST** _[http://localhost:8080/quotes](http://localhost:8080/quotes)_

body:  
{
    "id": 1,
    "type": "success",
    "value": {
        "id": 10,
        "quote": "Really loving Spring Boot, makes stand alone Spring apps easy."
    }
}
