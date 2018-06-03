package product;

import com.alibaba.fastjson.JSON;
import com.yonghui.product.dto.ShopProductStockDto;
import com.yonghui.product.service.api.SkuShopStockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Cheungqzy on 2017/6/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-dubbo.xml")
public class product {

    @Autowired
    private SkuShopStockService skuShopStockService;

    @Test
    public void test(){
        Integer total = skuShopStockService.getTotalShortOfStock("9D01");
        System.out.println(total);
        List<ShopProductStockDto> res = skuShopStockService.getShortOfStockByPage("9D01",0,100);
        System.out.println(JSON.toJSONString(res.get(0)));

    }

}
