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

## Validating other requests through curl
* PUT

```
$ curl -H "Content-Type: application/json" -X PUT  -d '{"firstName":"Richa","lastName":"Bhatia","emailAddress":"richa.bhatia@gmail.com"}' http://localhost:8080/persons
{"firstName":"Richa","lastName":"Bhatia","emailAddress":"richa.bhatia@gmail.com"}
```

```
$ redis-cli hgetall persons
 1) "bobevans@someplace.com"
 2) "{\"firstName\":\"Bob\",\"lastName\":\"Evans\",\"emailAddress\":\"bobevans@someplace.com\"}"
 3) "johndoe@nowhere.com"
 4) "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"emailAddress\":\"johndoe@nowhere.com\"}"
 5) "myname@example.com"
 6) "{\"firstName\":\"My\",\"lastName\":\"Name\",\"emailAddress\":\"myname@example.com\"}"
 7) "jane@somewhere.com"
 8) "{\"firstName\":\"Jane\",\"lastName\":\"Smith\",\"emailAddress\":\"jane@somewhere.com\"}"
 9) "richa.bhatia@gmail.com"
10) "{\"firstName\":\"Richa\",\"lastName\":\"Bhatia\",\"emailAddress\":\"richa.bhatia@gmail.com\"}"
```

* GET 

```
$ curl -H "Content-Type: application/json" -X GET http://localhost:8080/persons/richa.bhatia%40gmail.com
{"firstName":"Richa","lastName":"Bhatia","emailAddress":"richa.bhatia@gmail.com"}
```

* UPDATE VIA POST

```$ curl -H "Content-Type: application/json" -X POST  -d '{"firstName":"Richa","lastName":"Bhatia","emailAddress":"richa.bhatia91@gmail.com"}' http://localhost:8080/persons
```
response:
```
{"firstName":"Richa","lastName":"Bhatia","emailAddress":"richa.bhatia91@gmail.com"}
```

```
$ redis-cli hgetall persons
 1) "johndoe@nowhere.com"
 2) "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"emailAddress\":\"johndoe@nowhere.com\"}"
 3) "myname@example.com"
 4) "{\"firstName\":\"My\",\"lastName\":\"Name\",\"emailAddress\":\"myname@example.com\"}"
 5) "richa.bhatia91@gmail.com"
 6) "{\"firstName\":\"Richa\",\"lastName\":\"Bhatia\",\"emailAddress\":\"richa.bhatia91@gmail.com\"}"
 7) "bobevans@someplace.com"
 8) "{\"firstName\":\"Bob\",\"lastName\":\"Evans\",\"emailAddress\":\"bobevans@someplace.com\"}"
 9) "richa.bhatia@gmail.com"
10) "{\"firstName\":\"Richa\",\"lastName\":\"Bhatia\",\"emailAddress\":\"richa.bhatia@gmail.com\"}"
11) "jane@somewhere.com"
12) "{\"firstName\":\"Jane\",\"lastName\":\"Smith\",\"emailAddress\":\"jane@somewhere.com\"}"
```

* DELETE

```
$ curl -H "Content-Type: application/json" -X DELETE http://localhost:8080/persons/richa.bhatia%40gmail.com
```

```
$ redis-cli hgetall persons
 1) "johndoe@nowhere.com"
 2) "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"emailAddress\":\"johndoe@nowhere.com\"}"
 3) "myname@example.com"
 4) "{\"firstName\":\"My\",\"lastName\":\"Name\",\"emailAddress\":\"myname@example.com\"}"
 5) "richa.bhatia91@gmail.com"
 6) "{\"firstName\":\"Richa\",\"lastName\":\"Bhatia\",\"emailAddress\":\"richa.bhatia91@gmail.com\"}"
 7) "bobevans@someplace.com"
 8) "{\"firstName\":\"Bob\",\"lastName\":\"Evans\",\"emailAddress\":\"bobevans@someplace.com\"}"
 9) "jane@somewhere.com"
10) "{\"firstName\":\"Jane\",\"lastName\":\"Smith\",\"emailAddress\":\"jane@somewhere.com\"}"
```