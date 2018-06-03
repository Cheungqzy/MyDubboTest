package es;


import es.model.EsPageResult;

/**
 * Created by zhangqing on 2017/9/15.
 */
public interface EsResponseTemplate<T> {

    EsPageResult transform(String responseStr);
}
