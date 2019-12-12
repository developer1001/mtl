package com.zgc.mtl.config.exception;

public class BusinessException extends RuntimeException{
	private static final long serialVersionUID = 1L;

//	private Logger logger = LoggerOFactory.getLogger(BusinessException.class);
	
	public BusinessException(String message) {
		super(message);
//		logger.error(message);
	}
	
	public BusinessException(String message, Throwable e) {
		super(message,e);
	}
}
