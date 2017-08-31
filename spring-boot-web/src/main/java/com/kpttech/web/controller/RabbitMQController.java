package com.kpttech.web.controller;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kpttech.common.pojo.Json;
import com.kpttech.web.RabbitMQConfig;
import com.kpttech.web.RabbitMQReceiver;

@Controller
public class RabbitMQController {
	
   private static final Logger logger = Logger.getLogger(RabbitMQController.class);
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Autowired
	private RabbitMQReceiver receiver;

	/* solr测试 */
	@RequestMapping("/doNotNeedSession/mqtest.action")
	@ResponseBody
	public Json mqTest(HttpServletRequest request) {
		Json j = new Json();
		try {
			
			logger.info("Sending message...");
			
			rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, "Hello from RabbitMQ!");
	        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);			
			
			logger.info("Sending message over!");

			j.setSuccess(true);
			j.setObj("成功");
			j.setMsg("成功");

			// }
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}

}
