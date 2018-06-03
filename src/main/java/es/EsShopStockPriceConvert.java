package es;

import com.alibaba.fastjson.JSON;
import com.yonghui.product.es.hub.dto.EsSkuShopPriceStockDto;

import java.util.Map;

/**
 * Created by Cheungqzy on 2017/12/18.
 */
public class EsShopStockPriceConvert extends SkuShopEsResponseTemplate<EsSkuShopPriceStockDto> {

    @Override
    public EsSkuShopPriceStockDto convert(Map reMap) {
        String str = JSON.toJSONString(reMap);
        EsSkuShopPriceStockDto skuManageDto = JSON.parseObject(str, EsSkuShopPriceStockDto.class);
        return skuManageDto;
    }
}
