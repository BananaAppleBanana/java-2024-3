package week4;

/**
 * B tree
 * [1, 10, 5, 20, 25, 17, 30, 3, 50]
 * idx = 0, val = 1
 * [1]
 * idx = 1, val = 10
 * [1, 10]
 * idx = 2, val = 5
 * [1, 5, 10]
 * idx = 3, val = 20
 * [1, 5, 10, 20]
 * 	  [10]
 *     /		\
 * [1, 5]    [10, 20]
 * idx = 4, val = 25
 *        [10]
 *     /		\
 * [1, 5]    [10, 20, 25]
 * idx = 5, val = 17
 *        [10]
 *     /		\
 * [1, 5]    [10, 17, 20, 25]
 *        [10, 20]
 *     /		\	   \
 * [1, 5]    [10, 17]  [20, 25]
 * idx = 6, val = 30
 *
 *        [10, 20]
 *     /		\     \
 * [1, 5]    [10, 17]  [20, 25, 30]
 * idx = 7, val = 3
 *            [10, 20]
 *        /		\		\
 * [1, 3, 5]    [10, 17]  [20, 25, 30]
 * idx = 8, val = 50
 *            [10, 20, 30]
 *        /		\		\		\
 * [1, 3, 5]    [10, 17]  [20, 25]  [30, 50]
 * add val 8
 * 				      [20]
 *     			/			  \
 * 		 [5, 10]   		     [20, 30]
 *        /	|	\		     /		\
 * [1, 3]  [5, 8]  [10, 17]    [20, 25]  [30, 50]
 *
 * B+
 *
 *       				[20]
 *     			/			  \
 * 		 [5, 10]   		     	[20, 30]
 *        /	|	\		     	/			\
 * [1, 3]<-> [5, 8]<->[10, 17] <->[20, 25] <-> [30, 50]
 *
 *
 *
 * RDBMS
 * non-clustered index
 *     				  [20]
 *     			/			  \
 * 		 [5, 10]   		     	[20, 30]
 *        /	|	\		     	/			\
 * [1, 3]<-> [5, 8]<->[10, 17] <->[20, 25] <-> [30, 50]
 *  |	 	   |
 * rowid	 rowid
 *   |
 *   |
 *    ------------------->  table
 *     					  id 		name
 *     					  1			xx
 *     					  3			xx
 *     					  5			xx
 *
 * index access scan
 * 	1. index unique scan
 *     2. index range scan
 *     3. index full scan
 *     4. index fast full scan
 * full table scan
 *     multithreading
 *
 *
 * BitMap index (duplicate element / value)
 *     len <= 32 ->  int -> mask ->   (mask | 1 << (ch - 'a'))
 *     1010101011000001010110000.....
 *
 *
 * 	id 		state	  rowid   	   rowid    nj	ny	va
 *     1		  nj		xx					1	0	0
 *     3		  ny		xy					0	1	0
 *     5		  nj							1	0	0
 *     10        va							0	0	1
 *
 *
 *
 *
 * 	nj : 1010
 *     ny : 0100
 *     va : 0001
 *
 *     nj or ny
 *
 *
 * clustered index
 * 					[20]
 *     			/			  \
 * 		 [5, 10]   		     	[20, 30]
 *        /	|	\		     	/			\
 * [1, 3]<-> [5, 8]<->[10, 17] <->[20, 25] <-> [30, 50]
 *  |	 	   |
 * rowid 	rowid
 * name     name
 * state
 * gender
 *   1
 *   xx
 *   va
 *
 *
 * table partition (date range, state)
 *     disk
 *     va  [data..]
 *     nj  [data, data...]
 */

//--nested loop
//        -- for(int i = 0; i < n; i++) {
//        --        for(int j = 0; j < m; j++) {
//        --        	if(arr1[i] == arr2[j]) {
//        --        		...
//        --            }
//        --        }
//        --    }
//        --merge join
//        -- Arrays.sort(arr1); //len n
//        -- Arrays.sort(arr2); //len m
//        -- for(int i = 0, j = 0; i < arr1.length && j < arr2.length;) {
//        --     if(arr1[i] == arr2[j]) {
//        --         //
//        --         i++;
//        --         j++;
//        --     } else if(arr1[i] > arr2[j]) {
//        --         j++;
//        --     } else {
//        --         i++;
//        --     }
//        -- }
//
//        --hash join
//        -- [][][][][][][][][][] bucket
//        -- 1. key -> hashcode -> hashing value
//        -- 2. hashing value + len of bucket => index
//        -- 3. bucket[idx] -> merge / join
//
//
//
//        explain plan for
//        select * from hr.employees;
//        select * from table(DBMS_XPLAN.display(null));
//
//        explain plan for
//        select * from hr.employees e, hr.departments d where e.department_id = d.department_id;
//        select * from table(DBMS_XPLAN.display(null));
//
//        explain plan for
//        select /*+use_nl(d e) */ * from hr.employees e inner join hr.departments d on e.department_id = d.department_id;
//        select * from table(DBMS_XPLAN.display(null));
//
//
//        explain plan for
//        select /*+use_hash(d e) */ * from hr.employees e inner join hr.departments d on e.department_id = d.department_id;
//        select * from table(DBMS_XPLAN.display(null));
//
//
//        explain plan for
//        select /*+LEADING(e) use_merge(e d)*/ * from hr.employees e inner join hr.departments d on e.department_id = d.department_id;
//        select * from table(DBMS_XPLAN.display(null));
//
//        explain plan for
//        select /*+LEADING(e) use_merge(e d) index(e) index(d)*/ * from hr.employees e inner join hr.departments d on e.department_id = d.department_id;
//        select * from table(DBMS_XPLAN.display(null));
//
//        explain plan for
//        select /*+index(e) */ * from hr.employees e where employee_id >= 100;
//        select * from table(DBMS_XPLAN.display(null));
//
//        explain plan for
//        select * from hr.employees e where employee_id >= 100 and employee_id <= 101;
//        select * from table(DBMS_XPLAN.display(null));
//
//        explain plan for
//        select /*+ index_rs_asc(e) */ * from hr.employees e where employee_id = 100 or employee_id = 101;
//        select * from table(DBMS_XPLAN.display(null));
//
//        explain plan for
//        select /*+ full(e) */ employee_id from hr.employees e;
//        select * from table(DBMS_XPLAN.display(null));
//
/**
 *  Innodb
 *
 *          |
 *      Buffer pool (table data/ row / data)
 *
 *
 *      1. insert / update / delete
 *          update buffer pool
 *          update undo log(disk)
 *          update redo log (cache)
 *          update bin log(cache)
 *      2. commit transaction
 *          fsync (flush redo log cache -> redo log disk)
 *           |
 *         fsync (flush bin log cache -> bin log disk)
 *
 *
 */
