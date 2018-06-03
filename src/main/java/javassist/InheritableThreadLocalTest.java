package javassist;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Cheungqzy on 2018/5/4.
 */
public class InheritableThreadLocalTest {

    public static void main(String[] args) {
        final InheritableThreadLocal inheritableThreadLocal=new InheritableThreadLocal();
        inheritableThreadLocal.set("hello");
        final InheritableThreadLocal inheritableThreadLocal1=new InheritableThreadLocal();
        inheritableThreadLocal1.set("hello");
        final ThreadLocal threadLocal2 = new ThreadLocal();
        threadLocal2.set("hello 2");
        new Thread(new Runnable() {
            InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();
            @Override
            public void run() {
                System.out.println(inheritableThreadLocal.get());
                System.out.println(inheritableThreadLocal1.get());
                System.out.println(threadLocal2.get());
            }
        }).start();
    }

    public static void main1(String[] args) {
        final InheritableThreadLocal<Span> inheritableThreadLocal = new InheritableThreadLocal<Span>();
        inheritableThreadLocal.set(new Span("xiexiexie"));
        //输出 xiexiexie
        Object o = inheritableThreadLocal.get();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("========");
                inheritableThreadLocal.get();
                inheritableThreadLocal.set(new Span("zhangzhangzhang"));
                inheritableThreadLocal.get();
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            executorService.submit(runnable);
            TimeUnit.SECONDS.sleep(1);
            executorService.submit(runnable);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("========");
        Span span = inheritableThreadLocal.get();

    }
    static class Span {
        public String name;
        public int age;
        public Span(String name) {
            this.name = name;
        }
    }

}
