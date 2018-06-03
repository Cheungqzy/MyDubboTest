package cheungqzy;

import cheungqzy.constant.QiNiuConfig;
import cheungqzy.xml.Dom;
import cheungqzy.xml.Jdom;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Cheungqzy on 2017/6/5.
 */
public class QiNiuDemo {

    private String accessKey = QiNiuConfig.accessKey;
    private String secretKey = QiNiuConfig.secretKey;
    private String bucket = QiNiuConfig.bucket;
    private String localFilePath = "F:\\tmp\\file\\test.xml";
    private String keyName = null;//"xml/test.xml"; //不指定为空
    private Zone zone = Zone.zone2();

    public String getUpToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println(JSON.toJSONString(upToken));
        return upToken;
    }

    public String getUpToken1(){
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, "123.key");
        System.out.println(JSON.toJSONString(upToken));
        return upToken;
    }

    public void uploadFileWithLocalFilePath(){
        Configuration configuration = new Configuration(zone);
        UploadManager uploadManager = new UploadManager(configuration);
        String upToken = getUpToken();
        try {
            Response response = uploadManager.put(localFilePath,keyName,upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

    }

    public void uploadFileWithInputStreamDOM(){
        Configuration configuration = new Configuration(zone);
        UploadManager uploadManager = new UploadManager(configuration);
        String upToken = getUpToken();
        try {
            Dom dom = new Dom();
            Response response = uploadManager.put(dom.buildXML(),"stream/test.xml",upToken,null,null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    public void uploadFileWithInputStreamJDOM(){
        Configuration configuration = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(configuration);
        String upToken = getUpToken();
        InputStream inputStream = null;
        try {
            Jdom jdom = new Jdom();
            inputStream = jdom.BuildXMLDocInputStream();
            Response response = uploadManager.put(inputStream,null,upToken,null,null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(JSON.toJSONString(putRet));
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            inputStream.close();
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        } catch (IOException ioe){

        }
    }

    public static void main(String[] args) {
        QiNiuDemo qiNiuDemo = new QiNiuDemo();
//        qiNiuDemo.uploadFileWithLocalFilePath();
//        qiNiuDemo.uploadFileWithInputStreamDOM();
        qiNiuDemo.uploadFileWithInputStreamJDOM();
    }

}
