package week4;

/**
 *  ORM
 *  hibernate, jpa(standard) , spring data jpa
 *  datasource 1 to 1 session factory 1 to many session
 *  entity manager(jpa) vs session(hibernate)
 *
 *  persist vs merge
 *      1. persist = insert without id
 *      2. merge = if db find row with given id -> update
 *                 if not -> persist
 *  object status
 *      1. transient (new instance)
 *      2. proxy / attached
 *      3. un-proxy / detach    ->  lazy initialization exception
 *  annotations + entity
 *  lazy loading, eager loading
 *  session, session factory
 *      1. 1st level cache : session
 *      2. 2nd level cache : session factory
 *  how to use hibernate / jpa / how to write hibernate implementation (classes)
 *      1. configure dependency (pom xml)
 *      2. configure data source (application.properties)
 *      3. entity class (1 - m / m - 1 / m - m) :  @Entity / @OneToMany / @Column / @JoinColumn..
 *      4. repository interface + implementation(inject entity manager / session)
 *      5. @Transactional (in service layer)
 *
 *
 *
 *     Query query = em.createQuery("select s from Student s where s.id = ?1");
 *     Query query = em.createQuery("select s from Student s join fetch s.teacher_students ts where s.id = ?1");
 *     query.setParameter(1, "17");
 *     Student s = (Student)query.getSingleResult();
 *
 *      spring.datasource.url=jdbc:postgresql://localhost:5432/university
 *      spring.datasource.password=password
 *      spring.datasource.username=postgres
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 * A 1 - m B
 * A (id, ..)
 * B (id, ..,  a_id)
 * @Entity
 * @Table(..)
 * @Data
 * class A {
 *      @Id
 *      @GeneratedType(strategy=..)
 *      private String id;
 *      @Column
 *      private String name;
 *      @OneToMany(mappedBy="a_ref_name", fetchType=..)
 *      private List<B> bList;
 * }
 *
 * @Entity
 * @Table()
 * @Data
 * class B {
 *     @ManyToOne(fetchType=..)
 *     @JoinColumn(?=a_id)
 *     private A a_ref_name;
 * }
 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  lazy loading
 *  List<A> aList = em.createQuery("select a from A a join fetch a.bList").get?Result();
 *  for(A a: aList) {
 *      List<B> bList = a.getBList();
 *      //trigger function from bList
 *  }
 *  eager loading
 *  List<A> aList = em.createQuery("select a from A a").get?Result();
 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @Transactional -> service layer
 *      transaction propagation
 *
 * methodA () {
 *      /..
 *      methodB(); -> throw exception , rollback A ?
 *      /..
 * }
 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Spring Data Jpa
 *
 * @Repository
 * public interface ARepository extends JpaRepository<A, String>, MyInterface {
 *
 *      @Query()
 *      ....
 *
 *      .... findXXByIdAndName
 * }
 *
 * interface MyInterface
 * ... implements MyInterface {
 *     ....
 * }
 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * *  Homework
 * emp(id, name, age, salary, dept_id)
 * dept(id, name)
 *  *  1. use hibernate session / jpa entity manager  , CRUD , emp / dept
 *  *  2. create rest api CRUD
 *  *  3. spring data jpa crud  (@Query, @Modifying)
 *  *  4. impl criteria query(dynamic query) -> select from xx where emp age > ? and dept name = ?
 *  *  5. @transactional -> transaction propagation
 *
 *
 *  - controller
 *  - service
 *      -impl
 *      ..
 *  - repository
 *      -impl
 *      ..
 *  - utility
 *      ..
 *  - domain / pojo
 *      -dto
 *      -entity
 *  - exception
 *      ..
 *
 *
 *   class A , class B
 *   1 - m m - 1
 *
 *   class Junctiontable {  id,  a_id, b_id
 *       id(pk)
 *       ,.,
 *       ...
 *   }
 *
 *   juntable -> extra column
 */