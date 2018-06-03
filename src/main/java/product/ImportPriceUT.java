package product;

import com.alibaba.fastjson.JSON;
import com.yonghui.common.util.DateUtil;
import com.yonghui.product.dto.MDSkuShopPriceDto;
import com.yonghui.product.dto.MDSkuShopStockDto;
import com.yonghui.product.dto.SyncSkuShopPriceDto;
import com.yonghui.product.service.api.SkuShopPriceService;
import com.yonghui.product.service.api.SkuShopStockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * Created by Cheungqzy on 2017/9/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-dubbo.xml")
public class ImportPriceUT {

    @Autowired
    private SkuShopPriceService skuShopPriceService;
    @Autowired
    private SkuShopStockService skuShopStockService;

    private final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.S";

    @Test
    public void test1(){
        String res = "{\"code\":\"S200\",\"message\":\"操作成功\",\"lastSerialid\":639334,\"data\":[" +
                "{\"barcodeid\":\"6921799888362\",\"deptid\":12370609,\"goodsid\":\"360407\",\"goodsname\":\"萨啦咪啃德佬鸡翅(烧烤薰味)38g\"," +
                "\"price\":11.70,\"producing\":\"浙江温州\",\"shopid\":\"9I09\",\"spec\":\"38g\",\"unitname\":\"包\"," +
                "\"venderid\":\"20002620\",\"viflag\":\"3\"," +
                "\"promDatas\":{\"promprice\":7.9,\"promno\":\"2000819748\"," +
                "\"startdate\":\"2017-08-24 00:00:00.0\",\"enddate\":\"2017-10-03 23:59:59.0\"}}]}";
        SyncSkuShopPriceDto data = JSON.parseObject(res, SyncSkuShopPriceDto.class);
        for(SyncSkuShopPriceDto.skuData skuData : data.getData()) {
            MDSkuShopPriceDto mdSkuShopPriceDto = new MDSkuShopPriceDto();
            mdSkuShopPriceDto.setSkuCode(skuData.getSkuCode());
            mdSkuShopPriceDto.setShopId(skuData.getShopId());
            mdSkuShopPriceDto.setDefaultPrice(skuData.getPrice());
            mdSkuShopPriceDto.setChannelFlag(skuData.getChannelFlag());
            if (skuData.getPromDatas() != null) {
                mdSkuShopPriceDto.setPromotionPrice(skuData.getPromDatas().getPromotionPrice());
                mdSkuShopPriceDto.setPromotionNumber(skuData.getPromDatas().getPromotionNumber());
                mdSkuShopPriceDto.setStartDate(DateUtil.getDateFromString(skuData.getPromDatas().getStartDate(), TIME_PATTERN));
                mdSkuShopPriceDto.setEndDate(DateUtil.getDateFromString(skuData.getPromDatas().getEndDate(), TIME_PATTERN));
            }
            skuShopPriceService.importPrice(mdSkuShopPriceDto, false, true);
        }
    }

    @Test
    public void test2(){
        MDSkuShopStockDto mdSkuShopStockDto = new MDSkuShopStockDto();
        mdSkuShopStockDto.setShopId("9D13");
        mdSkuShopStockDto.setSkuCode("694835");
        mdSkuShopStockDto.setQty(new BigDecimal(12));
        skuShopStockService.OperateSyncStock(mdSkuShopStockDto, true, false, true);
    }

}
