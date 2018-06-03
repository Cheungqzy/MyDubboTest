package center;

import com.alibaba.fastjson.JSON;
import com.yonghui.apppush.api.AppPushSyncDataService;
import com.yonghui.common.model.SaleChannel;
import com.yonghui.product.dto.SkuIdentify;
import com.yonghui.product.hub.service.api.ScanCodeHubService;
import com.yonghui.product.hub.service.model.ScanCodeInfo;
import com.yonghui.product.service.api.CategoryService;
import com.yonghui.product.service.api.PosSkuService;
import com.yonghui.product.service.api.SpuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cheungqzy on 2017/9/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-dubbo.xml")
public class AppPushCenter {

    @Autowired
    private AppPushSyncDataService appPushSyncDataService;
    @Autowired
    private SpuService spuService;
    @Autowired
    private ScanCodeHubService scanCodeHubService;
    @Autowired
    private PosSkuService posSkuService;
    @Autowired
    private CategoryService categoryService;

    @Test
    public void test1(){
        System.out.println(JSON.toJSONString(spuService.getSkuItemDtoBySkuCodeAndShopId("S-916478","9I05")));
    }

    @Test
    public void test2(){
        SkuIdentify skuIdentify = posSkuService.getSkuByBarcodeAndShopId("2300138002005","9I05");
        System.out.println(JSON.toJSONString(skuIdentify));
        ScanCodeInfo scanCodeInfo = scanCodeHubService.getScanCodeInfoByBarCode("2300138002005","9I05", SaleChannel.POS,null);
        System.out.println(JSON.toJSONString(scanCodeInfo));
    }

    @Test
    public void test3(){
        List<String> list = new ArrayList<>();
        list.add("1016");
        list.add("1018");
//        List<Category> res = categoryService.selectByPrimaryKey(list);
//        Category res1 = categoryService.selectByPrimaryKey("1016");
//        System.out.println(JSON.toJSONString(res));
//        System.out.println(JSON.toJSONString(res1));
    }

}
