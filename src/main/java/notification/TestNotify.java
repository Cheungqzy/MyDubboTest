package notification;

import com.alibaba.fastjson.JSON;
import com.yonghui.apppush.api.AppPushApiService;
import com.yonghui.common.constants.App;
import com.yonghui.thirdparty.api.notification.AppNotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("/applicationContext-dubbo.xml")
public class TestNotify {
	@Autowired
	private AppNotificationService appNotificationService;
	@Autowired
	private AppPushApiService appPushApiService;
	public static int count= 0;
	private ExecutorService executorService = Executors.newFixedThreadPool(80);

	public synchronized void add(){
		count ++;
		System.out.println(count);
	}
	public void print(){
		System.out.println(count);
	}
	@Test
	public void testpushToMemberGJRate(){
		for(int i=0;i<2000;i++) {
			executorService.submit(new ThreadTask(this));
		}
		try {
			Thread.sleep(10000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testMember() {
		System.out.println();
		System.out.println(appNotificationService.pushToMember(App.YH_PARTNER, 2016700000, "测试", "这个是标题", "myyh://yhlife.com/show/native?name=shopcart", 24));
		System.out.println();
	}
	
	@Test
	public void testMembers() {
		List<Long> ids = new ArrayList<>();
		ids.add(21042l);
		ids.add(123l);
		ids.add(1232l);
		ids.add(1245l);
		ids.add(1231l);
		ids.add(123234l);
		ids.add(123564536l);
		
		System.out.println();
		System.out.println(appNotificationService.pushToMember(App.YH_TO_HOME, ids, "接口测试", "123", "", 24));
		System.out.println();
	}

	@Test
	public void testReport() {
		System.out.println();
		System.out.println(appNotificationService.queryPushReport(App.YH_TO_HOME, "8323690522"));
		System.out.println();
	}
	@Test
	public void testApppush(){
		System.out.println(JSON.toJSONString(appPushApiService.getAllUserGroup()));
	}

	class ThreadTask implements Runnable{
		private TestNotify testNotify;
		public ThreadTask(TestNotify testNotify){
			this.testNotify = testNotify;
		}

		@Override
		public void run() {
			for(int i=0;i<2;i++) {
				try {
					testNotify.appNotificationService.pushToMemberGJ(App.YH_PARTNER, 110, "测试", "测试", "", "");
				}catch (Exception e){
					testNotify.add();
				}
			}
		}
	}

}
