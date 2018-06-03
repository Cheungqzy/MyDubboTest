package javassist;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Cheungqzy on 2018/5/4.
 */
public class JavassistExtendDemo {

    public static void main(String[] args) throws CannotCompileException, IOException, NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass stuClass = pool.makeClass("com.ricky.Student");
        //设置父类
        stuClass.setSuperclass(pool.get("javassist.Person"));
        //hobbies属性
        CtField ageField = new CtField(pool.getCtClass("java.util.List"), "hobbies", stuClass);
        stuClass.addField(ageField);
        Class<?> clazz = stuClass.toClass();
        System.out.println("class:"+clazz.getName());
        System.out.println("------------属性列表------------");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType()+"\t"+field.getName());
        }
        System.out.println("------------方法列表------------");
        Method[] methods = clazz.getMethods();
        for (Method method: methods){
            System.out.println(method.getReturnType()+"\t"+method.getName()+"\t"+Arrays.toString(method.getParameterTypes()));
        }

    }

}