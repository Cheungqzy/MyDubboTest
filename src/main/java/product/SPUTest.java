package product;

import com.alibaba.fastjson.JSON;
import com.yonghui.product.dto.ProductDto;
import com.yonghui.product.service.api.SkuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Cheungqzy on 2017/12/29.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-dubbo.xml")
public class SPUTest {

    @Autowired
    private SkuService skuService;

    @Test
    public void getProduct(){
        ProductDto productDto = skuService.getSkuGroupInfo("3153;7708;", "9I01");
        System.out.println(JSON.toJSONString(productDto));
    }

}
