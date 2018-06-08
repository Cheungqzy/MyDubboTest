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
import com.yonghui.product.model.*;
import com.yonghui.product.service.api.*;
import com.yonghui.product.sku.center.api.CityService;
import com.yonghui.product.sku.center.api.PropertyValueService;
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

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Cheungqzy on 2017/6/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-dubbo.xml")
public class productSpuTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Autowired
    private SpuService spuService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void t1(){
        System.out.println();
        List<String> skuCodes = Arrays.asList("S-898330","S-898327","S-916478","S-916477");
        List<String> spuSpecSkuCodeByList = spuService.getSpuSpecSkuCodeByList("9I05", skuCodes);
        System.out.println(JSON.toJSONString(spuSpecSkuCodeByList));
        System.out.println();
    }

}
