package main;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cheungqzy on 2017/6/23.
 */
public class TestUtils {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.keySet().forEach(ele->{
            System.out.println(ele.toString());
        });
    }

}
