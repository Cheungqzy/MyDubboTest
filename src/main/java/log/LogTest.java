package log;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Cheungqzy on 2018/5/3.
 */
public class LogTest {

    public static void main(String[] args) {
        MDC.put("traceId", "main");
        ThreadTest threadTest = new ThreadTest("A");
        ThreadTest threadTest1 = new ThreadTest("B");
        threadTest.setContextClassLoader(LogTest.class.getClassLoader());
        threadTest.run();
        threadTest1.run();
    }

}

class ThreadTest extends Thread {

    private static Logger logger = LoggerFactory.getLogger(ThreadTest.class);

    private String name;

    public ThreadTest(String name){
        this.name = name;
    }

    @Override
    public void run() {
        MDC.put("username", name);
        try {
            Thread.sleep(5000);
            MDC.put("traceId", name);
            logger.info("xxxxxxxxxxxx");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}