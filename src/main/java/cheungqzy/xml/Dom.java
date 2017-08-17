package cheungqzy.xml;

import cheungqzy.util.DateUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Cheungqzy on 2017/6/5.
 */
public class Dom {

    public ByteArrayInputStream buildXML(){
        try {
            DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            document.setXmlStandalone(true);
            //创建属性名、赋值
            Element root = document.createElement("YH0001");
            root.setAttributeNS("ns0","YH","YH0001");
            root.setAttribute("datetime", DateUtil.getCurrentTime());
            //创建第一个根节点、赋值
            Element lan = document.createElement("lan");
            lan.setAttribute("id", "1");
            Element name = document.createElement("name");
            name.setTextContent("java");
            Element ide = document.createElement("IDE");
            ide.setTextContent("Eclipse");
            lan.appendChild(name);
            lan.appendChild(ide);
            //创建第二个根节点、赋值
            Element lan2 = document.createElement("lan");
            lan2.setAttribute("id", "2");
            Element name2 = document.createElement("name");
            name2.setTextContent("Swift");
            Element ide2 = document.createElement("ide");
            ide2.setTextContent("XCode");
            lan2.appendChild(name2);
            lan2.appendChild(ide2);
            //添加到属性中、
            root.appendChild(lan);
            root.appendChild(lan2);
            document.appendChild(root);
            //定义了用于处理转换指令，以及执行从源到结果的转换的
            /*TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("encoding", "UTF-8");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            System.out.println(writer.toString());
            transformer.transform(new DOMSource(document), new StreamResult(new File("F:\\tmp\\file\\dom.xml")));*/

            DOMSource source = new DOMSource(document);
            StringWriter xmlAsWriter = new StringWriter();
            StreamResult result = new StreamResult(xmlAsWriter);
            TransformerFactory.newInstance().newTransformer().transform(source,result);
            StringReader xmlReader = new StringReader(xmlAsWriter.toString());
            ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlAsWriter.toString().getBytes("UTF-8"));
            return  inputStream;

        } catch (ParserConfigurationException /*| TransformerException*/ e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Dom dom = new Dom();
        dom.buildXML();
    }
}

