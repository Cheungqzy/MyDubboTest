package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cheungqzy on 2017/9/2.
 */
public class Txt {

    public static void main(String[] args) {
        System.out.println("asd");
        File file = new File("C:\\Users\\Cheungqzy\\Desktop\\mysql-bin004242_2\\mysql-bin004242_2");  // CSV文件路径
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        String line,everyLine;
        try {
            int i=0;
            List<String> allString = new ArrayList<>();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                i++;
                if(i > 72096 && i <= 100000) {
                    if ("### UPDATE `product_center`.`t_sku_shop_stock`".equals(line)) {
                        for (int k = 0; k < 3; k++)
                            br.readLine();
                        String temp = br.readLine() + " " + br.readLine();
                        for (int k = 0; k < 21; k++)
                            br.readLine();
                        String str1 = br.readLine();
                        for (int k = 0; k < 26; k++)
                            br.readLine();
                        String str2 = br.readLine();
                        if("###   @26=1".equals(str1) && "###   @26=0".equals(str2) ) {
                            list.add(temp);
                        }

                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        File file1 = new File("C:\\Users\\Cheungqzy\\Desktop\\mysql-bin004242_2\\2.txt");
        try {
            PrintWriter out = new PrintWriter(file1);
            for(String str : list){
                out.write(str + "\r\n");
            }
            out.flush();
            out.close();
        }catch (Exception e){

        }
    }

}
