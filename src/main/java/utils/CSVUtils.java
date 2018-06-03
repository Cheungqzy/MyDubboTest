package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cheungqzy on 2017/8/11.
 */
public class CSVUtils {

    public static void main(String[] args)
    {
        String message = "8.12-8.18永辉品牌周，维达抽纸6包仅9.9元；全场满30减5；大牌满减折上折；新用户全场39减10！惊不惊喜？刺不刺激？快下载APP扫货吧！满18就包邮，猛戳 http://t.cn/R401FNj";

        System.out.println(message.length());
        File csv = new File("C:\\Users\\Cheungqzy\\Desktop\\20180425\\1.csv");  // CSV文件路径
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line,everyLine;
        List<String> allString = new ArrayList<>();
        try {
            int i = 0;
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {   i++;
                allString.add(line);
            }
            System.out.println("csv表格中所有行数："+i + " " + allString.size());
//            System.out.println(allString.toString());
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
