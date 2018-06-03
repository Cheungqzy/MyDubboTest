package product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yonghui.apppush.api.AppPushApiService;
import com.yonghui.bi.data.api.DashSummaryService;
import com.yonghui.common.message.bridge.MessageBridgeUtils;
import com.yonghui.common.model.SaleChannel;
import com.yonghui.common.util.DateUtil;
import com.yonghui.jituan.product.center.dto.VendorDto;
import com.yonghui.jituan.shop.product.center.dto.SkuVendorDto;
import com.yonghui.message.bridge.api.EventMessageService;
import com.yonghui.message.bridge.api.RegistrationService;
import com.yonghui.message.bridge.api.model.MessageSendResult;
import com.yonghui.message.bridge.api.model.ParamTypeSupported;
import com.yonghui.message.bridge.api.model.PublisherInfo;
import com.yonghui.product.dto.ShopProductStockDto;
import com.yonghui.product.dto.ShopStockQueryParams;
import com.yonghui.product.dto.SkuManageQueryParams;
import com.yonghui.product.hub.service.api.PosSkuHubService;
import com.yonghui.product.hub.service.api.ScanCodeHubService;
import com.yonghui.product.hub.service.model.ScanCodeInfo;
import com.yonghui.product.model.CombineSkuItem;
import com.yonghui.product.model.CombineSkuItemStock;
import com.yonghui.product.model.GVendorOrg;
import com.yonghui.product.model.Shop;
import com.yonghui.product.model.Sku;
import com.yonghui.product.service.api.CombineSkuService;
import com.yonghui.product.service.api.GVendorOrgService;
import com.yonghui.product.service.api.PosSkuService;
import com.yonghui.product.service.api.ShopService;
import com.yonghui.product.service.api.SkuManageService;
import com.yonghui.product.service.api.SkuService;
import com.yonghui.product.service.api.SkuShopPriceService;
import com.yonghui.product.service.api.SkuShopStockService;
import com.yonghui.product.service.api.VendorService;
import com.yonghui.product.sku.center.api.CityService;
import com.yonghui.product.sku.center.dto.CityTinyDTO;
import com.yonghui.product.sku.center.dto.ShopDTO;
import com.yonghui.product.sku.center.dto.ShopTinyDTO;
import com.yonghui.shop.price.center.api.ShopPriceService;
import com.yonghui.shop.stock.center.api.ShopStockService;
import com.yonghui.sync.stock.hub.api.SyncShopStockService;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Cheungqzy on 2017/6/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-dubbo.xml")
public class productTest implements ApplicationContextAware,Runnable {
    @Autowired
    private SkuShopStockService skuShopStockService;
    @Autowired
    private SkuShopPriceService skuShopPriceService;
    @Autowired
    private SkuService skuService;
    @Autowired
    private com.yonghui.product.sku.center.api.SkuService sSkuService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ScanCodeHubService scanCodeHubService;
    @Autowired
    private RegistrationService registrationService;
    private ApplicationContext applicationContext;
    @Autowired
    private EventMessageService eventMessageService;
    @Autowired
    private GVendorOrgService gVendorOrgService;
    @Autowired
    private DashSummaryService dashSummaryService;
    @Autowired
    public  SkuManageService skuManageService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private PosSkuHubService posSkuHubService;
    @Autowired
    private AppPushApiService appPushApiService;
    @Autowired
    private PosSkuService posSkuService;
    @Autowired
    private CombineSkuService combineSkuService;
    @Autowired
    private ShopStockService shopStockService;
    @Autowired
    private com.yonghui.jituan.product.center.api.VendorService jVendorService;
    @Autowired
    private com.yonghui.product.sku.center.api.ShopService skuShopService;
    @Autowired
    private CityService cityService;
    @Autowired
    private SyncShopStockService syncShopStockService;
    @Autowired
    private ShopPriceService shopPriceService;
    @Autowired
    private com.yonghui.jituan.shop.product.center.api.SkuVendorService skuVendorService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void test1x13(){
        Map<String, String> map = shopPriceService.selectAllShortSkuMapByShopId("9D13");
        System.out.println("---"+map.size());
    }

