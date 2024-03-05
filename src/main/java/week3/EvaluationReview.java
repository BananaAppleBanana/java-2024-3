package week3;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Day 1:
 * what is oop
 * what is object? -> state and behaviors
 * poly -> overloading and overriding
 * encapsulation -> hiding information about your class
 * abstraction
 * abstract vs interface
 * Inheritance
 *
 * top interview question:
 * 1: abstract vs interface,
 * 2: what is oop
 * 3:what are diff between == and equals()
 * 4: string vs string builder vs string buffer
 * 5:dose java use pass-by-value or pass-by-reference?
 * 6: static class(method) vs non-static class(method)
 * 7:checked exception and uncheck exception
 * 8:exception vs error
 * 9:final vs finally vs finalize
 * 10: what is an immutable class
 * 11: hashmap before java 8 vs hashmap after java 8
 *
 *
 * primitive type ->
 * wrapper type ->
 *
 *
 * string vs string builder vs string buffer
 * access modifiers
 * public
 * protected
 * default
 * private
 *
 * this keyword
 * used to reference current object, as parameter, constructor
 * supper keyword
 * call parent's constructor
 * call parent's methods
 * pointing to parent class
 *
 * shallow copy vs deep copy ->
 *
 * dose java use pass-by-value or pass-by-reference?
 *
 *
 * exceptions
 * checked exception and uncheck exception
 *
 * exception vs error
 *
 * generic:
 * T,E,K,N,V
 *
 *
 * Day 2
 * top question:
 * array vs arraylist
 * arraylist vs linkedlist
 * TC: add, get remove in array list
 * HashMap vs concurrent hashmap
 * how does hashmap work?
 * how does hashing in hashmap work
 *
 *
 *
 *
 * linkedlist:
 * TC for add(), get, remove(index) add(index, element)
 *
 * deque: FILO and FIFO
 *
 * Queue -> array
 * max heap and heap
 *
 * HashMap
 *
 * hashset
 * hashset vs tree set
 * O(1) O(LOGN)
 * allowed null, does not null
 * Day 3
 * Top questions:
 * what is functional interface
 * can you give some examples about functional interface-> runnable, callable, comparator, cosumer, function, supplier
 * what is lambda expression
 * how do you use lambda expression
 * what is stream api
 * intermediate functions-> filter, map, flatmap, sorted...
 * terminal function-> foreach, reduce, min, max, count, anymacth, allmacth, nonematch
 * collections vs stream api
 * optional
 * what is optional
 * why do you use optional
 * what is optional.ofnull
 *
 * completable future vs future
 * what is method reference
 * how do you use method reference

 *
 * runAsync and supplyAsync
 *
 *
 * Day 4
 * Top questions:
 * what is CAS: compare and set
 * the process of CAS
 * what is concurrethashmap
 * concurrethashmap in java 7 vs java 8
 * what is blocking queue ->
 *
 * what is fixed thread pool
 * what is fork join pool
 * executor vs executor service vs executors
 *
 * Day5
 * Top questions:
 * what is JVM
 * what are components in JVM
 * stack vs heap in JVM
 * what is stack
 * what is heap
 * what are components in heap
 * what is young gene
 * what is old gene
 * what is gc roots
 * what are GC algorithm you know
 * normal deletion
 * normal deletion-compacting
 * CMS
 * G1
 * does gc always called with system.gc() -> no
 * thread states
 * new, ruannable, waiting, terminated
 *
 * sleep vs wait()
 * what is volatile
 * what is synchronized
 * pessimistic locker vs optimistic locker
 * Day 6
 * top questions:
 * what is tcp, upd, dns, http and http vs https
 * http: methods: get, post, put, delete...
 * status code, 1XX, 2XX,3XX,4XX,5XX
 * what is three way handshake
 * what are design pattern you know
 * singleton pattern
 * eager initialization
 * static block initialization
 * lazy initialization vs eager initialization
 * thread safe
 * bill pugh
 * prototype pattern
 * Factory
 * Day 7
 * what is spring mvc
 * Model, view, controller
 * what is rest controller
 * rest controller vs controller
 * what is requestBody, requestParam, pathvariable annotation
 * what is global exception
 * what is controlleradvice exception
 * what is exceptionhandler
 * what is spring ioc
 * what is DI
 * what are bean life cycles
 * bean scope
 * singleton, prototype, request, session..
 *
 *
 * day 8
 * JPA
 * TOP question
 * @service vs controller vs repository vs component annotation
 * what spring aop
 * join point, point cut
 * @before, @after, afterReturn..
 * what is spring validations
 * what are validation annotation you know
 * what TDD process
 *
 *
 *
 *
 *
 *
 * Fail Fast -> Concurrent modification Exception -> modCount (iterator)
 * java 8 stream api
 * List<Integer> list = new ArrayList<>();
 *      list.stream().filter(x -> x > 3).map(x -> x * 2).collect();
 *      ReferencePipeline(node) <-> ReferencePipeline(node) <-> ReferencePipeline(node)
 *      Sink(iterator) -> Sink(x -> x > 3) -> Sink(x -> x * 2) -> Sink(collect..)
 *
 * ReentrantLock ->
 *      lock multiple times
 *      Condition -> waiting list
 *      fairlock / unfairlock
 * Synchronized(obj) :  obj waiting list
 *
 * Threadpool + blocking queue
 *      1.ThreadPoolExecutor
 *            FixedThreadPool
 *            CachedThreadPool
 *      2. ScheduledThreadPool
 *              DelayedWorkQueue(heap / priorityQueue)
 *
 *         hour [][][Node] ..  []  24
 *               k
 *         min [][][][]  .. []   60
 *                 j
 *         sec [][] ..[Node][]  60
 *                            i
 *
 *     3. ForkJoinPool (stealing pool)
 *              [][][][]        T1  [][][][]
 *                              T2  [][][][]
 *
 * ThreadPool Size
 *      1. CPU based task -> 100% CPU,
 *      2. [80% CPU task][20% IO response]
 *          1 / (1 - 0.2) = 1 / 0.8 = 1.25
 *
 * Thread.join()
 *
 * Countdown Latch -> 50 -> countDown()
 * cyclicBarrier.
 * Semaphore
 *
 *
 *
 * Reflection
 *
 *
 *
 * Design pattern
 * Singleton -> reflection / cloneable issue / serialization issue
 *
 *
 * Spring Rest + coding
 *
 *  Tomorrow
 *      1. dynamic proxy
 *      2. customized annotation
 *      3. other design patterns
 *      4. spring rest + coding
 *      5. review homework
 * homework: Spring boot coding
 *      1.design , http method / status code / url / request body  response body..
 *        implement CRUD,  on white board (google doc link)
 *              get all users
 *              get user by id
 *              create user
 *              update user
 *              delete user
 *        create controller -> annotation
 *                  + exception handling
 *                  + implementation
 *                  + inject UserService(UserServiceImpl1,  UserServiceImpl2)
 *        <10min
 */
