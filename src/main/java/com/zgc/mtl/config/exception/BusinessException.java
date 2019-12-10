package com.zgc.mtl.config.exception;

public class BusinessException extends Exception{
//	private Logger logger = LoggerOFactory.getLogger(BusinessException.class);
	
	public BusinessException(String message) {
		super(message);
//		logger.error(message);
	}
}
