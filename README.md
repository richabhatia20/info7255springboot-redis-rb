# info7255springboot-redis-rb
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