    @Test
    public void test1x12(){
        System.out.println(skuService.isItem("123;123;"));
        skuService.getSkuGroupInfo("asd", null);
    }

    @Test
    public void test1x11(){
        //shopPriceService.updateShopProductStatus("B-800583", "9233");
//        skuVendorService.updateSkuVendorStatus("1", "9068");
        SkuVendorDto skuVendorDto = skuVendorService.selectBySkuShop("1", "9010");
        System.out.println(JSON.toJSONString(skuVendorDto));
    }

    @Test
    public void test1x10(){
        //cityService.getCityByName("上海x");
        List<CityTinyDTO> citysByProvinceIdList = cityService.getCitysByProvinceIdList(Arrays.asList(1L, 2L));
        List<ShopTinyDTO> openCitysBySellerIdAndCityId = skuShopService.getOpenShopListBySellerIdAndCityId(2L, null);
        System.out.println(citysByProvinceIdList.size() + " " + openCitysBySellerIdAndCityId.size());
    }

    @Test
    public void testx9(){
        VendorDto vendorByCode = jVendorService.getVendorByCode("100008FZ");
        System.out.println(JSONObject.toJSONString(vendorByCode));
    }

    @Test
    public void testx8(){
        Long start = System.currentTimeMillis();
        Map<String,BigDecimal> res = shopStockService.realTimeStockBatch(Arrays.asList("100314","101743","101963","102498"),"9D13");
        Long end = System.currentTimeMillis();
        System.out.println(JSON.toJSONString(res) + " " + (end - start));
    }

