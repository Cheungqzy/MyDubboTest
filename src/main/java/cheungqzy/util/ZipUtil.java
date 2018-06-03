package cheungqzy.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Cheungqzy on 2017/7/27.
 */
public class ZipUtil {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Cheungqzy\\Desktop\\文描\\2230");
        try {
           if(file.isDirectory() && file.listFiles().length > 0 ){
               File[] files = file.listFiles();
               System.out.println(CheckMainImage(files));
           }
        }catch (Exception e){

        }
        System.out.println("0.JPG".toLowerCase().equals("0.jpg"));
    }
    private static Boolean CheckMainImage(File[] files){
        for(File file : files){
            if(file.getName().toLowerCase().equals("0.jpg") || file.getName().toLowerCase().equals("0.png") || file.getName().toLowerCase().equals("0.jpeg"))
                return true;
        }
        return false;
    }
    public static void unZipFiles(File zipFile, String descDir)
            throws IOException {
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        try (ZipFile zip = new ZipFile(zipFile)) {
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");

                // 判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                // 输出文件路径信息
                System.out.println(outPath);

                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
            System.out.println("******************解压完毕********************");
        }
    }

    public static byte[] file2byte(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
