package main;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池线程中断
 * Created by Cheungqzy on 2018/3/25.
 */
public class ThreadInterruptTest {

    public static ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

    public static void main(String[] args) {
        ThreadInterruptTest threadInterruptTest = new ThreadInterruptTest();

        String ni = null;
        try{
            ni = test();
        }catch (Exception e){

        }
        System.out.println(ni == null);
    }

    public static String test(){
        if(true)
            throw new RuntimeException();
        return "asd";
    }

    public ThreadInterruptTest(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor;
        threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(1);
        threadPoolTaskExecutor.setMaxPoolSize(1);
        threadPoolTaskExecutor.setKeepAliveSeconds(1);
        threadPoolTaskExecutor.setQueueCapacity(3);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        threadPoolTaskExecutor.initialize();

        Future<String> future = threadPoolTaskExecutor.submit(new Task1());
        future.cancel(true);
        Future<String> future1 = threadPoolTaskExecutor.submit(new Task1());
        Future<String> future2 = threadPoolTaskExecutor.submit(new Task1());
        try {
            System.out.println(future.get());
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}

class Task1 implements Callable<String> {
    @Override
    public String call() throws Exception {
        try{
            Thread.sleep(20000);
        }catch (Exception e){
        }
        return "nihao ";
    }

}