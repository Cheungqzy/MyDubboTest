package cheungqzy.thread;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Cheungqzy on 2017/6/12.
 */
public class ThreadTest {

    public static void main(String[] args) {
        ThreadTest test = new ThreadTest();
        List<Future<String>> list = new ArrayList<>();
        test.generate(5, list);
        test.doOtherThings();
        test.getResult(list);
    }

    public void generate(int threadNum, List<Future<String>> fList){
        ExecutorService service = Executors.newFixedThreadPool(threadNum);
        for(int i=0 ;i<threadNum;i++){
            Future<String> future = service.submit(getJob(i));
            fList.add(future);
        }
        service.shutdown();
    }

    public Callable<String> getJob(final int i) {
        final int time = new Random().nextInt(10);
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000 * time);
                return "thread - " + i +"  " + time;
            }
        };
    }

    public void doOtherThings() {
        try {
            for (int i = 0; i < 3; i++) {
                int temp = new Random().nextInt(5);
                System.out.println("do other thing no:" + i + "  "+temp);
                Thread.sleep(1000 * temp);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getResult(List<Future<String>> list){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(getCollectJob(list));
        service.shutdown();
    }

    public Runnable getCollectJob(final List<Future<String>> list){
        return new Runnable() {
            @Override
            public void run() {
                for(Future<String> future : list){
                    try{
                        while(true){
                            if(future.isDone() && !future.isCancelled()){
                                System.out.println("Future:" + JSON.toJSONString(future) + ",Result:" + future.get());
                                break;
                            }else{
                                Thread.sleep(1000);
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
    }

}
