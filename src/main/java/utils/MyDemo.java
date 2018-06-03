package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.io.BufferedReader;
import java.io.File;

/**
 * Created by Cheungqzy on 2018/4/3.
 */
public class MyDemo extends Thread implements Runnable {

    @Override
    public void run() {
        System.out.println("Asd");
    }

    public static void main(String arg[]){
        Long lnum = null;
        System.out.println(lnum + "_" +2L);
        String nihao = "asdasd*asdsa";
        String[] s = "_sad".split("_", 2);
        System.out.println(JSON.toJSONString(s));
        System.out.println(JSON.toJSONString("sad_".split("_", 2)));
        System.out.println(JSONObject.toJSONString(nihao.split("\\*")));

        System.out.println(MyDemo.class.getSimpleName());
        JdkSerializationRedisSerializer js = new JdkSerializationRedisSerializer(MyDemo.class.getClassLoader());

        File file = new File("C:\\Users\\Cheungqzy\\Desktop\\asd.txt");  // CSV文件路径
        BufferedReader br = null;
        try
        {
            /*br = new BufferedReader(new FileReader(file));
            String test = br.readLine();
            List<Map<String, Object>> res = (List<Map<String, Object>>)js.deserialize(test.getBytes());
            System.out.println(JSONObject.toJSONString(res));
            */
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
