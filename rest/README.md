# Readme

Instructions for running and deploying to iot-platform.

## Local development

### SpringBoot REST server

* H2 in relational database
* Neo4j Graph database

#### How to start

* Firstly start Neo4j database using docker compose
* Position your self at the root of project and type:
  
```sh
docker-compose -f docker-compose-neo4j.yml up
```

* Accessing neo4j database using browser `http://localhost:7474/browser/`
* No authorization for developing purpose

## Neo4j cypher queris - USED
```
// Get scenes for specific tag
OPTIONAL MATCH (parent:Tag {name:"fer"})-[:IS_OPERATING*]->(child:Tag)-[:IS_MONITORING]->(scene:Scene)
OPTIONAL MATCH (parent2:Tag {name:"fer"})-[:IS_MONITORING]->(novo)
RETURN scene,novo
```

```
//Get tag hierarchy for specific tag name
MATCH (parent:Tag {name: "fer"})-[*]->(child:Tag)
RETURN parent, child
```





## Java Clases
* Implementation of nodes, services, controllers inside ../graph packages inside src

## Hibride option
### Relational databse
* is used to store data about scenes, views etc.
* a relational database is used to store data about scenes, views, and other relevant information. This allows for structured storage and efficient querying of data based on predefined schemas.

### graph database 
* is used to store hierarchy between tags objects used inside scenes
* a graph database is utilized to store the hierarchical relationships between tag objects used within scenes. Graph databases excel at representing complex relationships, making them ideal for managing hierarchical data

## REST

### Get availabe tags
```
http://localhost:8080/api/tags/names
```
```
[
    "sensor",
    "actuator",
    "admin",
    "ferit",
    "podravka",
    "fer",
    "fer_vrt",
    "kukuruz",
    "fao200",
    "fao400",
    "fortenova",
    "tvrtka1",
    "senzori",
    "temperatura",
    "tlo",
    "zrak",
    "atlantic",
    "fake"
]
```

