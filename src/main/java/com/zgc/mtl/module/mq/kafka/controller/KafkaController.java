package com.zgc.mtl.module.mq.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.common.enu.ResultCode;
import com.zgc.mtl.module.mq.kafka.KafkaSender;

/**
 * kafka示例
 * @date 2019-08-06 17:23:13
 * @author yang
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaSender sender;

    @RequestMapping(value = "/send")
    public Object sendKafka(String message) {
        try {
            logger.info("kafka的消息={}", message);
            sender.send(message);
            return ResultCode.SUCCESS;
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
            return ResultCode.FAILURE;
        }
    }
}
