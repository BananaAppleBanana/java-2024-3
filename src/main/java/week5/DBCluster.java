package week5;

/**
 *  Single Leader
 *      design 1
 *      leader
 *         |        \       \
 *      follower  follower  follower
 *
 *      write: only write to leader -> asynchronous sync data to followers
 *             write to leader and 1 ~ N followers -> asynchronous sync data to other followers
 *
 *      read : read from leader or follower
 *
 *      design 2
 *      Leader <-> follower <-> follower <-> follower
 *
 *      design 3
 *      Primary
 *         |            \                \
 *      Secondary   read replica    read replica
 *
 *  Multi Leader
 *      Leader(all data)          Leader(all data)          Leader(all data)
 *     /        \                   /       \                   /       \
 *  follower    follower        follower    follower        follower    follower
 *
 *      1. timestamp
 *      2. Vector clock
 *
 *  Leaderless = all leaders
 *
 *      Cassandra
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *   raft vs paxos
 *      consensus algorithm
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Sharding
 *
 *     DB0 0, 3, 6, 9.. +3
 *     DB1 1, 4, 7, .. +3
 *     DB2 2, 5, 8 ... +3
 *     DB3
 *
 *     read id data -> db number = id % 3  -> 0 % 3 = 0, 1 % 3 = 1..  3 % 3 = 0, 4 % 3 = 1
 *     read id data -> db number = id % 4  -> 0 % 4 = 0, 3 % 4 = 3, 4 % 4 = 0
 *
 *   *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *   CP / AP
 *                          mongos(gateway)         -       mongos config
 *
 *                  /           |               \
 *           sharding1        sharding2         sharding3
 *            primary
 *          secondary
 *          secondary
 *           1 ~ 10k        10k+1 ~ 20k         20k+1 ~ 30k
 *
 *      write -> primary + 0 ~ n secondary
 *      read  -> primary / secondary
 *
 *
 *   Cassandra Node
 *
 *          -->      memtable   ->  SSTable(immutable)
 *           |
 *        commit log
 *
 *
 *                          SSTable
 *              /           |               \           \
 *        SSTable1        SSTable2      SSTable3      SSTable4
 *
 *
 *
 *   Cassandra Cluster(consistent hashing) AP
 *                 hash len (0 ~ 10000)
 *
 *                           Node1(0) 'a'
 *
 *
 *          Node4(2001)                   Node2(501) 'a'
 *
 *
 *                         Node3(1001) 'b'
 *
 *   Replica factory = 3
 *   Read consistency level = 1 ~ rf
 *      request -> node4 -> read from node1, node 2, node3
 *                       -> read all data from fastest node
 *                          read hash value from other node
 *                       -> if hash value diff -> trigger read repair
 *                       -> return to user
 *   Write consistency level = 1 ~ rf
 *      request -> node4 -> send write request to node1, 2, 3
 *                       -> if wc = 2
 *                       -> get success response from 2, 3
 *                       -> return to user
 *
 *   RC + WC > RF
 *
 *   *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *   Global Secondary Index
 *      name:
 *         Tom: [sharding 1, 3]
 *   *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *   Redis
 *      hash slots fixed number
 *
 *
 *      min~500       501~1k      1001 ~ max
 *      Leader   <->  Leader  <->   Leader
 *     /    \
 *  Follower    ..
 *
 *
 *
 *    backup
 *      1. every certain time -> snapshot
 *      2. aof -> append commands / operations
 *
 *
 *    Tomorrow
 *      1. message queue
 *      2. global transaction
 */