public class EvaluationReview {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for (int v: list) {
            list.add(1);
            list.add(5);
        }
    }


}

/**
 *  new Instance() ->
 *  Class.static
 *  Class.forName("...driver");
 */
class MyReflectionExample {

    public int ele = 10;

    public int getVal() {
        return 5;
    }

    public int getEle() {
        return ele;
    }
}


class MyReflectionTest {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = MyReflectionExample.class;
        MyReflectionExample instance = (MyReflectionExample)clazz.getDeclaredConstructor().newInstance();
        System.out.println(instance.getEle());
//        Class<?> clazz = MyReflectionExample.class;
        Field[] fields = clazz.getDeclaredFields();
        for(Field f: fields) {
            f.setAccessible(true);
            f.set(instance, 15);
        }
        Method[] methods = clazz.getDeclaredMethods();
        for(Method m: methods) {
            System.out.println(m.invoke(instance));
        }

        System.out.println(instance.getEle());


    }
}

class MyBlockingQueue<E> {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();
    private int size;
    private Object[] queue;
    private int start, end;
    public MyBlockingQueue(int len) {
        queue = new Object[len];
    }

    public E poll() {
        lock.lock();
        E ele = null;
        try {
            while(size == 0) {
                empty.await();
            }
            ele = (E)queue[start++];
            start %= queue.length;
            full.signal();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
        return ele;
    }

    public void offer(E e) {
        lock.lock();
        try {
            while(size == queue.length) {
                full.await();
            }
            queue[end++] = e;
            end %= queue.length;
            empty.signal();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class MySingleton implements Cloneable, Serializable {
    private static volatile MySingleton instance;

    private MySingleton() {
        //if != null , throw exception
    }

    public static MySingleton getInstance() {
        if(instance == null) {
            //T1, T2, T3
            synchronized (MySingleton.class) {
                if(instance == null) {
                    instance = new MySingleton();
                }
            }
        }
        return instance;
    }

    public Object clone() {
        //throw exception
        return null;
    }
}
