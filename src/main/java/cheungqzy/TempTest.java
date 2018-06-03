package cheungqzy;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cheungqzy on 2017/6/14.
 */
public class TempTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add("asd");
        list.removeAll(list1);
        list.addAll(list1);
        System.out.println(list);
    }

    public static void main1(String[] args) {

        /*System.out.println(Integer.parseInt("00"));

        System.out.println(new BigDecimal(100.52).multiply(new BigDecimal(100)).setScale(2,4).intValue());
        System.out.println(new BigDecimal(100.22).intValue());

        System.out.println(new BigDecimal(0.22).setScale(2,4).doubleValue() * 100);

        System.out.println("asd".contains(""));

        System.out.println(new BigDecimal(0).compareTo(new BigDecimal(0))==1);*/
//        System.out.println(timeToString(StringToTime("20:00") - 30));

        byte a = 0;

        System.out.println();

        BigDecimal bigDecimal = new BigDecimal(1.333443);

        System.out.println(bigDecimal.divide(new BigDecimal(1), 4 , BigDecimal.ROUND_UP));

//        System.out.println(randomFileName("asd.zip","asdasd"));

        System.out.println(new BigDecimal(1.3131).setScale(2, BigDecimal.ROUND_DOWN));
        System.out.println(new BigDecimal(1.000).compareTo(new BigDecimal(1.0)));
    }


    public static String randomFileName(String originalName, String md5) {
        String ext[] = originalName.split("\\.");
        System.out.println(JSON.toJSONString(ext));
        System.out.println(ext[ext.length - 1]);
        return md5 + "." + ext[ext.length - 1];

    }


    public static String timeToString(Integer integer){
        Integer ours = integer/60;
        Integer  minute = integer%60;
        String res;
        if(ours <= 0)
            res = "00";
        else if(ours < 10){
            res = "0" + ours;
        }else
            res = String.valueOf(ours);

        if(minute <= 0)
            res += ":00";
        else if(minute < 10){
            res += ":0" + minute;
        }else
            res += ":" + String.valueOf(minute);
        return res;
    }

    public static Integer StringToTime(String time){
        String[] arg = time.split(":");
        return Integer.parseInt(arg[0]) * 60 + Integer.parseInt(arg[1]);
    }
}
