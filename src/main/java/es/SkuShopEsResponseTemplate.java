package es;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.yonghui.product.es.hub.dto.EsResponce;
import com.yonghui.product.es.hub.dto.item.Hits;
import es.model.EsPageResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public abstract class SkuShopEsResponseTemplate<T> implements EsResponseTemplate<T> {

    private static final Logger logger = LoggerFactory.getLogger(SkuShopEsResponseTemplate.class);

    @Override
    public EsPageResult<T> transform(String responseStr) {
        if(StringUtils.isBlank(responseStr)){
            return null;
        }
        List<T> retDatas = Lists.newArrayList();
        int totalCount = 0;
        EsResponce esResponce = JSON.parseObject(responseStr, EsResponce.class);
        if (esResponce.getHits() != null && CollectionUtils.isNotEmpty(esResponce.getHits().getHits())) {
            totalCount = esResponce.getHits().getTotal();
            List<Hits> results = esResponce.getHits().getHits();
            logger.info("result list size:{}", results.size());
            for(Hits result : results){
                Map<String ,Object> reMap = result.getSource();

                T t = convert(reMap);
                retDatas.add(t);
            }

        }
        return new EsPageResult<T>(totalCount, retDatas);
    }

    public abstract T convert(Map<String ,Object> reMap);
}
