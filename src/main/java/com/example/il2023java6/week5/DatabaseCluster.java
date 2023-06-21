package com.example.il2023java6.week5;

/**
 *  Leader - Follower
 *  Master - Slave
 *  Primary - Secondary
 *  *   *  *   *  *   *  *   *  *   *  *   *  *   *  *   *
 *  RDBMS cluster
 *  Leader(write db)
 *      /           \       \       \
 *   Standby   Follower1    2        3
 *   DB         read db   read db  read db
 *
 *  write database : 1 leader + N followers
 *
 *  RDBMS partition
 *  DB1         DB2         DB3
 *  1. id % db number
 *  2. hash(id) % db number
 *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  Raft
 *
 *        Leader  -  Candidate  -  Follower
 *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  CAP => CP / AP
 *  CP
 *  MongoDB cluster
 *                  mongos (gateway) ---- config server (id - sharding locations)
 *             /        \           \
 *        sharding1  sharding2   sharding3
 *       primary node   ..          ...
 *       secondary node
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  Multi-leader
 *      Leader1         Leader2         Leader3
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  Leaderless system
 *  AP
 *  Cassandra Node
 *      write
 *          memory          disk
 *      ->  mem table ->  SSTable
 *          |
 *      commit log
 *
 *      read -> mem table -> if not in mem table -> read disk blooming filter -> merge result -> return to user
 *
 *          SST5
 *        /         \       \           \
 *      SST1      SST2      SST3       SST4
 *
 *   hash1 [][2][][][][]
 *   hash2 [][1][][][][1]
 *   hash3 [][][1][][][1]
 *   count(orange) => min(2, 1, 1) => 1
 *   count(apple) => min(2, 1, 1) => 1
 *
 *
 *                  N0 (0 ~ 10k)
 *
 *
 *     N3(30k~max)                   N1(10k ~ 20k)
 *
 *
 *                  N2(20k ~ 30k)
 *
 *                  N0(0)
 *
 *
 *     N3(30k)                  N1(10k)
 *
 *
 *                  N2(20K)
 *
 *    Replica factor: 3
 *    Read Consistency Level: 1 ~ 3
 *    Write Consistency Level: 1 ~ 3
 *
 *    consistency : wc + rc > replica factor
 *
 *    example
 *    wc = 2
 *    -> write -> N3 -> N0, N1, N2 (if any 2 of them finish the write request, we send success response to user)
 *    rc = 2
 *    -> read -> N3 -> read two of (N0, N1, N2)
 *                  -> get all data from fastest node N0, get Digest request (hash value of data) from N1
 *                  -> if N0 has new data, N1 doesn't have
 *                  -> trigger read repair => only repair N0 and N1
 *                  -> return new data to user
 *
 *
 *  Eventually Consistency
 *      BASE => basic availability
 *              soft stage
 *              eventually consistency
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   Redis
 *   Redis node fail tolerance
 *          AOF => append of file
 *          DB Snapshot => every certain time => take a snapshot
 *   Redis cluster
 *          0 ~ 25k hash slots
 *          Leader(0 ~ 10k)     Leader(10k ~ 11k)
 *         /        \               /       \
 *      Follower    Follower
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   Cache
 *
 *      user    -  cache
 *       |
 *      DB
 *
 *      read data
 *          1. read from cache
 *              if find => return data
 *              if not => read from db => save to cache
 *      write data
 *          1. remove data from cache
 *          2. save to database
 *
 */










