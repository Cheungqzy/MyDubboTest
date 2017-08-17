package cheungqzy.reflect;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

/**
 * Created by Cheungqzy on 2017/6/5.
 */
public class MyReflectTest {

    public static void main(String[] args) {
        Class cl = Student.class;
        Field[] fields = cl.getFields();
        Method[] methods = cl.getMethods();

        Field[] fields1 = cl.getDeclaredFields();
        Method[] methods1 = cl.getDeclaredMethods();

        Class su = cl.getSuperclass();

        Field[] fields2 = su.getDeclaredFields();

        Class[] interfaces = cl.getInterfaces();

        System.out.println(interfaces[0].toString());
        System.out.println(cl.toString());
        System.out.println(cl.toGenericString());
        System.out.println(cl.getPackage().getName() + "  ----->");
        try {
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(
                    cl.getPackage().getName().replace('.', '/'));
            System.out.println(dirs.hasMoreElements());
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                if ("file".equals(url.getProtocol())) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    MyReflectTest.listFile(cl.getPackage().getName(), filePath);
                }

            }
        } catch (Exception e) {

        }

//        Package

        try {
            Constructor[] constructor = interfaces[0].getConstructors();
            Field[] fields3 = interfaces[0].getFields();
            Method[] methods2 = interfaces[0].getMethods();
            Method[] methods3 = interfaces[0].getDeclaredMethods();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Student student = new Student(1, "asd", 2);
            Method method = cl.getDeclaredMethod("getName");

            Object res = method.invoke(student, null);
            System.out.println(res.toString());

            Method method1 = cl.getDeclaredMethod("say");
            method1.setAccessible(true);
            method1.invoke(student, null);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
        }

    }

    public static void listFile(String packageName, String packagePath) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirfiles = dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.getName().endsWith(".class"));
            }
        });
        for (File file : dirfiles) {
            String className = file.getName().substring(0, file.getName().lastIndexOf("."));
            try {
                Class temp = Class.forName(packageName + '.' + className);
//                System.out.println(className);
//                System.out.println(className +"--->" + temp.isAssignableFrom(MyReflectTest.class));
//                System.out.println(temp.getModifiers());
                if(!temp.isInterface() && !Modifier.isAbstract(temp.getModifiers())){
//                    System.out.println(className);
                    System.out.println(className +"+++>" + temp.isAssignableFrom(MyReflectTest.class));
//                    System.out.println(temp.getModifiers());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
