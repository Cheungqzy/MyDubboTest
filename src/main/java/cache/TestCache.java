package cache;

import com.alibaba.fastjson.JSON;
import com.yonghui.common.util.DateUtil;
import com.yonghui.thirdparty.api.sms.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Cheungqzy on 2017/9/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext-test.xml", "/applicationContext-dubbo.xml"})
public class TestCache{

    private static Logger logger = LoggerFactory.getLogger(TestCache.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private Sender marketingSender;
    @Autowired
    private Sender industrySender;

    @Test
    public void test4(){
        String key = "nihaoa";
        System.out.println(redisTemplate.opsForValue().increment(key, 1L));
        redisTemplate.expire(key, 10, TimeUnit.MINUTES);
        //System.out.println(redisTemplate.opsForValue().get(key));
    }

    @Test
    public void test5(){
        try {
            String key = "nihaoa";
            redisTemplate.opsForValue().get(key);
            System.out.println(JSON.toJSONString(""));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //System.out.println(redisTemplate.opsForValue().get(key));
    }


    @Test
    public void test1(){
        List<Date> res = (List<Date>)redisTemplate.opsForValue().get("x");
        if(res == null) {
            res = new ArrayList<>();
            res.add(new Date(new Date().getTime() + 60000));
            redisTemplate.opsForValue().set("x",res);
            redisTemplate.expireAt("x", getMinDate(res));
            System.out.println("-->添加成功 " + JSON.toJSONString(res));
        }else if(res.size() < 5){
            res.add(new Date(new Date().getTime()+60000));
            redisTemplate.opsForValue().set("x",res);
            redisTemplate.expireAt("x", getMinDate(res));
            System.out.println("-->添加成功 " + JSON.toJSONString(res));
        }else {
            Calendar calendar = Calendar.getInstance();
            System.out.println("超过上限，时间为：" + DateUtil.formatDate(new Date()) + "过期时间 " + DateUtil.formatDate(getMinDate(res)));
        }
    }

    @Test
    public void test3() {
        for(int i=0 ; i< 30; i++){
            test1();
        }
    }

    @Test
    public void test2() {
        for(int i=0;i<1000;i++) {
            try {
                Thread.sleep(200);
            }catch (Exception e){

            }
            List<String> list = new ArrayList<>();
            list.add("18502552294");
            industrySender.sender(list, "测试短信");
        }
    }

    public Date getMinDate(List<Date> list){
        Date min = list.get(0);
        for(int i=1 ;i < list.size(); i++ ){
            if(min.compareTo(list.get(i)) > 0){
                min = list.get(i);
            }
        }
        return min;
    }

}
