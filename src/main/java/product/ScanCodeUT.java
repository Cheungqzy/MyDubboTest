package product;

import com.alibaba.fastjson.JSON;
import com.yonghui.common.model.SaleChannel;
import com.yonghui.product.hub.service.api.ScanCodeHubService;
import com.yonghui.product.hub.service.model.ScanCodeInfo;
import com.yonghui.product.service.api.PosSkuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Cheungqzy on 2017/9/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-dubbo.xml")
public class ScanCodeUT {

    @Autowired
    private ScanCodeHubService scanCodeHubService;
    @Autowired
    private PosSkuService posSkuService;

    @Test
    public void test1(){
        ScanCodeInfo scanCodeInfo = scanCodeHubService.getScanCodeInfoByBarCode("2345611002001","9D13", SaleChannel.POS,null);
        System.out.println(JSON.toJSONString(scanCodeInfo));
    }

    @Test
    public void test2(){
        System.out.println("正常商品 - 散称" + JSON.toJSONString(posSkuService.getSkuByBarcodeAndShopId("2345611002001","9D13")));
        System.out.println("正常商品 - 标品" + JSON.toJSONString(posSkuService.getSkuByBarcodeAndShopId("6920152471562","9D13")));
        System.out.println("删除商品 - 散称" + JSON.toJSONString(posSkuService.getSkuByBarcodeAndShopId("2345612002001","9D13")));
        System.out.println("删除商品 - 标品" + JSON.toJSONString(posSkuService.getSkuByBarcodeAndShopId("6901826888138","9D13")));
        System.out.println("店中店商品 （商家业态相同） - 散称" + JSON.toJSONString(posSkuService.getSkuByBarcodeAndShopId("2345613002001","9D13")));
        System.out.println("店中店商品 （商家业态不同） - 散称" + JSON.toJSONString(posSkuService.getSkuByBarcodeAndShopId("2345614002001","9D13")));
        System.out.println("店中店商品 （商家业态相同） - 标品" + JSON.toJSONString(posSkuService.getSkuByBarcodeAndShopId("613008719524","9D13")));
        System.out.println("店中店商品 （商家业态不同） - 标品" + JSON.toJSONString(posSkuService.getSkuByBarcodeAndShopId("613008720209","9D13")));
        System.out.println("SAP状态 = 3停售、6退场  - 标品" + JSON.toJSONString(posSkuService.getSkuByBarcodeAndShopId("6917878002996","9D13")));
        System.out.println("SAP状态 = 6退场  - 散称" + JSON.toJSONString(posSkuService.getSkuByBarcodeAndShopId("2345615002001","9D13")));
        System.out.println("有效标识 = 0  - 标品" + JSON.toJSONString(posSkuService.getSkuByBarcodeAndShopId("6920238066033","9D13")));
    }

    @Test
    public void test3(){
        System.out.println("店中店商品 （商家业态不同） - 标品" + JSON.toJSONString(posSkuService.getSkuByBarcodeAndShopId("613008720209","9D13")));
    }

}
