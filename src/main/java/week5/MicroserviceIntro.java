package week5;

/**
 * vertical scaling vs horizontal scaling
 *
 * Monolithic
 *             load balancer (server, ip: port) load balancer (server, ip: port) / (health check)
 *      /         |         \           \
 *  server1     server2     server3   server4
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  CAP : CP / AP
 *      consistency :
 *      availability
 *      partition tolerance
 *  BASE (AP)
 *      basic availability
 *      soft stage
 *      eventually consistency
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 * Microservice
 *                              |
 *                          api gateway
 *                         /            \
 *
 *      emp server(ip1 + port1)     emp server(ip2 + por2)    emp server(ip3 + port3)
 *
 *              |                   \                           \
 *      dept server(ip4 + port1)     dept server(ip5 + por2)    dept server(ip6 + port3)
 *
 *      rest request -> http://dept-service/uri
 *
 *      user1 -> request1(id1) -> api gateway server1(log id1 + user1 + timestamp1) -> emp server1(log id1 + user1 + timestamp2) -> dept server1(log id1 + user1 + timestamp3)
 *      user1 -> request2(id2) -> api gateway server1(log id2 + user1 + timestamp4) -> emp server1(log id2 + user1 + timestamp4) -> dept server1(log id2 + user1 + timestamp5)
 *
 *
 * How to create microservice / what services we need
 *      1. service registration / discovery service (DNS)
 *          emp service ->  dept service
 *              |
 *          discovery service (key: value)  (key service name : value list of ip)
 *          a.  emp service -> http://dept-service/uri -> dept service
 *          b.  emp service -> http request (query dept-service ip) -> discovery service
 *          c.  discovery service  -> response list of ip -> emp service
 *          d.  emp service -> http://ip/uri -> dept service
 *     2. Api gateway
 *          1. hide private subnet / ips
 *          2. centralized entry point
 *          3. log
 *          4. generate co-relation id  / global unique id
 *                  api gateway server1        api gateway server2          api gateway server3
 *
 *                a. long 64 bit number => timestamp + machine id + process id + serial id(0 - 11111)
 *                b. UUID
 *                c. centralized id generator server(?)
 *                d. database sequence
 *                      api gateway1 :  id -> 0, 3, 6, 9, .. + 3
 *                      api gateway2 :  id -> 1, 4, ... + 3
 *                      api gateway3 :  id -> 2, 5, ...
 *
 *
 *          5. redirect request -> security service
 *          6. rate limiter
 *     3. centralized security service
 *     4. centralized timestamp service
 *     5. centralized config service
 *     6. message queue
 *         producer - message queue - consumer
 *         1. asynchronous communication between services
 *         2. handle more requests
 *     7. database cluster (single leader / multi leader / leader less,  sequential io / random io)
 *     8. redis cluster
 *     9. circuit breaker
 *          serverA -> serverB -> serverC
 *              example:
 *                      if -> 5 requests , 3 requests timeout -> status to close -> return default result
 *                                                                      |
 *                                                               background thread -> check health of serverC
 *                                                                                          |
 *                                                                                      return healthy
 *                                                                                         |
 *                                                                                   status -> open / on
 *
 *     10. deployment (docker)
 *     11. ci/cd
 *
 *
 *
 *
 *   tomorrow
 *   1. spring cloud application
 *   2. monolithic vs microservice
 *   3. disadvantages of microservice
 *   4. why microservice
 *   5. demo
 *
 *
 *
 *
 *
 */