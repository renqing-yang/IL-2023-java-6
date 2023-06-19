package com.example.il2023java6.week5;

/**
 *  Monolithic Application
 *          Load balancer
 *          /   \   \   \
 *      node1   2   3   4
 *
 *      scalability
 *          horizontal scaling
 *          vertical scaling
 *
 *      issues / cons / problems
 *          1. develop features in same project
 *          2. deployment
 *          3. scale
 *          ...
 *
 *  Microservice Application
 *             user
 *              |
 *         service A
 *             |
 *         service B
 *        /         \
 *    service C     service D
 *
 *    why microservices ? cons ? advantages ?
 *    1. diff teams / diff developers work on diff services
 *    2. deployment => partial deployment
 *    3. fail tolerance
 *    4. can use diff languages in diff service
 *    5. scalability
 *    ...
 *
 *    difficulties when design microservices ? down side ?
 *    1. hard to define service boundary
 *    2. security
 *          centralized security service
 *    3. api gateway : Spring Cloud Zuul / Spring Cloud Gateway
 *          centralized entry point of your application
 *          routing /endpoint1 => serviceA, /endpoint2 => serviceB
 *          rate limiter
 *          log
 *          co-relation id / global unique id
 *              uuid
 *              timestamp + machine id + thread id / process id + serial id(0 ~ xx) => 64
 *              db1 (id: 0, 3, 6, 9, 12...) / db2 (id: 1, 4, 7, 10, 13...) / db3 (id: 2, 5, 8 ..)
 *    4. service discovery (Spring Cloud Eureka) + client side load balancer (Spring Cloud Ribbon)
 *          serviceA -> serviceB
 *          rest template => url (http://service-name/path) => fetch ip from discovery services => send http request to url
 *          process
 *          1. start application
 *          2. register itself into discovery service
 *          3. discovery service start sending health check to diff nodes / services
 *    5. config service (Spring Cloud Config) + actuator /refresh
 *          centralized configuration service
 *    6. circuit breaker (Spring Cloud Hystrix)
 *          serviceA -> serviceB -timeout> serviceC
 *          serviceB -> serviceC  fails 3 times in last 5 requests
 *                   -> status from open to close + background thread check serviceC api
 *                   -> in the mean time -> return default value
 *                   -> if background thread can get response from serviceC -> change status to open
 *    7. message queue (Kafka / Rabbit MQ, Active MQ / SQS, SNS..)
 *              user
 *              |
 *          serviceA -> message queue[][][][][] -> serviceB
 *          1. user -> request -> service A
 *          2. serviceA commit msg to message queue
 *          3. serviceA -> response -> user
 *
 *          serviceB keep pulling new messages from message queue
 *
 *    8. database cluster
 *          rdbms cluster
 *          mongodb cluster
 *          cassandra cluster
 *    9. cache cluster
 *          redis cluster
 *          how to use cache with database
 *    10. log servers
 *          cloudwatch aws service
 *          Splunk server
 *              search log
 *              build index on logs
 *              create metrics : example P99
 *    11. global transaction
 *          two phase commit
 *          SAGA pattern
 *                  service
 *                  /   \
 *data storage     1    2
 *    ..
 *
 */