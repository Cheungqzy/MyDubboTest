package cache;

import com.yonghui.common.cache.GenericCacheManager;
import org.springframework.stereotype.Component;

/**
 * Created by Cheungqzy on 2017/9/7.
 */
@Component
public class FrequencyCacheManager extends GenericCacheManager<Long, String>{

    public FrequencyCacheManager() {
        this.cacheExpiry = 5;
    }

    @Override
    protected Long createObject(String id) {
        return null;
    }

    @Override
    protected Long getObject(String id) {
        return new Long(0);
    }

}
