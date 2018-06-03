package product;

import com.yonghui.product.dto.YhShopTabQueryParams;
import com.yonghui.product.dto.yhshop.TabCategoryDto;
import com.yonghui.product.model.TabCategory;
import com.yonghui.product.service.api.yhshop.TabCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Cheungqzy on 2018/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-dubbo.xml")
public class ProductTab {

    @Autowired
    private TabCategoryService tabCategoryService;

    @Test
    public void test1(){
        Integer totalNum = tabCategoryService.getTotalNum(new YhShopTabQueryParams());
        List<TabCategoryDto> tabListByPage = tabCategoryService.getTabListByPage(new YhShopTabQueryParams());
        List<TabCategoryDto> tabListBySellerId = tabCategoryService.getTabListBySellerId((long) 1);
        System.out.println(tabListByPage.size());
        System.out.println(totalNum);
        TabCategory tabCategory = new TabCategory();
        tabCategory.setTabName("nihao");
        tabCategory.setSellerId(1);
//        tabCategoryService.createTab(tabCategory);
        tabCategoryService.deleteByTabId(2L);
    }

}
