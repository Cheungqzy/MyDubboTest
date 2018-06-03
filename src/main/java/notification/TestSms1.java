package notification;

import com.alibaba.fastjson.JSON;
import com.yonghui.thirdparty.api.notification.AppNotificationService;
import com.yonghui.thirdparty.api.sms.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-dubbo.xml")
public class TestSms1 {
	@Autowired
	private Sender marketingSender;
	@Autowired
	private AppNotificationService appNotificationService;

	@Test
	public void test() {
		List<String> phones = new ArrayList<>();
		phones.add("18502552294");
		marketingSender.sender(phones, "同学你好！");
	}

	@Test
	public void testResgistrationRecord(){
		appNotificationService.cleanRegistrationRecord();
		Integer tes = appNotificationService.getTotalRegistrationRecord();
		System.out.println(tes);
		List<Long> res = appNotificationService.getRegistrationRecordByPage(0,10000);
		System.out.println(JSON.toJSONString(res));
	}

	@Test
	public void execute() {
		try {
			Integer total = appNotificationService.getTotalRegistrationRecord();
			Integer size = 10000;
			Integer page = total%size == 0 ? total/size : total/size + 1 ;
			List<Long> res = new ArrayList<>();
			for(int i = 0; i < page ; i++){
				try {
					res = appNotificationService.getRegistrationRecordByPage(0, size);
				}catch (Exception e){
					System.out.println("某批次删除处理失败，忽略");
				}
				for(Long temp : res){
					try {
						appNotificationService.deleteByPrimaryKey(temp);
					}catch (Exception e){
						System.out.println("某次删除处理失败，忽略");
					}
				}
				res.clear();
			}
		} catch (Exception e) {
			System.out.println("启动清理登录记录任务失败");
		}
	}

}
