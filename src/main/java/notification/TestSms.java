package notification;

import com.yonghui.thirdparty.api.sms.Sender;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("/applicationContext-dubbo.xml")
public class TestSms {

	static String message1 = "812永辉品牌周,维达抽纸6包9.9元,全场满30减5,新用户39减10,大牌折上折.详见http://t.cn/R401FNj";
	static String  message = "812永辉品牌周,维达抽纸6包9.9元,全场满30减5,新用户39减10,大牌折上折。详见http://t.cn/R401FNj";
	/** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块      */
	public static final String US_ASCII = "US-ASCII";
	/** ISO拉丁字母表 No.1，也叫做ISO-LATIN-1     */
	public static final String ISO_8859_1 = "ISO-8859-1";
	/** 8 位 UCS 转换格式     */
	public static final String UTF_8 = "UTF-8";
	/** 16 位 UCS 转换格式，Big Endian(最低地址存放高位字节）字节顺序     */
	public static final String UTF_16BE = "UTF-16BE";
	/** 16 位 UCS 转换格式，Litter Endian（最高地址存放地位字节）字节顺序     */
	public static final String UTF_16LE = "UTF-16LE";
	/** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识     */
	public static final String UTF_16 = "UTF-16";
	/** 中文超大字符集     **/
	public static final String GBK = "GBK";

	public static final String GB2312 = "GB2312";
	@Autowired
	private Sender marketingSender;
	@Autowired
	private Sender industrySender;

	@Test
	public void test1() {
		for(int i=0;i<3;i++) {
			List<String> list = new ArrayList<>();
			list.add("18502552294");
			industrySender.sender(list, "测试短信");
		}
	}

	public static void main(String[] args) {
		TestSms testSms = new TestSms();
		testSms.test2();
	}

	public void test2() {
		File csv = new File("C:\\Users\\Cheungqzy\\Desktop\\dump\\jmap_heap_201711211043_12783\\jmap_heap_201711211043_12783.bin");  // CSV文件路径
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csv));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		try {
			List<String> list = new ArrayList<>();
			int i = 0, start = 1, length = 100;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				i++;
				if(i < start-1){
					continue;
				}
				if(i == length)
					break;
				try {
//					line = new String(line.getBytes(getEncoding(line)), UTF_8);
				}catch (Exception e){

	 			}
				//System.out.println(line);
				sb.append(line);
			}
			String temp = sb.toString();
			try{
				FileWriter fileWriter = new FileWriter("C:\\Users\\Cheungqzy\\Desktop\\2.txt");
				fileWriter.write(temp);
				fileWriter.close();
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

	}
	public String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
		if(str != null) {
			//用默认字符编码解码字符串。与系统相关，中文windows默认为GB2312
			byte[] bs = str.getBytes();
			return new String(bs, newCharset);    //用新的字符编码生成字符串
		}
		return null;
	}

	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GB2312
				String s = encode;
				return s;      //是的话，返回“GB2312“，以下代码同理
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是ISO-8859-1
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {   //判断是不是UTF-8
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GBK
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";        //如果都不是，说明输入的内容不属于常见的编码格式。
	}

	@Test
	public void test() {
		File csv = new File("C:\\Users\\Cheungqzy\\Desktop\\shop_phone1.csv");  // CSV文件路径
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csv));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		int start = 300000; //指定开始位置
		int i = 0 , size = 0;
		try {
			List<String> list = new ArrayList<>();
			Boolean isOnline = true;
			while ((line = br.readLine()) != null)
			{
				i++;
				if(i>start){
					if(StringUtils.isNoneBlank(line)){
						list.add(line);
					}
					if(list.size() == 100){
						start = i - 100;
						if(start == 3000000)
							throw new RuntimeException("----------");
						size = list.size();
						if (isOnline){
							marketingSender.sender(list, message);
							System.out.println("****短信批次：" + " i=" + i + " start=" + start + " size=" + size);
							Thread.sleep(100);
						}else {
							System.out.println("****短信批次：" + " i=" + i + " start=" + start + " size=" + size);
						}
						list.clear();
					}
				}
			}
			if(list.size() > 0 ){
				if (isOnline){
					marketingSender.sender(list, message);
					System.out.println(list.toString());
					System.out.println("****最后短信批次：" + " i=" + i + " start=" + (i -list.size()) + " size=" + list.size());
				}else {
					System.out.println("****最后短信批次：" + " i=" + i + " start=" + (i -list.size()) + " size=" + list.size());
				}
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
			System.out.println("****失败批次位置：　" + " i=" + i + " start=" + start + " size="+size );
		}

	}
}