### Add new scene - with tag ferit
```
http://localhost:8080/rest2/scene
```
```
{
    "title": "FER novo",
    "subtitle": "sva mjerenja",
    "layout": "LIST",
    "pictureLink": "https://www.tportal.hr/media/thumbnail/900x540/61817.jpeg",
    "tags": [
        "ferit"
    ],
    "views": [
        {
            "title": "Temperatura zraka",
            "description": "",
            "viewType": "series",
            "measurementUnit": "C",
            "selectForm": {
                "submitSelectionRequest": null,
                "inputs": [
                    {
                        "inputType": "STRING",
                        "name": "aggregationWindow",
                        "title": "Interval",
                        "inputOrder": 1,
                        "description": "Agregacija na bazi intervala",
                        "defaultValue": "1d",
                        "pattern": null,
                        "values": [
                            "1d",
                            "1h",
                            "15m"
                        ]
                    },
                    {
                        "inputType": "DATE",
                        "name": "startDateISO",
                        "title": "Početak",
                        "inputOrder": 2,
                        "description": "Datum početka grafa",
                        "defaultValue": "{{weekBeforeCurrentDateISO}}"
                    },
                    {
                        "inputType": "DATE",
                        "name": "endDateISO",
                        "title": "Kraj",
                        "inputOrder": 3,
                        "description": "Datum kraja grafa",
                        "defaultValue": "{{currentDateISO}}"
                    }
                ]
            },
            "query": {
                "method": "POST",
                "headers": {
                    "Content-type": "application/vnd.flux",
                    "Accept": "application/csv",
                    "Authorization": "Token {{influxFer}}"
                },
                "payload": "from(bucket:\"telegraf\")\n|> range(start: {{startDateISO}}, stop: {{endDateISO}})\n|> filter(fn: (r) => r._measurement == \"TC\" and r.id_wasp == \"SAP01\" and r._field == \"value\")\n|> drop(columns: [\"_start\", \"_stop\", \"_field\", \"host\", \"id\"])\n|> window(every: {{aggregationWindow}})\n|> mean()\n|> duplicate(column: \"_stop\", as: \"_time\")\n|> drop(columns: [\"_start\", \"_stop\"])\n",
                "URI": "https://iotat.tel.fer.hr:57786/api/v2/query?org=fer"
            },
            "responseExtracting": {
                "dataFormat": "csv",
                "timeColumn": "_time",
                "valueColumn": "_value"
            }
        },
        {
            "title": "Vlaga zraka",
            "description": "",
            "viewType": "series",
            "measurementUnit": "%",
            "selectForm": {
                "submitSelectionRequest": null,
                "inputs": [
                    {
                        "inputType": "STRING",
                        "name": "aggregationWindow",
                        "title": "Interval",
                        "inputOrder": 1,
                        "description": "Agregacija na bazi intervala",
                        "defaultValue": "1d",
                        "pattern": null,
                        "values": [
                            "1d",
                            "1h",
                            "15m"
                        ]
                    },
                    {
                        "inputType": "DATE",
                        "name": "startDateISO",
                        "title": "Početak",
                        "inputOrder": 2,
                        "description": "Datum početka grafa",
                        "defaultValue": "{{weekBeforeCurrentDateISO}}"
                    },
                    {
                        "inputType": "DATE",
                        "name": "endDateISO",
                        "title": "Kraj",
                        "inputOrder": 3,
                        "description": "Datum kraja grafa",
                        "defaultValue": "{{currentDateISO}}"
                    }
                ]
            },
            "query": {
                "method": "POST",
                "headers": {
                    "Content-type": "application/vnd.flux",
                    "Accept": "application/csv",
                    "Authorization": "Token {{influxFer}}"
                },
                "payload": "from(bucket:\"telegraf\")\n|> range(start: {{startDateISO}}, stop: {{endDateISO}})\n|> filter(fn: (r) => r._measurement == \"HUM\" and r.id_wasp == \"SAP01\" and r._field == \"value\")\n|> drop(columns: [\"_start\", \"_stop\", \"_field\", \"host\", \"id\"])\n|> window(every: {{aggregationWindow}})\n|> mean()\n|> duplicate(column: \"_stop\", as: \"_time\")\n|> drop(columns: [\"_start\", \"_stop\"])\n",
                "URI": "https://iotat.tel.fer.hr:57786/api/v2/query?org=fer"
            },
            "responseExtracting": {
                "dataFormat": "csv",
                "timeColumn": "_time",
                "valueColumn": "_value"
            }
        },
        {
            "title": "Solarna radijacija",
            "description": "",
            "viewType": "series",
            "measurementUnit": "μmol*m-2*s-1",
            "selectForm": {
                "submitSelectionRequest": null,
                "inputs": [
                    {
                        "inputType": "STRING",
                        "name": "aggregationWindow",
                        "title": "Interval",
                        "inputOrder": 1,
                        "description": "Agregacija na bazi intervala",
                        "defaultValue": "1d",
                        "pattern": null,
                        "values": [
                            "1d",
                            "1h",
                            "15m"
                        ]
                    },
                    {
                        "inputType": "DATE",
                        "name": "startDateISO",
                        "title": "Početak",
                        "inputOrder": 2,
                        "description": "Datum početka grafa",
                        "defaultValue": "{{weekBeforeCurrentDateISO}}"
                    },
                    {
                        "inputType": "DATE",
                        "name": "endDateISO",
                        "title": "Kraj",
                        "inputOrder": 3,
                        "description": "Datum kraja grafa",
                        "defaultValue": "{{currentDateISO}}"
                    }
                ]
            },
            "query": {
                "method": "POST",
                "headers": {
                    "Content-type": "application/vnd.flux",
                    "Accept": "application/csv",
                    "Authorization": "Token {{influxFer}}"
                },
                "payload": "from(bucket:\"telegraf\")\n|> range(start: {{startDateISO}}, stop: {{endDateISO}})\n|> filter(fn: (r) => r._measurement == \"PAR\" and r.id_wasp == \"SAP01\" and r._field == \"value\")\n|> drop(columns: [\"_start\", \"_stop\", \"_field\", \"host\", \"id\"])\n|> window(every: {{aggregationWindow}})\n|> mean()\n|> duplicate(column: \"_stop\", as: \"_time\")\n|> drop(columns: [\"_start\", \"_stop\"])\n",
                "URI": "https://iotat.tel.fer.hr:57786/api/v2/query?org=fer"
            },
            "responseExtracting": {
                "dataFormat": "csv",
                "timeColumn": "_time",
                "valueColumn": "_value"
            }
        },
        {
            "title": "Temperatura tla",
            "description": "",
            "viewType": "series",
            "measurementUnit": "C",
            "selectForm": {
                "submitSelectionRequest": null,
                "inputs": [
                    {
                        "inputType": "STRING",
                        "name": "aggregationWindow",
                        "title": "Interval",
                        "inputOrder": 1,
                        "description": "Agregacija na bazi intervala",
                        "defaultValue": "1d",
                        "pattern": null,
                        "values": [
                            "1d",
                            "1h",
                            "15m"
                        ]
                    },
                    {
                        "inputType": "DATE",
                        "name": "startDateISO",
                        "title": "Početak",
                        "inputOrder": 2,
                        "description": "Datum početka grafa",
                        "defaultValue": "{{weekBeforeCurrentDateISO}}"
                    },
                    {
                        "inputType": "DATE",
                        "name": "endDateISO",
                        "title": "Kraj",
                        "inputOrder": 3,
                        "description": "Datum kraja grafa",
                        "defaultValue": "{{currentDateISO}}"
                    }
                ]
            },
            "query": {
                "method": "POST",
                "headers": {
                    "Content-type": "application/vnd.flux",
                    "Accept": "application/csv",
                    "Authorization": "Token {{influxFer}}"
                },
                "payload": "from(bucket:\"telegraf\")\n|> range(start: {{startDateISO}}, stop: {{endDateISO}})\n|> filter(fn: (r) => r._measurement == \"SOILTC\" and r.id_wasp == \"SAP01\" and r._field == \"value\")\n|> drop(columns: [\"_start\", \"_stop\", \"_field\", \"host\", \"id\"])\n|> window(every: {{aggregationWindow}})\n|> mean()\n|> duplicate(column: \"_stop\", as: \"_time\")\n|> drop(columns: [\"_start\", \"_stop\"])\n",
                "URI": "https://iotat.tel.fer.hr:57786/api/v2/query?org=fer"
            },
            "responseExtracting": {
                "dataFormat": "csv",
                "timeColumn": "_time",
                "valueColumn": "_value"
            }
        },
        {
            "title": "Vlaga tla",
            "description": "Frequency",
            "viewType": "series",
            "measurementUnit": "",
            "selectForm": {
                "submitSelectionRequest": null,
                "inputs": [
                    {
                        "inputType": "STRING",
                        "name": "aggregationWindow",
                        "title": "Interval",
                        "inputOrder": 1,
                        "description": "Agregacija na bazi intervala",
                        "defaultValue": "1d",
                        "pattern": null,
                        "values": [
                            "1d",
                            "1h",
                            "15m"
                        ]
                    },
                    {
                        "inputType": "DATE",
                        "name": "startDateISO",
                        "title": "Početak",
                        "inputOrder": 2,
                        "description": "Datum početka grafa",
                        "defaultValue": "{{weekBeforeCurrentDateISO}}"
                    },
                    {
                        "inputType": "DATE",
                        "name": "endDateISO",
                        "title": "Kraj",
                        "inputOrder": 3,
                        "description": "Datum kraja grafa",
                        "defaultValue": "{{currentDateISO}}"
                    }
                ]
            },
            "query": {
                "method": "POST",
                "headers": {
                    "Content-type": "application/vnd.flux",
                    "Accept": "application/csv",
                    "Authorization": "Token {{influxFer}}"
                },
                "payload": "from(bucket:\"telegraf\")\n|> range(start: {{startDateISO}}, stop: {{endDateISO}})\n|> filter(fn: (r) => r._measurement == \"SOIL_C\" and r.id_wasp == \"SAP01\" and r._field == \"value\")\n|> drop(columns: [\"_start\", \"_stop\", \"_field\", \"host\", \"id\"])\n|> window(every: {{aggregationWindow}})\n|> mean()\n|> duplicate(column: \"_stop\", as: \"_time\")\n|> drop(columns: [\"_start\", \"_stop\"])\n",
                "URI": "https://iotat.tel.fer.hr:57786/api/v2/query?org=fer"
            },
            "responseExtracting": {
                "dataFormat": "csv",
                "timeColumn": "_time",
                "valueColumn": "_value"
            }
        },
        {
            "title": "Vlaga lista",
            "description": "",
            "viewType": "series",
            "measurementUnit": "V",
            "selectForm": {
                "submitSelectionRequest": null,
                "inputs": [
                    {
                        "inputType": "STRING",
                        "name": "aggregationWindow",
                        "title": "Interval",
                        "inputOrder": 1,
                        "description": "Agregacija na bazi intervala",
                        "defaultValue": "1d",
                        "pattern": null,
                        "values": [
                            "1d",
                            "1h",
                            "15m"
                        ]
                    },
                    {
                        "inputType": "DATE",
                        "name": "startDateISO",
                        "title": "Početak",
                        "inputOrder": 2,
                        "description": "Datum početka grafa",
                        "defaultValue": "{{weekBeforeCurrentDateISO}}"
                    },
                    {
                        "inputType": "DATE",
                        "name": "endDateISO",
                        "title": "Kraj",
                        "inputOrder": 3,
                        "description": "Datum kraja grafa",
                        "defaultValue": "{{currentDateISO}}"
                    }
                ]
            },
            "query": {
                "method": "POST",
                "headers": {
                    "Content-type": "application/vnd.flux",
                    "Accept": "application/csv",
                    "Authorization": "Token {{influxFer}}"
                },
                "payload": "from(bucket:\"telegraf\")\n|> range(start: {{startDateISO}}, stop: {{endDateISO}})\n|> filter(fn: (r) => r._measurement == \"LW\" and r.id_wasp == \"SAP01\" and r._field == \"value\")\n|> drop(columns: [\"_start\", \"_stop\", \"_field\", \"host\", \"id\"])\n|> window(every: {{aggregationWindow}})\n|> mean()\n|> duplicate(column: \"_stop\", as: \"_time\")\n|> drop(columns: [\"_start\", \"_stop\"])\n",
                "URI": "https://iotat.tel.fer.hr:57786/api/v2/query?org=fer"
            },
            "responseExtracting": {
                "dataFormat": "csv",
                "timeColumn": "_time",
                "valueColumn": "_value"
            }
        },
        {
            "title": "Tlak zraka",
            "description": "",
            "viewType": "series",
            "measurementUnit": "Pa",
            "selectForm": {
                "submitSelectionRequest": null,
                "inputs": [
                    {
                        "inputType": "STRING",
                        "name": "aggregationWindow",
                        "title": "Interval",
                        "inputOrder": 1,
                        "description": "Agregacija na bazi intervala",
                        "defaultValue": "1d",
                        "pattern": null,
                        "values": [
                            "1d",
                            "1h",
                            "15m"
                        ]
                    },
                    {
                        "inputType": "DATE",
                        "name": "startDateISO",
                        "title": "Početak",
                        "inputOrder": 2,
                        "description": "Datum početka grafa",
                        "defaultValue": "{{weekBeforeCurrentDateISO}}"
                    },
                    {
                        "inputType": "DATE",
                        "name": "endDateISO",
                        "title": "Kraj",
                        "inputOrder": 3,
                        "description": "Datum kraja grafa",
                        "defaultValue": "{{currentDateISO}}"
                    }
                ]
            },
            "query": {
                "method": "POST",
                "headers": {
                    "Content-type": "application/vnd.flux",
                    "Accept": "application/csv",
                    "Authorization": "Token {{influxFer}}"
                },
                "payload": "from(bucket:\"telegraf\")\n|> range(start: {{startDateISO}}, stop: {{endDateISO}})\n|> filter(fn: (r) => r._measurement == \"PRES\" and r.id_wasp == \"SAP01\" and r._field == \"value\")\n|> drop(columns: [\"_start\", \"_stop\", \"_field\", \"host\", \"id\"])\n|> window(every: {{aggregationWindow}})\n|> mean()\n|> duplicate(column: \"_stop\", as: \"_time\")\n|> drop(columns: [\"_start\", \"_stop\"])\n",
                "URI": "https://iotat.tel.fer.hr:57786/api/v2/query?org=fer"
            },
            "responseExtracting": {
                "dataFormat": "csv",
                "timeColumn": "_time",
                "valueColumn": "_value"
            }
        },
        {
            "title": "Baterija",
            "description": "",
            "viewType": "series",
            "measurementUnit": "%",
            "selectForm": {
                "submitSelectionRequest": null,
                "inputs": [
                    {
                        "inputType": "STRING",
                        "name": "aggregationWindow",
                        "title": "Interval",
                        "inputOrder": 1,
                        "description": "Agregacija na bazi intervala",
                        "defaultValue": "1d",
                        "pattern": null,
                        "values": [
                            "1d",
                            "1h",
                            "15m"
                        ]
                    },
                    {
                        "inputType": "DATE",
                        "name": "startDateISO",
                        "title": "Početak",
                        "inputOrder": 2,
                        "description": "Datum početka grafa",
                        "defaultValue": "{{weekBeforeCurrentDateISO}}"
                    },
                    {
                        "inputType": "DATE",
                        "name": "endDateISO",
                        "title": "Kraj",
                        "inputOrder": 3,
                        "description": "Datum kraja grafa",
                        "defaultValue": "{{currentDateISO}}"
                    }
                ]
            },
            "query": {
                "method": "POST",
                "headers": {
                    "Content-type": "application/vnd.flux",
                    "Accept": "application/csv",
                    "Authorization": "Token {{influxFer}}"
                },
                "payload": "from(bucket:\"telegraf\")\n|> range(start: {{startDateISO}}, stop: {{endDateISO}})\n|> filter(fn: (r) => r._measurement == \"BAT\" and r.id_wasp == \"SAP01\" and r._field == \"value\")\n|> drop(columns: [\"_start\", \"_stop\", \"_field\", \"host\", \"id\"])\n|> window(every: {{aggregationWindow}})\n|> mean()\n|> duplicate(column: \"_stop\", as: \"_time\")\n|> drop(columns: [\"_start\", \"_stop\"])\n",
                "URI": "https://iotat.tel.fer.hr:57786/api/v2/query?org=fer"
            },
            "responseExtracting": {
                "dataFormat": "csv",
                "timeColumn": "_time",
                "valueColumn": "_value"
            }
        }
    ],
    "roles": [
        "fer"
    ],
    "keys": [
        "influxFer",
        "influxFerit"
    ]
}
```

