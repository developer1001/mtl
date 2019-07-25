package com.zgc.mtl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.zgc.mtl.model.Log;
import com.zgc.mtl.service.LogService;
/**
 * mongdb 日志
 * @date 2019-07-25 14:51:00
 * @author yang
 */
@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private MongoTemplate mongoTemplate;
	private static final String collectionName = "log";
	@Override
	public Object insert(Log log) {
		Log insert = mongoTemplate.insert(log, collectionName);
		return insert;
	}

	@Override
	public Object update(Log log) {
		Query query = new Query(Criteria.where("logId").is(log.getLogId()));
		Update update = new Update();
		update.set("logType", log.getLogType());
		update.set("content", log.getContent());
		UpdateResult updateFirst = mongoTemplate.updateFirst(query, update, collectionName);
		return updateFirst;
	}

	@Override
	public Object get(String logId) {
		Query query = new Query(Criteria.where("logId").is(logId));
		List<Log> find = mongoTemplate.find(query, Log.class,collectionName);
		return find;
	}

	@Override
	public Object remove(String logId) {
		Query query = new Query(Criteria.where("logId").is(logId));
		DeleteResult remove = mongoTemplate.remove(query, collectionName);
		return remove;
	}

}
