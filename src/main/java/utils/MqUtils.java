//package commonUtils;
//
//import com.yonghui.common.message.bridge.MessageBridgeProducer;
//import com.yonghui.common.util.DateUtil;
//import com.yonghui.message.bridge.api.RegistrationService;
//import com.yonghui.message.bridge.api.model.ParamTypeSupported;
//import com.yonghui.message.bridge.api.model.PublisherInfo;
//import org.apache.commons.lang3.math.NumberUtils;
//import org.joda.time.DateTime;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayOutputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.TreeMap;
//
///**
// * Created by Cheungqzy on 2017/8/14.
// */
//@Component
//public class MqUtils implements Serializable{
//    @Autowired
//    private RegistrationService registrationService;
//    @Autowired
//    private MessageBridgeProducer messageBridgeProducer;
//
//    public void sendMessage(String definitionId, String params) {
//
//        PublisherInfo subscriberInfo = registrationService.getPublisherInfo(definitionId);
//
//        TreeMap<String, ParamTypeSupported> messageParams = subscriberInfo.getMessageParams();
//
//        String[] list = params.split(",");
//        HashMap<String, Object> paramValues = new HashMap<>();
//        for (String str : list){
//            String [] temp = str.split("=");
//            paramValues.put(temp[0],convertToTarget(messageParams.get(temp[0]).getClazz(), temp[1]));
//        }
//
//        messageBridgeProducer.sendMessageBytes(definitionId, null, this.serialize(paramValues),null);
//    }
//
//    public static byte[] serialize(Object object) {
//        byte[] bytes = null;
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
//             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
//            if (object != null) {
//                oos.writeObject(object);
//                bytes = baos.toByteArray();
//            }
//            oos.close();
//            baos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bytes;
//    }
//
//    public static Object convertToTarget(Class<?> clazz, String value) {
//        if (clazz == Integer.class) {
//            return NumberUtils.toInt(value);
//        } else if (clazz == Long.class) {
//            return NumberUtils.toLong(value);
//        } else if (clazz == BigDecimal.class) {
//            return new BigDecimal(value);
//        } else if (clazz == Boolean.class) {
//            return Boolean.valueOf(value);
//        } else if (clazz == Date.class) {
//            DateTime time = DateTime.parse(value, DateUtil.DEFAULT_DATETIME_FORMATTER);
//            return time.toDate();
//        }
//        return value;
//    }
//
//}
