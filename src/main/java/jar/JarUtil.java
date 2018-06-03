package jar;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Cheungqzy on 2018/4/27.
 */
public class JarUtil {

    public static void main(String[] args) {
//        JarUtil.showPath();
        new JarUtil().showPathDemo();
        JarUtil.process();
    }

    private static void process() {
        try {
            String root = Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1);
            System.out.println(root +"product-api-server.jar");
            JarFile jarFile = new JarFile(root +"product-api-server.jar");
            Enumeration enumOfJar = jarFile.entries();
            while (enumOfJar.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) enumOfJar.nextElement();
                System.out.println(jarEntry.getName());
            }
        } catch (IOException ioe) {
            System.out.println("IO异常: " + ioe);
        }
    }

    public static void showPath(){
        try {
            Enumeration e = Thread.currentThread().getContextClassLoader().getResources("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPathDemo(){
        try {
            System.out.println(this.getClass().getResource("/").getPath());
            System.out.println(this.getClass().getResource("").getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
