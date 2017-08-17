package cheungqzy.proxy;

import cheungqzy.reflect.Student;
import cheungqzy.reflect.StudentB;
import cheungqzy.reflect.logHelper;

import java.lang.reflect.Proxy;

/**
 * Created by Cheungqzy on 2017/6/6.
 */
public class MyProxy {

    public static void main(String[] args) {
        try {
            Class cl = Class.forName("cheungqzy.reflect.Student");
            Student student = new Student(1,"zq",1);
            StudentB studentB = new StudentB();
            MyInvocationHandler myInvocationHandler = new MyInvocationHandler(studentB);
            Object proxy = (Object) Proxy.newProxyInstance(cl.getClassLoader(),new Class[]{Class.forName("cheungqzy.reflect.logHelper")},myInvocationHandler);
            ((logHelper)proxy).log();
//            ((InterfaceB)proxy).testB();
//            proxy.getAge();
//           proxy.testB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
