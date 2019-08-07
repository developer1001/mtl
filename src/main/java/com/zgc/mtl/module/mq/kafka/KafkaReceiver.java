package com.zgc.mtl.module.mq.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.zgc.mtl.common.constant.TopicConst;
@Component
public class KafkaReceiver {
	private static Logger log = LoggerFactory.getLogger(KafkaReceiver.class);

//	@KafkaListener(topics = { TopicConst.LOG_TOPIC })
	public void listen(String message) {
		log.info("------------------接收消息 message =" + message);
	}
}