### Get short scenes
```
http://localhost:8080/rest2/scene
```
```
[
    {
        "id": 3,
        "title": "FER rani kukuruz",
        "subtitle": "sva mjerenja",
        "tags": [
            "fao200"
        ]
    },
    {
        "id": 62,
        "title": "FER srednji kukuruz",
        "subtitle": "sva mjerenja",
        "tags": [
            "fao400"
        ]
    },
    {
        "id": 156,
        "title": "FER-ov vrt - tlo",
        "subtitle": "mjerenja tla",
        "tags": [
            "tlo"
        ]
    },
    {
        "id": 194,
        "title": "FER-ov vrt - zrak",
        "subtitle": "mjerenja zraka",
        "tags": [
            "zrak"
        ]
    },
    {
        "id": 281,
        "title": "FER-ov vrt - temp. tla",
        "subtitle": "mjerenja temperature tla",
        "tags": [
            "tlo",
            "temperatura"
        ]
    },
    {
        "id": 298,
        "title": "FERIT - 1",
        "subtitle": "sva mjerenja",
        "tags": [
            "ferit"
        ]
    },
    {
        "id": 336,
        "title": "FERIT - 2",
        "subtitle": "sva mjerenja",
        "tags": [
            "ferit"
        ]
    },
    {
        "id": 423,
        "title": "FERIT - GDD",
        "subtitle": "GDD na FERIT-ovim stranicama",
        "tags": [
            "ferit"
        ]
    },
    {
        "id": 432,
        "title": "FER - GDD",
        "subtitle": "GDD na FER-ovim stanicama",
        "tags": [
            "fer"
        ]
    },
    {
        "id": 447,
        "title": "FER lažni uređaji",
        "subtitle": "mjerenja i aktuator",
        "tags": [
            "fake",
            "sensor",
            "actuator"
        ]
    }
]
```

### Deleet newly created scene
```
http://localhost:8080/rest2/scene/465
```

### Get scenes names availabe from specific tag hierarchy
```
http://localhost:8080/api/scenes/tags/ferit
```

```
[
    "FERIT - GDD",
    "FERIT - 2",
    "FERIT - 1"
]
```

### Get Scenes object for specific tag hierarchy
```
http://localhost:8080/rest2/scenes/tag/ferit
```


## Papper abstract
```
In this paper, the issue of hierarchy among data is presented. In addition, the most common
models for realizing hierarchy in the SQL domain of relational databases and also in the
domain of NOSQL on the graph model are explained. The paper compares the mentioned
models according to the requirements of the complexity of query writing, visualization and
the difficulty of implementation.
Through the work, we made an introduction to the Neo4j graphic database and the Cypher
query language.
Through the SpringBoot framework, the Neo4j database was integrated with the existing
IoT Polje project and additional services, which were developed as part of the R project.
Through the work, a hybrid solution of the hierarchy was developed, which does not
depend on the existing design and implementation of data storage. In other words, we have
created a hierarchy solution that is easily integrated with existing relational database.
```

