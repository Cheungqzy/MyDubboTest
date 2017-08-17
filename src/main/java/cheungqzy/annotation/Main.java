package cheungqzy.annotation;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

/**
 * Created by Cheungqzy on 2017/7/10.
 */
public class Main {

    public static void main(String[] args) {
        Class cl = Main.class;
        System.out.println(cl.getPackage().getName() + "  ----->");
        try {
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(
                    cl.getPackage().getName().replace('.', '/'));
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                if ("file".equals(url.getProtocol())) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    System.out.println(filePath);
                    Main.listFile(cl.getPackage().getName(), filePath);
                }
            }
        } catch (Exception e) {

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
        System.out.println(" ---> "+dirfiles.length);
        for (File file : dirfiles) {
            String className = file.getName().substring(0, file.getName().lastIndexOf("."));
            try {
                Class temp = Class.forName(packageName + '.' + className);
                if(temp.isAnnotationPresent(MyResource.class)){
                    Annotation[] annotations = temp.getAnnotations();
                    for(Annotation annotation : annotations){
                        if(annotation.annotationType().equals(MyResource.class)){
                            System.out.println(((MyResource)annotation).id());
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
