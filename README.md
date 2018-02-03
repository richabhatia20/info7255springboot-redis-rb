# Project for INFO 7255
Spring Boot with Redis datastore

## Installing Redis
* Install redis with homebrew
```
$ brew install redis
```

* Start redis server
```
$ redis-server /usr/local/etc/redis.conf
```
* Verify server is running
```
$ redis-cli ping
```
The server will reply with PONG

## Dependencies in POM.XML
* Web
* Jersey (JAX-RS)
* JPA
* Redis (Using Jedis)
* Cloud connectors
* Cloud Foundery discovery

## Various APIs
* http://localhost:8080/persons
```
[{
	"firstName": "Bob",
	"lastName": "Evans",
	"emailAddress": "bobevans@someplace.com"
}, {
	"firstName": "John",
	"lastName": "Doe",
	"emailAddress": "johndoe@nowhere.com"
}, {
	"firstName": "Jane",
	"lastName": "Smith",
	"emailAddress": "jane@somewhere.com"
}]
```

* Validating other requests through curl

