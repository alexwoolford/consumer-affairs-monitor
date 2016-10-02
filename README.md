# consumer-affairs-monitor

1) create Solr collection:

    solr create_collection -c consumer_affairs -d data_driven_schema_configs -shards 2 -replicationFactor 2

2) create fields:

    curl -X POST -H 'Content-type:application/json' --data-binary '{
      "add-field":{
         "name":"company",
         "type":"string",
         "multiValued":false,
         "indexed":true,
         "required":true,
         "stored":true }
    }' http://hadoop02.woolford.io:8983/solr/consumer_affairs/schema


    curl -X POST -H 'Content-type:application/json' --data-binary '{
      "add-field":{
         "name":"author",
         "type":"string",
         "multiValued":false,
         "indexed":true,
         "required":true,
         "stored":true }
    }' http://hadoop02.woolford.io:8983/solr/consumer_affairs/schema


    curl -X POST -H 'Content-type:application/json' --data-binary '{
      "add-field":{
         "name":"timestamp",
         "type":"long",
         "multiValued":false,
         "indexed":true,
         "required":true,
         "stored":true }
    }' http://hadoop02.woolford.io:8983/solr/consumer_affairs/schema


    curl -X POST -H 'Content-type:application/json' --data-binary '{
      "add-field":{
         "name":"rating",
         "type":"int",
         "multiValued":false,
         "indexed":true,
         "required":true,
         "stored":true }
    }' http://hadoop02.woolford.io:8983/solr/consumer_affairs/schema


    curl -X POST -H 'Content-type:application/json' --data-binary '{
      "add-field":{
         "name":"sentiment",
         "type":"int",
         "multiValued":false,
         "indexed":true,
         "required":true,
         "stored":true }
    }' http://hadoop02.woolford.io:8983/solr/consumer_affairs/schema


    curl -X POST -H 'Content-type:application/json' --data-binary '{
      "add-field":{
         "name":"content",
         "type":"string",
         "multiValued":false,
         "indexed":true,
         "required":true,
         "stored":true }
    }' http://hadoop02.woolford.io:8983/solr/consumer_affairs/schema