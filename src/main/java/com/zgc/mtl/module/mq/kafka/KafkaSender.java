package com.zgc.mtl.module.mq.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.zgc.mtl.common.constant.TopicConst;
@Component
public class KafkaSender {
	private static Logger log = LoggerFactory.getLogger(KafkaSender.class);
	@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    //发送消息方法
    public void send(String msg) {
        log.info("------------ message = {}", msg);
        kafkaTemplate.send(TopicConst.LOG_TOPIC, msg);
    }
}
