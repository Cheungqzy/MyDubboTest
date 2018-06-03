package main;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Cheungqzy on 2018/5/31.
 */
public class CharMain {

    public static void main(String[] args) {
        String s = "a你好适藏";
        char[] chars = s.toCharArray();
        for (char ch : chars){
            System.out.println(ch + " " + (int)ch );
        }
//        System.out.println(" " + (char) 40869 + " " +(char)19968);
        getStr();
    }

    private static String getStr(){
        List<String> strs = Arrays.asList("asd", "ddd");
        Iterator<String> iterable = strs.iterator();

        while (iterable.hasNext()){
            String str = iterable.next();
            str = "xxx";
        }
        System.out.println(JSON.toJSONString(strs));
        return "";
    }

}

