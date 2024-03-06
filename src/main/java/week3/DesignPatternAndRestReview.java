package week3;

import java.lang.annotation.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 *  1. dynamic proxy
 *  *      2. customized annotation
 *  *      3. other design patterns
 *  *      4. review homework
 *         5. spring / rest coding
 */
class MyDynamicProxyTest {
    public static void main(String[] args) {
//        StudentService ss = new StudentServiceInheritanceProxy();
//        ss.printStudentInfo();

        StudentService ss = (StudentService)Proxy.newProxyInstance(
                MyDynamicProxyTest.class.getClassLoader(),
                new Class[]{StudentService.class},
                new StudentServiceInvocationHandler(new StudentServiceImpl())
        );
//        ss.printStudentInfo();
        System.out.println(ss.getStudentId());
    }
}

class StudentServiceInvocationHandler implements InvocationHandler {
    private final Object obj;

    public StudentServiceInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy : before impl");
        Object res = method.invoke(obj);
        System.out.println("proxy : after impl");
        return res;
    }
}

interface StudentService {
    void printStudentInfo();

    int getStudentId();
}
class StudentServiceImpl implements  StudentService {

    public void printStudentInfo() {
        System.out.println("this is student service");
    }

    @Override
    public int getStudentId() {
        return 10;
    }
}

//class StudentServiceStaticProxy implements StudentService {
//    private final StudentService ss;
//
//    public StudentServiceStaticProxy(StudentService studentServiceImpl) {
//        ss = studentServiceImpl;
//    }
//
//    @Override
//    public void printStudentInfo() {
//        System.out.println("before");
//        ss.printStudentInfo();
//        System.out.println("after");
//    }
//}

//class StudentServiceInheritanceProxy extends StudentServiceImpl implements StudentService {
//    @Override
//    public void printStudentInfo() {
//        System.out.println("before");
//        super.printStudentInfo();
//        System.out.println("after");
//    }
//}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@interface MyFirstAnnotation {
    String value() default "abc";
}

@MyFirstAnnotation
class MyFirstAnnotationTest {
    public static void main(String[] args) {
        Class<?> clazz = MyFirstAnnotationTest.class;
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for(Annotation annotation: annotations) {
            MyFirstAnnotation anno = (MyFirstAnnotation)annotation;
            System.out.println(anno.value());
        }
    }
}




/**
 *  Builder
 *
 *  Student.MyStudentBuilder().setName();
 *  new MyStudentBuilder().
 */
class MyStudentWeek3 {
    private String name;
    private int age;

    public MyStudentWeek3(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
class MyStudentBuilder {
    private String name;
    private int age;

    public MyStudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MyStudentBuilder setAge(int age) {
        this.age = age;
        return this;
    }
    public MyStudentWeek3 build() {
        return new MyStudentWeek3(name, age);
    }
}

/**
 * Observer (publisher - subscriber / topic)
 */
class MySubscriber {
    public void receive(String msg) {
        //...
    }
}
class Topic {
    private final List<MySubscriber> subscribers = new ArrayList<>();

    public void subscribe(MySubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void send(String msg) {
        for(MySubscriber sub: subscribers) {
            sub.receive(msg);
        }
    }
}

/**
 * inheritance : IS-A
 * composition : Has-A
 *          TreeNode {
 *              TreeNode;
 *          }
 * bridge
 * strategy
 */
@FunctionalInterface
interface CalculatorService {
    int calculate(int v1, int v2);
}
class MyCalculator {
    private int process(int v1, int v2, CalculatorService calculatorService) {
        return calculatorService.calculate(v1, v2);
    }

    public static void main(String[] args) {
        MyCalculator myCalculator = new MyCalculator();
        System.out.println(myCalculator.process(1, 5, (v1, v2) -> v1 + v2));
    }
}

/**
 *  throws multiple exception at same time?
 */


/**
 *   Rest , CRUD Users
 *   get all users
 *      url: /users?age=,name=
 *      method: get
 *      status: OK,  400, 500
 *      response body: list of users (json)
 *   get user by id
 *      url :/users/{id}
 *      ..
 *      status: OK, 404, 500
 *      ..
 *   post user
 *      url: /users
 *      method: post
 *      status: 201
 *      request body:  {user info,  no id}
 *      response body:  {user id}
 *   update
 *      url: /users/{id}
 *      method: put
 *      status: 204 / 200
 *      request body: {user info}
 *      response body: no content
 *   delete
 *      url: /users/{id}
 *      method: delete
 *      ..
 *
 *   1. @Autowired annotation
 *      constructor injection ,  private final field
 *   2. @RequestMapping("/users")
 *   3. ResponseEntity + diff status code
 *   4. @ExceptionHandler
 *
 *
 *
 *   homework
 *      1. google RestTemplate
 *      2. create spring boot project
 *         configure rest template in configuration class
 *         inject rest template in service class
 *         send request to "3rd party url" , get response
 *         return to controller layer  -> return to user
 *         /xx
 *         1. get all
 *         2. get all,  request parameter
 *         < 15min
 *
 *         github
 *
 *   Tomorrow
 *      1. Spring Security
 *      2. authentication authorization JWT
 *      3. HTTPS
 *      4. CORS
 *      5. CSRF
 *      6. SQL Injection
 *      7. ...
 */

