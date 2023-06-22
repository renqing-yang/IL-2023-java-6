package com.example.il2023java6.week5;

/**
 * producer(server) ->  message queue(server) ->  consumer(server)
 * Rabbit MQ/ Active MQ
 * 1. queue model
 *      p1                                  consumer1 [m2]
 *      p2      [][][][m1][m3][m2]          consumer2 [m3]
 *      p3                                  consumer3 [m1]
 * 2. publisher - subscriber model
 *      p1                                  consumer1 [m2]
 *      p2      [][][][m1][m3][m2]          consumer2 [m2]
 *      p3                                  consumer3 [m2]
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 * Kafka
 *                  broker(server)
 *     p1       Topic1Partition1[m1][m2]            consumer group1  consumer1 (T1partition1)
 *     p2       Topic1Partition2[m3]                consumer group1  consumer2 (T1partition2)
 *     p3
 *                                                  consumer group2  consumer1 (T1partition1, 2)
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 * SQS / SNS
 * 1. SQS (visibility timeout)
 *      p1                                  consumer1 [m2]
 *      p2      [][][][m1][m3][m2]          consumer2 [m3]
 *      p3                                  consumer3 [m1]
 *  2. SNS
 *
 *  SNS + SQS
 *                          ->  SQS -> consumers
 *  ----> SNS ---> Topic    ->  SQS -> consumers
 *                          ->  SQS -> consumers
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  Dead Letter Queue
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   At least one / duplicate messages / reprocess same message multiple times
 *   1. idempotent service / idempotent api
 *   2. cache
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   Global Transaction
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   MQ + DB
 *          user
 *          |
 *      serviceA    ->  message queue -> serviceB
 *          |
 *         DB
 *   1. user send request to service A
 *   2. serviceA save data to database
 *   3. serviceA send message to message queue
 *   4. serviceA send response to user
 *
 *   CDC(change data capture) + outbox pattern
 *
 *          user
 *          |
 *      serviceA
 *          |
 *         DB   ->  change data capture service ->  message queue -> serviceB
 *
 *    service A
 *    1. user send request to service A
 *    2. serviceA save data to database
 *       begin tx
 *       insert user data
 *       insert message into outbox table
 *       commit
 *    3. serviceA send response to user
 *
 *    change data capture service
 *    1. checking changes in outbox table
 *    2. send to message queue
 *    3. delete it from outbox table
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *         coordinator
 *         /       \
 *       DB1        DB2
 *
 *    2 phases commit
 *
 *    phase 1 : coordinator send request to both DB1, DB2
 *              DB1, DB2 give you ok response
 *    phase 2 : coordinator send commit request to both of them
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   SAGA
 *   serviceA   -  message queue  -  service B  -  message queue ...
 *      |                               |
 *     DB                               DB
 *
 *    1. serviceA commit DB query  (example amount -20)
 *       serviceA send message to mq
 *    2. serviceB commit DB query
 *          if committed   => move to next step
 *          if not  => send roll back message to service A (amount +20)
 */