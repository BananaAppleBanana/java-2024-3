package week3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 *  SOLID
 *
 *  Single Responsibility
 *      StudentService {
 *           X employee data  / service
 *           X student pojo / name / field
 *      }
 *
 *  Open - Close
 *      /v1/users
 *      new features  /v2/users
 *          open to extend
 *          close to modify
 *
 *  Liskov substitution
 *      class Pizza {}
 *      class Car extends Pizza {}
 *
 *  Interface segregation
 *      interface Car {
 *          100x function
 *      }
 *
 *   Dependency inversion
 *      class EmployeeController {
 *          private final EmployeeService(interface) emp;
 *      }
 *
 *
 *      interface..
 *
 *
 *      class EmployeeImpl1 implements EmployeeService {}
 *      class EmployeeImpl2 implements EmployeeService {}
 *
 *
 *  package structure
 *      university package
 *          UController
 *          UService
 *          UPojo
 *          URepo
 *
 *
 *      controller
 *      service
 *      repo / dao
 *      pojo / domain (entity , dto)
 *      config
 *
 *
 *
 * restTemplate.getForObject(url, University[].class
 */
class CompletableFutureExample {
    public static void main(String[] args) {
        List<CompletableFuture<Integer>> futureList = new ArrayList<>();
        futureList.add(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 5;
        }));
        for(int i = 0; i < 4; i++) {
            final int x = i;
            futureList.add(CompletableFuture.supplyAsync(() -> x));
        }
        CompletableFuture<Void> res = CompletableFuture
                .allOf(futureList.toArray(new CompletableFuture[0]))
                .orTimeout(2, TimeUnit.SECONDS);
        res.thenAccept(VOID -> futureList.stream().map(CompletableFuture::join).forEach(System.out::println));
        res.join();
    }
}

/**
 * @Service
 * class A ..{
 *      @Async
 *      fun1() {}
 *
 *      func2() { this.func1(); }
 * }
 *
 * class A Proxy instance(multithreading in invoke , method = fun1()) -> class A instance
 * class B call
 *          injection class A.func1();
 *          new A().func1();
 *
 *
 * class Bean {
 *     private final Bean2 b = new Bean2();
 * }
 *
 *
 * code review
 *  1. package / class naming style   / api design
 *     OOD , abstract class / interface
 *  2. hard code
 *  3. exception
 *  4. log
 *  5. security
 *  6. logic / function issues
 *  7. time complexity / space complexity
 *  8. multithreading
 *  9. memory leak
 *
 *
 *  GC -> out of memory + memory leak
 *  tuning
 *
 *
 *  1. UsernamePasswordAuthenticationFilter / AbstractAuthenticationProcessingFilter
 *  2. AuthenticationManager / ProviderManager
 *  3. DaoAuthenticationProvider / AbstractUserDetailsAuthenticationProvider
 *  4. SecurityContext (ThreadLocal)
 *  5. UserDetails
 *  6. UserDetailsService
 *
 *
 */