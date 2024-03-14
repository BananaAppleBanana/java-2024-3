package week4;

/**
 *
 *         // store the SQL statement in a string
 *         String QUERY = "select * from employee_details";
 *         //register the oracle driver with DriverManager
 *         Class.forName("oracle.jdbc.driver.OracleDriver");
 *         //Here we have used Java 8 so opening the connection in try statement
 *         try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:system/pass123@localhost:1521:XE"))
 *         {
 *                 Statement statemnt1 = conn.createStatement();
 *                 //Created statement and execute it
 *                 ResultSet rs1 = statemnt1.executeQuery(QUERY);
 *                 {
 *                     //Get the values of the record using while loop
 *                     while(rs1.next())
 *                     {
 *                         int empNum = rs1.getInt("empNum");
 *                                      getInt() => return int
 *                                      getInt() => return Integer
 *                                      getInteger() => return Integer
 *
 *                         String lastName = rs1.getString("lastName");
 *                         String firstName = rs1.getString("firstName");
 *                         String email = rs1.getString("email");
 *                         String deptNum = rs1.getString("deptNum");
 *                         String salary = rs1.getString("salary");
 *                         //store the values which are retrieved using ResultSet and print it
 *                     System.out.println(empNum + "," +lastName+ "," +firstName+ "," +email +","+deptNum +"," +salary);
 *                     }
 *                 }
 *         }
 *         catch (SQLException e) {
 *             //If exception occurs catch it and exit the program
 *             e.printStackTrace();
 *         }
 */
/**
 *  why not using jdbc, why hibernate
 *  1. ResultSet -> reflection mapping
 *  2. hard to change database impl
 *      a. interface -> diff impl (polymorphism)
 *      b. centralized query -> compiled -> oracle query / mysql query
 *  3. connection pool
 *      blocking queue (free connections)
 *      blocking queue (used connections)
 *  4. sql injection
 *      "select .. from xx where username =" + user input + "and password = ?"
 *      user input 'xx' : "\'xyz\' or true"
 *      user input 'xx' : ; drop table;
 *
 *      a. preparedStatement
 *          set(1, 'xx') :
 *              user input 'xx' : "\'xyz\' or true"
 *              select .. from xx where username = "\'xyz\' or true"
 *      b. frontend -> validation
 *      c. backend  -> validation
 *  5. cache
 *  6. dynamic query -> builder
 *      a. criteria query
 *
 *
 *
 *  Tomorrow : hibernate
 *  1. hibernate
 *  2. jpa , spring data jpa
 *  3. lazy loading , eager loading (N + 1 problem)
 *  4. how to use hibernate / jpa / how to write hibernate implementation (classes)
 *  5. demo
 *
 *
 *  Next Week
 *      Microservice (services / message queue / database clusters / AWS / deployment / CI/CD)
 *
 *
 *
 *  Homework
 *  1. use hibernate session / jpa entity manager  , CRUD , emp / dept
 *  2. create rest api CRUD
 *  3. spring data jpa crud  (@Query, @Modifying)
 *  4. impl criteria query -> select from xx where emp age > ? and dept name = ?
 *  5. @transactional -> transaction propagation
 *
 */
