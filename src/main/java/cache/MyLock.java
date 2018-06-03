package cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Cheungqzy on 2017/9/7.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext-dubbo.xml","/applicationContext-cache.xml"})
public class MyLock {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    FrequencyCacheManager frequencyCacheManager;

    @Test
    public void test(){
        String key = "hello";
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        System.out.println(counter.getAndAdd(1));
        counter.expireAt(new Date(new Date().getTime() + 1000000));
        try {
            String keys = "keyasd";
            frequencyCacheManager.get(keys);
            frequencyCacheManager.increase(keys);
            frequencyCacheManager.get(keys);
            System.out.println(counter.get());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
