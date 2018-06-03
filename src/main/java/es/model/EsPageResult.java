package es.model;

import java.util.List;

/**
 * Created by zhangqing on 2017/9/15.
 */
public class EsPageResult<T> {

    private Integer totalCount;

    private List<T> results;

    public EsPageResult() {}

    public EsPageResult(Integer totalCount, List<T> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
