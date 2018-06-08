package product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.yonghui.product.dto.SkuManageDto;
import com.yonghui.product.dto.SkuManageQueryParams;
import com.yonghui.product.es.hub.api.EsSkuManageService;
import com.yonghui.product.es.hub.api.SyncSkuShopService;
import com.yonghui.product.es.hub.dto.EsSkuShopPriceStockDto;
import com.yonghui.product.es.hub.dto.SkuImageManageQueryParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Cheungqzy on 2018/1/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-dubbo.xml")
public class SyncEsShopProductTest {

    @Autowired
    private SyncSkuShopService syncSkuShopService;
    @Autowired
    private EsSkuManageService esSkuManageService;
//    @Autowired
//    private ShopSkuService shopSkuService;
//
//    @Test
//    public void test11(){
//        YhShopSkuQueryParams yhShopSkuQueryParams = new YhShopSkuQueryParams();
//        yhShopSkuQueryParams.setShopId("9D13");
//        yhShopSkuQueryParams.setSortType("skuType");
//        yhShopSkuQueryParams.setSortDirection("asc");
//        ShopSkuResult skuByShopId = shopSkuService.getSkuByParams(yhShopSkuQueryParams, new ShopSkuResult(new YhShopDto()));
//        System.out.println("asd");
//    }

    @Test
    public void getSkuList(){
    }

    @Test
    public void initSku(){
        String message = "{\"dbName\":\"product_center\",\"destination\":\"t-sku-new\",\"eventType\":\"UPDATE\",\"relaData\":{},\"rowdataLst\":[{\"afterColumns\":[{\"mysqlType\":\"varchar(20)\",\"name\":\"sku_code\",\"updated\":true,\"value\":\"10006\"}],\"beforeColumns\":[{\"mysqlType\":\"varchar(20)\",\"name\":\"sku_code\",\"updated\":false,\"value\":\"100066\"}]}],\"tableName\":\"t_sku\"}";
        try {
            syncSkuShopService.pushSkuShopInfo(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){ //全量同步
        syncSkuShopService.indexAllSku(0);
    }
    @Test
    public void test2(){ //斷點優先執行
        syncSkuShopService.indexAllSku(1);
    }

    @Test
    public void test3(){ //暂停任务
        syncSkuShopService.stopIndexAllSku();
    }

    @Test
    public void testx3() {//重建指定sku索引
        syncSkuShopService.initSkuEsRecordByList(Arrays.asList("101743"));
    }

    @Test
    public void testa2(){ //初始化商品数据
        syncSkuShopService.initSkuEsRecordByList(Arrays.asList("B-274854","B-446500","J-20252","J-836818","J-843288","S-911343","S-830349","S-914301","R-20252","R-843288","S-CB23004","446500","D-922960-L","S-924552","17903","23868","924552","CB26570","X-446500","X-274854","S-274854"));
    }

    @Test
    public void testa1(){ //获取文描信息
        SkuImageManageQueryParams skuImageManageQueryParams = new SkuImageManageQueryParams();
        skuImageManageQueryParams.setPage(0);
//        skuImageManageQueryParams.setDisplaySkuName("牛奶");
        skuImageManageQueryParams.setSkuCodes(Arrays.asList("266592"));
        Map<String, Object> res = esSkuManageService.findSkuImageListByPage(skuImageManageQueryParams);
        System.out.println(res.size());
    }

    @Test
    public void testx4(){//获取门店制定skuCode数据
        List<EsSkuShopPriceStockDto> res = esSkuManageService.getEsRecordBySkuListShopId("9I01", Arrays.asList("CB1005","S-836015"));
        System.out.println("es --> "+JSON.toJSONString(res));
        List<EsSkuShopPriceStockDto> res1 = esSkuManageService.getEsRecordBySkuListShopIdFromDB("9D13", Arrays.asList("CB1005","B-CB3858"));
        System.out.println("db --> " + JSON.toJSONString(res1));
    }

    @Test
    public void testx5(){//分页获取es指定门店数据 分页 - 压测
        int success = 0, fail = 0;
        for(int i=0 ;i<1; i++) {
            try {
                Long start = System.currentTimeMillis();
                List<EsSkuShopPriceStockDto> res = esSkuManageService.getFullEsRecordByShopId("9D13", 0, 10);
                Long end = System.currentTimeMillis();
                System.out.println(i + " " + (end - start));
                System.out.println(JSON.toJSONString(res));
                success++;
            }catch (Exception e){
                fail++;
            }
        }
        System.out.println(success + " "+fail);
    }

    @Test
    public void testx8(){//获取es指定门店数据总数
        int success = 0, fail = 0;
        for(int i=0 ;i<10; i++) {
            try {
                Long start = System.currentTimeMillis();
                List<EsSkuShopPriceStockDto> res = esSkuManageService.getFullEsRecordByShopId("9D13", i, 5000);
                Long end = System.currentTimeMillis();
                System.out.println(i + " " + (end - start) + " " + res.size());
                for(EsSkuShopPriceStockDto esSkuShopPriceStockDto : res){
                    if(!esSkuShopPriceStockDto.getSkuCode().startsWith("R-")){
                        System.out.println("9466" + " " + esSkuShopPriceStockDto.getSkuCode());
                    }
                }
                success+=res.size();
                if(res.size() <= 0)
                    break;
            }catch (Exception e){
                fail++;
            }
        }
        System.out.println("总数： " + success + " " + fail);
    }

    @Test
    public void testx6(){//分页获取es指定门店数据，按时间戳
        //esSkuManageService.updateEsRecordPriceInfo("9D13", "317036", new BigDecimal(15.5), new BigDecimal(15.5));
        List<EsSkuShopPriceStockDto> res = esSkuManageService.getStockChangeListByShopIdTime("9D13", 0, 10, 1494858203126L, 1494858203146L);
        System.out.println(JSON.toJSONString(res));
    }

    @Test
    public void testx7(){//更新数据
        esSkuManageService.updateEsRecordPriceInfo("9D13", "317036", new BigDecimal(15.5), new BigDecimal(15.5));
    }

    @Test
    public void testx11(){//更新数据
        try {
            SkuManageQueryParams params = new SkuManageQueryParams();
            params.setSkuCodes(Lists.newArrayList("R-CB25987"));
            params.setPagination(true);
            params.setPage(0);
            params.setSize(10000);
            Map skuShopList = esSkuManageService.findSkuShopListByPage(params);
            List<SkuManageDto> skuShopStocks = (List<SkuManageDto>) skuShopList.get("rows");
            System.out.println(skuShopStocks.size());
        }catch (Exception e){

        }
    }

    @Test
    public void testx12(){//DB获取数据
        try {
            List<String> list = new ArrayList<>();
            list.add("10684");
            List<EsSkuShopPriceStockDto> skuShopStocks = esSkuManageService.getEsRecordBySkuListShopIdFromDB("9D13", list);
            System.out.println(JSON.toJSONString(skuShopStocks, SerializerFeature.WriteMapNullValue));
        }catch (Exception e){

        }
    }

}
