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
* POST http://localhost:8080/insuranceplans
```
{
	"creationDate": "20-08-1991",
	"org": "myOrg1",
	"objectId": "richa",
	"objectType": "plan",
	"planType": "inNetwork",
	"planCostShares": {
		"deductible": "454",
		"org": "myOrg1",
		"copay": "54",
		"objectId": "richa1",
		"objectType": "abcPlanCostShares"
	},
	"linkedPlanServicesList": [{
		"linkedService": {
			"org": "myOrg1",
			"objectId": "richa12",
			"objectType": "service",
			"name": "Yearly physical"
		},
		"planServiceCostShares": {
			"deductible": 10,
			"org": "richa3",
			"copay": "175",
			"objectId": "richa13",
			"objectType": "membercostshare"
		},
		"org": "myOrg1",
		"objectId": "richa14",
		"objectType": "planservice"
	}]
}
```

* PUT http://localhost:8080/insuranceplans

```
$ curl -H "Content-Type: application/json" -X PUT  -d '{
	"creationDate": "03-10-1994",
	"org": "myOrg3",
	"objectId": "sheenam",
	"objectType": "plan",
	"planType": "outNetwork",
	"planCostShares": {
		"deductible": "454",
		"org": "myOrg3",
		"copay": "54",
		"objectId": "sheenamb",
		"objectType": "abcPlanCostShares"
	},
	"linkedPlanServicesList": [{
		"linkedService": {
			"org": "myOrg3",
			"objectId": "sheenambh",
			"objectType": "service",
			"name": "Yearly physical"
		},
		"planServiceCostShares": {
			"deductible": 10,
			"org": "myOrg3",
			"copay": 175,
			"objectId": "sheenambh1",
			"objectType": "membercostshare"
		},
		"org": "myOrg3",
		"objectId": "sheenambh12",
		"objectType": "planservice"
	}]
}' http://localhost:8080/insuranceplans
{'message':'Plan saved successfully'}
```

```
$ redis-cli hgetall plans
1) "outNetwork"
2) "{\"creationDate\":\"03-10-1994\",\"org\":\"myOrg3\",\"objectId\":\"sheenam-1ef561d9-7c4e-4f9c-984c-053ac2399aee\",\"objectType\":\"plan\",\"planType\":\"outNetwork\",\"planCostShares\":{\"deductible\":\"454\",\"org\":\"myOrg3\",\"copay\":\"54\",\"objectId\":\"sheenamb-401695a4-2519-4c03-a9dd-11574ce67388\",\"objectType\":\"abcPlanCostShares\"},\"linkedPlanServicesList\":[{\"linkedService\":{\"org\":\"myOrg3\",\"objectId\":\"sheenambh\",\"objectType\":\"service\",\"name\":\"Yearly physical\"},\"planServiceCostShares\":{\"deductible\":\"10\",\"org\":\"myOrg3\",\"copay\":\"175\",\"objectId\":\"sheenambh1\",\"objectType\":\"membercostshare\"},\"org\":\"myOrg3\",\"objectId\":\"sheenambh12\",\"objectType\":\"planservice\"}]}"
```

* GET http://localhost:8080/insuranceplans

```
$ curl -H "Content-Type: application/json" -X GET http://localhost:8080/insuranceplans
```
response:
```
[
    {
        "creationDate": "03-10-1994",
        "org": "myOrg3",
        "objectId": "sheenam-aaff665e-6b8b-45da-8d41-0aa6aaecf048",
        "objectType": "plan",
        "planType": "outNetwork",
        "planCostShares": {
            "deductible": "454",
            "org": "myOrg3",
            "copay": "54",
            "objectId": "sheenamb-4793c82c-af3d-4277-82cc-cc29fc104cfe",
            "objectType": "abcPlanCostShares"
        },
        "linkedPlanServicesList": [
            {
                "linkedService": {
                    "org": "myOrg3",
                    "objectId": "sheenambh",
                    "objectType": "service",
                    "name": "Yearly physical"
                },
                "planServiceCostShares": {
                    "deductible": "10",
                    "org": "myOrg3",
                    "copay": "175",
                    "objectId": "sheenambh1",
                    "objectType": "membercostshare"
                },
                "org": "myOrg3",
                "objectId": "sheenambh12",
                "objectType": "planservice"
            }
        ]
    },
    {
        "creationDate": "20-08-1991",
        "org": "myOrg1",
        "objectId": "richa-06930869-abf1-41c2-a8f6-fb2bfdee45be",
        "objectType": "plan",
        "planType": "inNetwork",
        "planCostShares": {
            "deductible": "454",
            "org": "myOrg1",
            "copay": "54",
            "objectId": "richa1-d2519289-dd97-4127-895f-a8828fb5259a",
            "objectType": "abcPlanCostShares"
        },
        "linkedPlanServicesList": [
            {
                "linkedService": {
                    "org": "myOrg1",
                    "objectId": "richa12",
                    "objectType": "service",
                    "name": "Yearly physical"
                },
                "planServiceCostShares": {
                    "deductible": "10",
                    "org": "richa3",
                    "copay": "175",
                    "objectId": "richa13",
                    "objectType": "membercostshare"
                },
                "org": "myOrg1",
                "objectId": "richa14",
                "objectType": "planservice"
            }
        ]
    }
]
```

* GET BASED ON TYPE http://localhost:8080/insuranceplans/inNetwork

response:
```
{
    "creationDate": "20-08-1991",
    "org": "myOrg1",
    "objectId": "richa-06930869-abf1-41c2-a8f6-fb2bfdee45be",
    "objectType": "plan",
    "planType": "inNetwork",
    "planCostShares": {
        "deductible": "454",
        "org": "myOrg1",
        "copay": "54",
        "objectId": "richa1-d2519289-dd97-4127-895f-a8828fb5259a",
        "objectType": "abcPlanCostShares"
    },
    "linkedPlanServicesList": [
        {
            "linkedService": {
                "org": "myOrg1",
                "objectId": "richa12",
                "objectType": "service",
                "name": "Yearly physical"
            },
            "planServiceCostShares": {
                "deductible": "10",
                "org": "richa3",
                "copay": "175",
                "objectId": "richa13",
                "objectType": "membercostshare"
            },
            "org": "myOrg1",
            "objectId": "richa14",
            "objectType": "planservice"
        }
    ]
}
```


* DELETE

```
$ curl -H "Content-Type: application/json" -X DELETE http://localhost:8080/insuranceplans/outP2Department
```
