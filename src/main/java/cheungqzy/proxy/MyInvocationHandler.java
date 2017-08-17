package cheungqzy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Cheungqzy on 2017/6/6.
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object o;

    public MyInvocationHandler(Object o){
        this.o = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---start--");
        Object result = method.invoke(o,args);
        System.out.println("----end---");
        return result;
    }

}