    @Test
    public void testx7(){
        List<ShopDTO> res = skuShopService.getAllShopsFromCache();
        String str = JSON.toJSONString(res);
        try {
            System.out.println(str.getBytes("utf-8").length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            File file = new File("C:\\Users\\Cheungqzy\\Desktop\\test1.txt");
            if(file.exists()){
                FileWriter fw = new FileWriter(file,false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(str);
                bw.close(); fw.close();
                System.out.println("test1 done!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testx6(){
        Map<String, VendorDto> map = jVendorService.getVendorByCodeList(Arrays.asList("100001","100006FZ"));
        System.out.println(map.size());
    }


    @Test
    public void testx5(){//获取商品详情
//        ProductDto productDto = skuService.getSkuInfo("101740");
//        System.out.println(JSON.toJSONString(productDto));
        List<CombineSkuItem> res1 =  combineSkuService.getSubSkuItemListBySkuCode("S-ZB701");
        List<CombineSkuItemStock> res =  combineSkuService.getSubSkuItemStockListBySkuCode("S-ZB701", "9I09");
        System.out.println(JSON.toJSONString(res1));
        System.out.println(JSON.toJSONString(res));
        System.out.println(shopStockService.realTimeStockWithOutStatus("S-10768", "9I09"));

    }

    @Test
    public void testx4(){
        assertTrue(skuService.isLongProcessSku(Arrays.asList("S-SPU120")));
        assertFalse(skuService.isLongProcessSku(Arrays.asList("S-SPU12")));
        assertTrue(skuService.isLongProcessSku(Arrays.asList("161;162;")));
        assertFalse(skuService.isLongProcessSku(Arrays.asList("227;")));

        assertTrue(skuService.isLongProcessSku(Arrays.asList("CB2")));
        assertFalse(skuService.isLongProcessSku(Arrays.asList("CB101")));

        assertTrue(skuService.isLongProcessSku(Arrays.asList("101743")));
        assertFalse(skuService.isLongProcessSku(Arrays.asList("10948")));

    }

    @Test
    public void testx2() {
        List<String> list = new ArrayList<>();
        list.add("101740");
        list.add("101743");
        list.add("101963");
        List<String> res = skuShopPriceService.getSkuCodeListBelongShopId(list, "9D13", "9D01");
        System.out.println(res.toString());
    }

    @Test
    public void testx1() {
        ShopStockQueryParams params = new ShopStockQueryParams();
        params.setShopId("9D13");
        Integer total = skuShopStockService.getTotalShortOfStockByQueryParams(params);
        System.out.println(total);

        params.setPage(0);
        params.setSize(10);
        List<ShopProductStockDto> res = skuShopStockService.getShortOfStockByQueryParams(params);
        System.out.println(JSON.toJSONString(res));

    }

    @Test
    public void test11(){
        posSkuService.getSkuByBarcodeAndShopId("4054500133818","9300");
    }

    @Test
    public void test10(){
        System.out.println(JSON.toJSONString(appPushApiService.getCreateManOfAppTask()));
    }

    @Test
    public void test9(){ //29散称码校验
        PosSkuHubService temp = (PosSkuHubService)applicationContext.getBean("posSkuHubService");
        Object 哦 = temp.getPosSkuByBarcodeAndShopId("291234612345100341","9D01",null);
        System.out.println(JSON.toJSONString(哦));
        你好();
    }

    @Test
    public void test8(){ //校验供应商接口
        GVendorOrg gVendorOrg = vendorService.getGvendorOrgByShopAndVedor("9D01", "20006849");
        System.out.println(JSON.toJSONString(gVendorOrg));
        你好();
    }

    public void 你好(){
        System.out.println("nihao");
    }

    @Test
    public void test7(){
        Long start = System.currentTimeMillis();
        Sku sku = skuService.getSkuByBarCode("6920238012139");
        Long end = System.currentTimeMillis();
        System.out.println(JSON.toJSONString(sku));
        System.out.println(end-start);

        /*Sku sk1 = skuService.getSkuBySkuCode("101963");
        System.out.println(JSON.toJSONString(sk1));
        ShopDto shop = shopService.getSellerIdAndShopGroupInfoByShopId("9D13");
        System.out.println(JSON.toJSONString(shop));*/
    }

    @Test
    public void test6(){
        for(int i=0;i<100;i++) {
            SkuManageQueryParams skuManageQueryParams = new SkuManageQueryParams();
            skuManageQueryParams.setShopId("9D13");
            skuManageQueryParams.setSkuCode("1014861");
            Map map = skuManageService.findSkuManageByPage(skuManageQueryParams);
            System.out.println("---");
        }
    }

    class ThreadA implements Runnable{
        productTest parent = null;
        public ThreadA(productTest parent){
            this.parent = parent;
        }
        @Override
        public void run() {
            SkuManageQueryParams skuManageQueryParams = new SkuManageQueryParams();
            skuManageQueryParams.setShopId("9D13");
            skuManageQueryParams.setSkuCode("1014861");
            Map map = parent.skuManageService.findSkuManageByPage(skuManageQueryParams);
        }
    }

    @Test
    public void test(){
        Integer total = skuShopStockService.getTotalShortOfStock("9D01");
        System.out.println(total);
        List<ShopProductStockDto> res = skuShopStockService.getShortOfStockByPage("9D01",0,100);
        for(ShopProductStockDto s : res){
            if(s.getRealQty().compareTo(new BigDecimal(0)) > 0 )
                System.out.println(JSON.toJSONString(s));
        }
        System.out.println(JSON.toJSONString(res.get(0)));
    }

    @Test
    public void test1(){
        Sku sku = new Sku();
        sku.setSkuCode("424435");
        sku.setBalanceRefund(0);
        sku.setIsDel(true);
        skuService.updateSkuBySkuCode(sku);
    }

    @Test
    public void test2(){
        Shop shop = shopService.selectByPrimaryKey("9D01");
        System.out.println(shop.getDuration());
        System.out.println(shop.getDeliveryStart() + "  " + shop.getDeliveryStartSpecial());
        System.out.println(shop.getDeliveryEnd() + "  " + shop.getDeliveryEndSpecial());
        System.out.println("----");
        System.out.println(shop.getSelfpickStart() + "  " + shop.getSelfpickStartSpecial());
        System.out.println(shop.getSelfpickEnd() + "  " + shop.getSelfpickEndtSpecial());
    }

    @Test
    public void test3(){
        ScanCodeInfo scanCodeInfo = scanCodeHubService.getScanCodeInfoByBarCode("2304348000004", "9D13", SaleChannel.POS, 1L);
        System.out.println(JSON.toJSONString(scanCodeInfo));
        ScanCodeInfo scanCodeInfo1 = scanCodeHubService.getScanCodeInfoBySkuCode("1", "9D13", SaleChannel.POS, 1L);
        System.out.println(JSON.toJSONString(scanCodeInfo1));
        System.out.println("123456789".substring(0,7));
    }

    @Test
    public void test4(){
       GVendorOrg gVendorOrg =  gVendorOrgService.getGvendorOrgByShopAndVedor("9D13","20015983");
        System.out.println(JSON.toJSONString(gVendorOrg));
    }

    @Test
    public void test5(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE,10);
        dashSummaryService.orderSummary(calendar.getTime());
    }

    @Override
    public void run() {
        for(int i = 0 ; i < 10000; i++ ) {
            Double a ,b ,c ;
            Random random = new Random();
            a = random.nextDouble()*10;
            b = random.nextDouble()*10;
            c = random.nextDouble()*10;
            String date = DateUtil.getCurDate();
            try {
                sendMessage("pos-journal-for-ftp-new",
                        "cancleFlag=;endTime=" + date + ";goodsId=852445,852415,853613;reSheetId=;saleValue=" + a + "," + b + "," + c + ";saleValuet=" + (a + b + c) + ";sheetId=" + date + getRandomString(5) + ";shopI=9D"+((i+10)%99));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    public void MQTest(){

//        for(int i = 0; i < 5; i++) {
//            fixedThreadPool.execute(this);
//        }
        sendMessage("pos-journal-for-ftp-new",
                "saleValuet=15.8;cancleFlag=;sapRtmenges=0,0;goodsId=723920,859031;sheetId=201707319D61PJ000004;saleValue=10.8,5;shopId=9D61;endTime=20170731000619;reSheetId=");

    }


    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public void sendMessage(String definitionId, String params) {

        PublisherInfo subscriberInfo = registrationService.getPublisherInfo(definitionId);

        TreeMap<String, ParamTypeSupported> messageParams = subscriberInfo.getMessageParams();

        String[] list = params.split(";");
        HashMap<String, Object> paramValues = new HashMap<>();
        for (String str : list){
            String [] temp = str.split("=");
            System.out.println("--" + JSON.toJSONString(temp));
            if(temp.length == 1){
                paramValues.put(temp[0],null);
                continue;
            }
            paramValues.put(temp[0],convertToTarget(messageParams.get(temp[0]).getClazz(), temp[1]));
        }

        try {
            MessageBridgeUtils.fireMessageWithFailover(definitionId, paramValues,
                    (definition, hashMap) -> {
                        MessageSendResult sendResult = eventMessageService.publish(definition, hashMap);
                        if (sendResult != null && !sendResult.isSuccess()) {
                            System.out.println(JSONObject.toJSONString(sendResult));
                        }
                    });
        } catch (Exception e) {
        }
//        MessageBridgeProducer producerManager = applicationContext.getBean(MessageBridgeProducer.class);
//        producerManager.sendMessageBytes(definitionId, null, this.serialize(paramValues),null);
    }

    public static byte[] serialize(Object object) {
        byte[] bytes = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            if (object != null) {
                oos.writeObject(object);
                bytes = baos.toByteArray();
            }
            oos.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static Object convertToTarget(Class<?> clazz, String value) {
        if (clazz == Integer.class) {
            return NumberUtils.toInt(value);
        } else if (clazz == Long.class) {
            return NumberUtils.toLong(value);
        } else if (clazz == BigDecimal.class) {
            return new BigDecimal(value);
        } else if (clazz == Boolean.class) {
            return Boolean.valueOf(value);
        } else if (clazz == Date.class) {
            DateTime time = DateTime.parse(value, DateUtil.DEFAULT_DATETIME_FORMATTER);
            return time.toDate();
        }
        return value;
    }
}
