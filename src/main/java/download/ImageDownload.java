package download;

import org.apache.commons.lang3.StringUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Cheungqzy on 2017/9/24.
 */
public class ImageDownload {

    static String mainPath = "http://image.yonghuivip.com";

    public static void main(String[] args) {

        File csv = new File("C:\\Users\\Cheungqzy\\Desktop\\123\\123.csv");  // CSV文件路径
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String line;
            int i = 0;
            while((line = br.readLine()) != null){
                i++;
                String[] temp = line.split(",");
                if(temp.length > 1 && StringUtils.isNotEmpty(temp[0]) && StringUtils.isNotEmpty(temp[1])){
                    try {
                        String url = mainPath+temp[1], fileName = temp[0] + ".JPG";
                        fileName = fileName.replace("S-","");
                        download(mainPath+temp[1], fileName, "C:\\Users\\Cheungqzy\\Desktop\\123\\src");
                        System.out.println("第" + i + "张图片下载成功 " + temp[0] + " " + url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    System.out.println("第" + i + "张图片下载失败");
                }
            }
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main1(String[] args) {
        String str = "http://image.yonghuivip.com/image/1491482917613dfaaeed694eae97c85b434d102c0fe199eb9c461";
        try {
            download(str,"S-13532","C:\\Users\\Cheungqzy\\Desktop\\123\\src");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void download(String urlString, String filename,String savePath) throws Exception {
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        con.setConnectTimeout(5*1000);
        InputStream is = con.getInputStream();
        byte[] bs = new byte[1024];
        int len;
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        os.close();
        is.close();
    }

    public String getDesktopPath(){
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
        return com.getPath();
    }

}
