package com.example.rabbittest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.utils.common.User;

@Component
public class MsgSender {
	
	@Autowired
	private Queue dateQueue;
	@Autowired
	private Queue objQueue;
	@Autowired
	private Queue jsonQueue;
	
	@Autowired
	private AmqpTemplate template;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendDateMsg() {
		String context = "now date ".concat(new SimpleDateFormat("yyyy-MM-dd HH:mm:dd").format(new Date()));
        template.convertAndSend(dateQueue.getName(), context);
	}
	
	public void sendUser(User user){
		 System.out.println("Sender : " + user);
		 template.convertAndSend(objQueue.getName(), user);
	}
	
	public void sendJSONObject(JSONObject obj) {
			
			template.convertAndSend(jsonQueue.getName(), obj);
	}
	
	public void sendRabbitMsg(Message msg) {
		rabbitTemplate.send(msg);
	}
}
