package week5;

/**
 *
 * why Message queue
 *
 *      user -> service1    ->   service2
 *               |                  |
 *              DB1                DB2
 *
 *
 *      user
 *       |
 *      service1    -  message queue  -  service2
 *       |                                  |
 *       DB1                               DB2
 *
 *
 *      1. user http request service1
 *      2. service1 -> push message to message queue
 *      3. service1 http response user
 *
 *
 *      service 2 pull message from message queue
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Queue model (active mq / rabbit mq)
 *      producer1                               consumer1[m3]
 *      producer2       [m1][m2][m3][m4]..      consumer2[m4]
 *      producer3                               consumer3[m2]
 *
 *
 *  Publisher Subscriber model
 *      producer1                               consumer1[m4]
 *      producer2       [m1][m2][m3][m4]..      consumer2[m4]
 *      producer3                               consumer3[m4]
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 * kafka
 * Queue model
 *      producer            broker1                 consumer group 1
 *                        T1 partition1             consumer1  [p1, p2]
 *                        T1 partition2
 *
 *
 *      producer            broker1                 consumer group 1
 *                        T1 partition1             consumer1  [p1]
 *                        T1 partition2             consumer2  [p2]
 *                                                  consumer3  [idle]
 *
 * Publisher / Subscriber
 *      producer            broker1                 consumer group 1
 *                        T1 partition1             consumer1  [p1]
 *                        T1 partition2             consumer2  [p2]
 *                                                  consumer3  [idle]
 *
 *                                                  consumer group 2
 *                                                  consumer1 [p1, p2]
 *
 *
 *  kafka cluster
 *
 *          broker1 :   T1 partition1 (leader)
 *                      T2 partition1 (follower)
 *
 *          broker2 :   T1 partition1 (follower)
 *                      T2 partition1 (leader)
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  SQS
 *  queue model
 *      visibility timeout
 *      producer1                               consumer1[m3]
 *      producer2       [m1][m2][m3][m4]..      consumer2[m4]
 *      producer3                               consumer3[m2]
 *
 *  SNS
 *  Publisher / Subscriber
 *                          SQS
 *            ->  SNS  ->   SQS
 *                          text / email
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Idempotent service
 *      update user id = 5, set name = 'Tom'
 *
 *
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Duplicate Message
 *      1. SNS deduplicate
 *      2. idempotent service
 *      3. cache -> processed message id
 *
 *          message queue -> consumer
 *                              |
 *                            cache
 *              1. consumer pull message
 *              2. process message
 *              3. write message id into cache
 *              4. remove message from message queue
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Global transaction
 *          Service   ->   message queue
 *             |
 *            DB
 *
 *          1. service -> insert db, commit
 *          ....
 *          2. service -> push message to mq , commit
 *
 *
 *          1. service -> push message to mq , commit
 *          ...
 *          2. service -> insert db, commit
 *
 *
 *  CDC + outbox pattern
 *          Service
 *             |
 *            DB   ->  cdc service ->   message queue
 *
 *        1. begin db tx
 *        2. insert request data
 *           insert message into outbox table
 *        3. commit db tx
 *
 *      cdc service
 *        1. read new messages from outbox table
 *        2. send to message queue
 *        3. mark message as success in outbox table
 *
 *
 *  2pc / 3pc (phase commit)
 *              |
 *          coordinator
 *          /       \
 *       DB1        DB2
 *
 *       1. prepare stage
 *          DB1 ready ?  -> ok
 *          DB2 ready ?  -> ok
 *       2. commit stage
 *
 *  SAGA pattern
 *      service1  -  mq  - service2  - mq  - service3
 *        |                  |               |
 *       DB1                DB2             DB3
 *
 *       1. service1  commit DB1 order - 1
 *          send msg to mq
 *       2. service2  commit DB2 -> if fail -> send to rollback queue -> service1 commit order + 1
 *          send msg to mq
 *       3. service3  commit DB3
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   service    -  cache
 *     |
 *    DB
 *
 *   cache aside pattern (?)
 *      read :
 *          1. read from cache  -> find data -> return to user
 *          2. if cannot find data -> read DB -> insert to cache
 *      write :
 *          1. remove data from cache
 *          2. write DB
 *
 *      problem:
 *          write to DB
 *          1. remove data from cache
 *          2. other users select data from db -> save to cache
 *          3. write DB commit
 *
 *      solution1: timeout
 *      solution2: write data to DB commit(cdc)
 *                 send message to message queue (remove certain data from cache)
 *                 consumer remove data from cache
 *
 *   service - cache - database
 *   Read through / Write through
 *      read : only read from cache
 *      write1 : only update cache -> cache update database immediately
 *      write2 : only update cache -> update database once a while
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  distributed lock (redis)
 *
 *      1. insert id = 1 if not exist
 *      ..
 *      2. execute tasks/operations/processes
 *      ..
 *
 *      solution
 *      1. timeout / TTL
 *      2. extend TTL (?)
 *      3. version id
 */