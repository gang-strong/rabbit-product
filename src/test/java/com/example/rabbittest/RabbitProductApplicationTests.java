package com.example.rabbittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.utils.common.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitProductApplicationTests {

	@Autowired
	private MsgSender sender;
	
	@Test
	public void sendMsg() {
		sender.sendDateMsg();
	}

	@Test
	public void sendObj(){
		sender.sendUser(new User(1, "测试rabbit", 22, "wangjingsoho.china"));
	}
	
	@Test
	public void sendJSONObject() {
		int i = 0;
		boolean flag = true;
		while(flag){
			JSONObject json = new JSONObject();
			json.put("key", new User(i, "name" + i, i+10, "address---" + i));
			sender.sendJSONObject(json);
			i++;
			if(i == Integer.MAX_VALUE) flag = false;
		}
		System.out.println("--------------end--------------");
	}
	
	@Test
	public void test() {
		Runnable able = new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i< 10; i++) {
					JSONObject json = new JSONObject();
					json.put("key", new User(i, "name" + i, i+10, "address---" + i));
					System.out.println("send end……  index = " + i);
					sender.sendJSONObject(json);
				}
			}
		};

		Thread t1 = new Thread(able);
		Thread t2 = new Thread(able);
		Thread t3 = new Thread(able);
		Thread t4 = new Thread(able);
		try {
			t1.start();
			Thread.sleep(1000);
			t2.start();
			Thread.sleep(1000);
			t3.start();
			Thread.sleep(1000);
			t4.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
