package javassist;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;


public class JavassistDemo {

    public static void main(String[] args) throws CannotCompileException, IOException, NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass stuClass = pool.makeClass("com.ricky.Student"); //定义类
//      CtClass cc =  pool.get(classname); //加载类
        CtField idField = new CtField(CtClass.longType, "id", stuClass);
        stuClass.addField(idField);
        CtField nameField = new CtField(pool.get("java.lang.String"), "name", stuClass);
        stuClass.addField(nameField);
        CtField ageField = new CtField(CtClass.intType, "age", stuClass);
        stuClass.addField(ageField);
        CtMethod getMethod = CtNewMethod.make("public int getAge() { return this.age;}", stuClass);
        CtMethod setMethod = CtNewMethod.make("public void setAge(int age) { this.age = age;}", stuClass);
        stuClass.addMethod(getMethod);
        stuClass.addMethod(setMethod);
//        stuClass.writeFile("F:\\Practice_Demo");
        Class<?> clazz = stuClass.toClass();
        System.out.println("class:"+clazz.getName());
        System.out.println("------------属性列表------------");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType()+"\t"+field.getName());
        }
        System.out.println("------------方法列表------------");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method: methods){
            System.out.println(method.getReturnType()+"\t"+method.getName()+"\t"+Arrays.toString(method.getParameterTypes()));
        }

    }

}