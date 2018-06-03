package main;

import com.alibaba.fastjson.JSON;
import com.yonghui.common.constants.Constant;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Cheungqzy on 2017/8/22.
 */
public class MainTest  {


    public static void main(String[] args) {
        Student student1 = new Student("a","a","a");
        Student student2 = new Student("b","b","b");
        List<Student> test = new ArrayList<>();
        test.add(student1);
        test.add(student2);
        Map<String, String> map = test.stream().collect(Collectors.toMap(Student::getName, Student::getName1));
        System.out.println(map.keySet());
        System.out.println(map.get("nihaoasd"));
        System.out.println(ManagementFactory.getRuntimeMXBean());
    }

    public static void main2(String[] args) {
        char [] chars = "1.00".toCharArray();
        Boolean flag = true;

        double d = 1.010;
        System.out.println( d % 1.0 == 0 ? String.valueOf((long)d) : String.valueOf(d));
        System.out.println(",asd,".contains(",asd,"));
        String str = "123;";
        System.out.println(str.indexOf(";"));
        System.out.println(str.substring(str.indexOf(";")+1));

        System.out.println(new BigDecimal(0.0619).divide(new BigDecimal(2.60), Constant.DEFAULT_QTY_SCALE, BigDecimal.ROUND_HALF_EVEN));
        List<String> a = new ArrayList<>(), b = new ArrayList<>();
        a.add("a");
        b.add("b");
        String[] res = "asd;".split(";");
        System.out.println(res);
        System.out.println(a.toString() + " " + b.toString());
        System.out.println(new BigDecimal(11.454).setScale(Constant.DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP));
        System.out.println(MainTest.class.getSimpleName());
        System.out.println(System.currentTimeMillis());
        System.out.println("0123456789123".substring(7,12));
        System.out.println("012345678912345678".substring(12,17));
        String name = "niao";
        String name1 = "niao1";
        String name2 = "niao2";
        Student s1 = new Student(name1,name1,name2);
        Student s2 = new Student(name1,name1,name2);
        Map<String, Student> map = new HashMap<>();
        map.put("s1",s1);
        map.get("s1").setName("xxxxxxx");
        File file = new File("C:/tmp.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.toString());
        System.out.println(JSON.toJSONString(map));
    }

    public static void main1(String[] args) {
        MainTest mainTest = new MainTest();
        mainTest.test();
    }

    public void test(){
        List<String> list = new ArrayList();
        list.add("a");list.add("b");list.add("c");list.add("d");list.add("e");list.add("e");
        ThreadLocalMain threadLocalMain = new ThreadLocalMain();
        Class cl = ThreadLocalMain.class;
        for(int i=0 ; i < list.size(); i++ ) {
            try {
                Method method = cl.getMethod("setTitle"+ (i+1),String.class);
                method.invoke(threadLocalMain,list.get(i));
            }catch (NoSuchMethodException e){
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }catch (IllegalAccessException e){
                System.out.println(e.getMessage());
            }catch (InvocationTargetException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println(threadLocalMain.toString());
    }
}

class Student {
    public String name ;
    public String name1 ;
    public String name2 ;

    public Student(String v1,String v2,String v3){
        name = v1;name1=v2;name2=v3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
}
