package product;

import com.alibaba.fastjson.JSON;
import com.yonghui.product.hub.service.api.IProductPriceService;
import com.yonghui.product.hub.service.api.OverlapShopService;
import com.yonghui.product.hub.service.model.OverlapShopDto;
import com.yonghui.product.hub.service.model.PriceStockDto;
import com.yonghui.product.hub.service.model.ProductPrice;
import com.yonghui.product.service.api.ShopService;
import com.yonghui.product.service.api.SpuService;
import com.yonghui.promotion.service.api.PromotionMakeupService;
import com.yonghui.thirdparty.api.notification.AppNotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Cheungqzy on 2017/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-dubbo.xml")
public class productBJ {

    @Autowired
    private OverlapShopService overlapShopService;
    @Autowired
    private IProductPriceService productPriceService;
    @Autowired
    private PromotionMakeupService promotionMakeupService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private AppNotificationService appNotificationService;
    @Autowired
    private SpuService spuService;

    @Test
    public void test5(){
        System.out.println(JSON.toJSONString(spuService.getSkuItemDtoBySkuCodeAndShopId("S-588655","9I01")));
    }

    @Test
    public void test4(){
        String res = shopService.getContainShopIds("9D13");
        System.out.println(JSON.toJSONString(res));
    }

    @Test
    public void test3(){
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        List<String> list = new ArrayList<>();
//        list.add("X-101699");
        list.add("102498");
        Map<String, ProductPrice> map = productPriceService.calculateSalesPrice("9D13", 4, null, list, true);
        System.out.println(JSON.toJSONString(map));
        Map map1 = promotionMakeupService.calculateCustomerPromotionPriceWithoutException( "9403", 4, list, null );
        System.out.println(JSON.toJSONString(map1));
    }

    @Test
    public void test2(){ //获取真实门店
        OverlapShopDto overlapShopDto = overlapShopService.getRealShopIdByOverlapShopId("X-101699", 4, "BJ001");
        System.out.println(JSON.toJSONString(overlapShopDto));
       /* OverlapShopDto overlapShopDto1 = overlapShopService.getRealShopIdByOverlapShopId("X-793212", 4, "BJ001");
        System.out.println(JSON.toJSONString(overlapShopDto1));
        OverlapShopDto overlapShopDto2 = overlapShopService.getRealShopIdByOverlapShopId("X-793212", 4, "BJ001");
        System.out.println(JSON.toJSONString(overlapShopDto2));*/
    }

    @Test
    public void test2_1(){ //获取真实门店
        String overlapShopDto = overlapShopService.getMinDistanceShopId("BJ001", 121.4260770000, 31.1791680000);
        System.out.println(JSON.toJSONString(overlapShopDto));
    }

    @Test
    public void test1(){ //获取虚拟门店价格库存接口
        List<String> list = new ArrayList<>();
//        list.add("X-252486");
//        list.add("X-35312");
        list.add("X-251250");
        Map<String, PriceStockDto> map =  overlapShopService.getPriceStockInfoByOverlapShopId("BJ001",list,4,null,116.430331,39.998067);
        System.out.println(JSON.toJSONString(map));
        /*Map<String, PriceStockDto> map1 =  overlapShopService.getPriceStockInfoByOverlapShopId("BJ001",list,4,null);
        System.out.println(JSON.toJSONString(map1));*/
    }

}
