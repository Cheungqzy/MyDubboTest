package cheungqzy.xml;

import cheungqzy.util.DateUtil;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Cheungqzy on 2017/6/5.
 */
public class Jdom {

    Book[] books = new Book[]
            {
                    new Book("1","唐诗三百首"),
                    new Book("2","Think in Java"),
                    new Book("3","神雕侠侣"),
                    new Book("4","葵花宝典")
            };

    public ByteArrayInputStream BuildXMLDocInputStream() {
        // 创建根节点 并设置它的属性 ;
        Element root = new Element("YH0001").setAttribute("datetime", DateUtil.getCurrentTime());
        root.setNamespace(Namespace.getNamespace("ns0","YH0001"));

        // 将根节点添加到文档中
        Document Doc = new Document(root);
        for (int i = 0; i < books.length; i++) {
            // 创建节点 book;
            Element elements = new Element("book");
            // 给 book 节点添加子节点并赋值；
            elements.addContent(new Element("id").setText(books[i].getId()));
            elements.addContent(new Element("name").setText(books[i].getName()));
            //
            root.addContent(elements);
        }
        // 输出 books.xml 文件；
        // 使xml文件 缩进效果
        Format format = Format.getPrettyFormat();
        format.setIndent("");
        format.setEncoding("utf-8");
        format.setLineSeparator("");
        XMLOutputter xMLOut = new XMLOutputter(format);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ByteArrayInputStream bi = null;
        try {
            xMLOut.output(Doc, bo);
            bi = new ByteArrayInputStream(bo.toByteArray());
            bo.close();
        }catch (IOException ioe){

        }
        return bi;
    }

    public void BuildXMLDoc() throws IOException, JDOMException {
        // 创建根节点 并设置它的属性 ;
        Element root = new Element("YH0001").setAttribute("datetime", DateUtil.getCurrentTime());
        root.setNamespace(Namespace.getNamespace("ns0","YH0001"));
        // 将根节点添加到文档中
        Document Doc = new Document(root);
        for (int i = 0; i < books.length; i++) {
            // 创建节点 book;
            Element elements = new Element("book");
            // 给 book 节点添加子节点并赋值；
            elements.addContent(new Element("id").setText(books[i].getId()));
            elements.addContent(new Element("name").setText(books[i].getName()));
            //
            root.addContent(elements);
        }
        // 输出 books.xml 文件；
        // 使xml文件 缩进效果
        Format format = Format.getPrettyFormat();
        format.setIndent("");
        format.setEncoding("utf-8");
        format.setLineSeparator("");
        XMLOutputter XMLOut = new XMLOutputter(format);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        XMLOut.output(Doc, new FileOutputStream("F:/tmp/file/books.xml"));
    }

    public static void main(String[] args) {
        try {
            Jdom j2x = new Jdom();
            System.out.println("正在生成 books.xml 文件...");
            j2x.BuildXMLDoc();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("F:/tmp/file/books.xml文件已生成");
    }
}
