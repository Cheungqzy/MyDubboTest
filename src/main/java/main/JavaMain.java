package main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.ImmutableMap;

import javax.com.yonghui.common.exception.log.ExceptionLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by Cheungqzy on 2017/6/28.
 */
public class JavaMain {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("nihao","niaho");
        map.put("nihao1","niah1");
        List<String> list1 = Arrays.asList("asd","asd");
        System.out.println(UUID.randomUUID().getLeastSignificantBits());
        System.out.println("X-1830".contains(":"));
        JSONObject.parseObject("");
        File file = new File("src\\main\\resources\\aaa.txt");
        try {
            System.out.println("123");
            InputStreamReader inr = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(inr);
            String line;
            line = br.readLine();
            System.out.println(line.length());
            Map<String, String> res = JSONObject.parseObject(line, Map.class);
            System.out.println(res.size());
            line = br.readLine();
            System.out.println(line.length());
            res = JSONObject.parseObject(line, Map.class);
            System.out.println(res.size());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(new Date().getTime());
    }

    public void test(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1231321");
            }
        });
//        executorService.shutdown();
        try {
            for (int i=0;i < 2;i++){
                Thread.sleep(5000);
                System.out.println("GC开始");
                System.gc();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void mai1(String[] args) {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(new Date());
        Date date= null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date.getTime());
        System.out.println((""+null).hashCode());

        Map map = new HashMap<>();

        System.out.println(Boolean.valueOf("1"));

        System.out.println(null instanceof Collection);

        List<Integer> a1 = new ArrayList<>(), a2 = new ArrayList<>();
        a1.add(1);a1.add(2);a1.add(3);a1.add(4);
        a2.add(2);a1.add(6);

        List<Integer> temp = a1.stream().map(s-> s+1).collect(Collectors.toList());

        Integer[] res = a1.toArray(new Integer[a1.size()]);
        System.out.println(JSON.toJSONString(res));
        Map<String,Object> map1 = new HashMap();
        map1.put("nihao",null);
        System.out.println(
                JSONObject.toJSONString(
                        ImmutableMap.of("doc", map1, "doc_as_upsert", true), SerializerFeature.WriteMapNullValue)
        );
        System.out.println("0123456".substring(0,4));
    }

    private static  List<Integer> listCombine(List<Integer> a1, List<Integer> a2){
        Integer tempa1 =null,tempa2=null;
        List<Integer> result = new ArrayList<>();
        while (a1.size() > 0 || a2.size()>0){
            if(tempa1==null&&a1.size()>0){
                tempa1 = a1.remove(0);
            }
            if(tempa2==null&&a2.size()>0){
                tempa2 = a2.remove(0);
            }
            if (tempa1==null&&tempa2!=null) {
                result.add(tempa2);
                tempa2=null;
            }else if(tempa1!=null&&tempa2==null){
                result.add(tempa1);
                tempa1=null;
            }else {
                if (tempa1 < tempa2) {
                    result.add(tempa1);
                    tempa1=null;
                } else {
                    result.add(tempa2);
                    tempa2=null;
                }
            }
        }
        return result;
    }

    static  class A{
        String a;
        String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
   static class B{
        String a;
        Integer b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }
    }

    public static boolean checkPayerTax(String taxcode){
        try {
            if(taxcode.indexOf("91") == 0 || taxcode.indexOf("92") == 0 || taxcode.indexOf("93") == 0){	//判断是否为91,92,93开头
                if(taxcode.length() == 18){	//是否满足18位
                    //统一社会信用代码前十七位基数数组
                    int[] taxcodeBaseArray =  new int[taxcode.length()-1];
                    for(int i = 0; i < taxcode.length() - 1; i++){
                        switch(taxcode.charAt(i)){
                            case '0' : taxcodeBaseArray[i]=0; break;
                            case '1' : taxcodeBaseArray[i]=1; break;
                            case '2' : taxcodeBaseArray[i]=2; break;
                            case '3' : taxcodeBaseArray[i]=3; break;
                            case '4' : taxcodeBaseArray[i]=4; break;
                            case '5' : taxcodeBaseArray[i]=5; break;
                            case '6' : taxcodeBaseArray[i]=6; break;
                            case '7' : taxcodeBaseArray[i]=7; break;
                            case '8' : taxcodeBaseArray[i]=8; break;
                            case '9' : taxcodeBaseArray[i]=9; break;
                            case 'A' : taxcodeBaseArray[i]=10; break;
                            case 'B' : taxcodeBaseArray[i]=11; break;
                            case 'C' : taxcodeBaseArray[i]=12; break;
                            case 'D' : taxcodeBaseArray[i]=13; break;
                            case 'E' : taxcodeBaseArray[i]=14; break;
                            case 'F' : taxcodeBaseArray[i]=15; break;
                            case 'G' : taxcodeBaseArray[i]=16; break;
                            case 'H' : taxcodeBaseArray[i]=17; break;
                            case 'J' : taxcodeBaseArray[i]=18; break;
                            case 'K' : taxcodeBaseArray[i]=19; break;
                            case 'L' : taxcodeBaseArray[i]=20; break;
                            case 'M' : taxcodeBaseArray[i]=21; break;
                            case 'N' : taxcodeBaseArray[i]=22; break;
                            case 'P' : taxcodeBaseArray[i]=23; break;
                            case 'Q' : taxcodeBaseArray[i]=24; break;
                            case 'R' : taxcodeBaseArray[i]=25; break;
                            case 'T' : taxcodeBaseArray[i]=26; break;
                            case 'U' : taxcodeBaseArray[i]=27; break;
                            case 'W' : taxcodeBaseArray[i]=28; break;
                            case 'X' : taxcodeBaseArray[i]=29; break;
                            case 'Y' : taxcodeBaseArray[i]=30; break;
                            default  : return false;
                        }
                    }
                    //加权因子数值数组
                    int[] weightedFactorArray = {1,3,9,27,19,26,16,17,20,29,25,13,8,24,10,30,28};
                    int sum = 0;
                    //基数与对应位数的因子数值相乘并求和
                    for(int i = 0; i < taxcodeBaseArray.length; i++){
                        sum = sum + taxcodeBaseArray[i] * weightedFactorArray[i];
                    }
                    //余数 = 和数除以31求余
                    int remainder = sum % 31;
                    //校验码的数值 = 阿拉伯数字31减去余数
                    int checkCodeNum = 31 - remainder;
                    String checkCode = "";
                    switch(checkCodeNum){
                        case 31 : checkCode ="0"; break;
                        case 1 : checkCode ="1"; break;
                        case 2 : checkCode ="2"; break;
                        case 3 : checkCode ="3"; break;
                        case 4 : checkCode ="4"; break;
                        case 5 : checkCode ="5"; break;
                        case 6 : checkCode ="6"; break;
                        case 7 : checkCode ="7"; break;
                        case 8 : checkCode ="8"; break;
                        case 9 : checkCode ="9"; break;
                        case 10 : checkCode ="A"; break;
                        case 11 : checkCode ="B"; break;
                        case 12 : checkCode ="C"; break;
                        case 13 : checkCode ="D"; break;
                        case 14 : checkCode ="E"; break;
                        case 15 : checkCode ="F"; break;
                        case 16 : checkCode ="G"; break;
                        case 17 : checkCode ="H"; break;
                        case 18 : checkCode ="J"; break;
                        case 19 : checkCode ="K"; break;
                        case 20 : checkCode ="L"; break;
                        case 21 : checkCode ="M"; break;
                        case 22 : checkCode ="N"; break;
                        case 23 : checkCode ="P"; break;
                        case 24 : checkCode ="Q"; break;
                        case 25 : checkCode ="R"; break;
                        case 26 : checkCode ="T"; break;
                        case 27 : checkCode ="U"; break;
                        case 28 : checkCode ="W"; break;
                        case 29 : checkCode ="X"; break;
                        case 30 : checkCode ="Y"; break;
                        default  : return false;
                    }
                    System.out.println(checkCode);
                    //判断校验位是否正确
                    if(checkCode.equals(taxcode.substring(17,18))){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                return true;
            }
        }catch (Exception e){
            ExceptionLogger.log(e);
            return true;
        }

    }

    /**
     * Created by Cheungqzy on 2017/8/22.
     */
    public static class MyMainTest {
        public static void main(String[] args) {
            List<String> strSplit = new ArrayList<>();
            strSplit.add("a");
            strSplit.add(0,"b");
            System.out.println(strSplit);
        }
    }
}
