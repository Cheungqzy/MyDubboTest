package main;

import com.alibaba.fastjson.JSON;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Cheungqzy on 2018/3/21.
 */
public class ThreadTest {

    public static ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

    public ThreadTest(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor;
        threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(1);
        threadPoolTaskExecutor.setMaxPoolSize(2);
        threadPoolTaskExecutor.setKeepAliveSeconds(1);
        threadPoolTaskExecutor.setQueueCapacity(2);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        threadPoolTaskExecutor.initialize();
        int sleepTime = 100, futureSize = 5, cSleepTime = 8000;
        new Thread(() -> {
            System.out.println("start A");
                List<Future<String>> futures = new ArrayList<>();
                for(int i=0; i< futureSize; i++) {
                    try{
                        Thread.sleep(sleepTime);
                        Future<String> future = threadPoolTaskExecutor.submit(new Task());
                        futures.add(future);
                    }catch (Exception e){
                        System.out.println("          A ----- " + e.getCause()); //
                    }
                }
                List<String> res = new ArrayList<>();
                for(Future<String> temp : futures){
                    try {
                        res.add(temp.get());
                    }catch (Exception e){
                        System.out.println("A result exception "); // + e.getCause()
                    }
                }
                System.out.println("A " + JSON.toJSONString(res) + " " + res.size());
            }
        ).start();

        new Thread(() -> {
            System.out.println("start B");
            List<Future<String>> futures = new ArrayList<>();
            for(int i=0; i< futureSize; i++) {
                try{
                    Thread.sleep(sleepTime);
                    Future<String> future = threadPoolTaskExecutor.submit(new Task());
                    futures.add(future);
                }catch (Exception e){
                    System.out.println("B ++++++ " + e.getCause());
                }
            }
            List<String> res = new ArrayList<>();
            for(Future<String> temp : futures){
                try {
                    res.add(temp.get());
                }catch (Exception e){
                    System.out.println("B result exception ");
                }
            }
            System.out.println("B " + JSON.toJSONString(res) + " " + res.size());
        }).start();
        try{
            Thread.sleep(cSleepTime);
            new Thread(() -> {
                System.out.println("start C");
                List<Future<String>> futures = new ArrayList<>();
                for(int i=0; i< futureSize; i++) {
                    try{
                        Thread.sleep(sleepTime);
                        Future<String> future = threadPoolTaskExecutor.submit(new Task());
                        futures.add(future);
                    }catch (Exception e){
                        System.out.println("c ++++++ ");
                    }
                }
                List<String> res = new ArrayList<>();
                for(Future<String> temp : futures){
                    try {
                        res.add(temp.get());
                    }catch (Exception e){
                        System.out.println("c result exception ");
                    }
                }
                System.out.println("c " + JSON.toJSONString(res) + " " + res.size());
            }).start();
        }catch (Exception e){

        }

    }

    public ThreadPoolTaskExecutor getThreadPool(){
        return null;
    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
    }

}
class Task implements Callable<String>{
    @Override
    public String call() throws Exception {
        try{
            Thread.sleep(1000);
        }catch (Exception e){
        }
        return "nihao ";
    }

}