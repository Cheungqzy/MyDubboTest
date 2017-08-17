package cheungqzy.thread;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Cheungqzy on 2017/8/17.
 */
public class ThreadDemo {
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.test4();
    }

    public void test1() {
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("cached");
                getTimeDelay1Hour();
            }
        });
    }

    public void test2(){
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("fixedn");
            }
        });
    }

    public void test3(){
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("schedule");
            }
        }, 3, TimeUnit.SECONDS);
    }

    public void test4(){ //getResult
        List<Future<Map<String, Object>>> list = new ArrayList<>();
        list.add(fixedThreadPool.submit(
                new Callable<Map<String, Object>>() {
                    @Override
                    public Map<String, Object> call() throws Exception {
                        Map map = new HashMap();
                        map.put("1","asd");
                        return map;
                    }
                }
        ));
        list.add(fixedThreadPool.submit(
                new Callable<Map<String, Object>>() {
                    @Override
                    public Map<String, Object> call() throws Exception {
                        Map map = new HashMap();
                        map.put("2","asd");
                        return map;
                    }
                }
        ));
        fixedThreadPool.shutdown();
        Map map = new HashMap();
        for(Future future : list) {
            while (true) {
                try {
                    if (future.isDone() && !future.isCancelled()) {
                        map.putAll((Map)future.get());break;
                    } else {
                        Thread.sleep(1000);
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println(JSON.toJSONString(map));
    }

    public void getTimeDelay1Hour(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(1, Calendar.HOUR);
        System.out.println(calendar.getTime());
    }



}
