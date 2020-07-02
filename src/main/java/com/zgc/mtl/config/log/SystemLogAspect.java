package com.zgc.mtl.config.log;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.zgc.mtl.common.util.NetUtil;

/**
 * 系统日志切面
 * @date 2019-12-10 12:31:00
 * @author yang
 */
@Aspect
@Component
public class SystemLogAspect {
	private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
	// @Autowired
	// ISysLogService sysLogService;

	// 第一个*表示匹配任意的方法返回值，第二个*表示所有类,第三个*表示所有方法，
	// 第一个..表示action包及其子包,第二个..表示方法的任意参数个数
	@Pointcut("execution (* com.zgc.mtl..*.*(..))")
	public void Aspect1() {
	}
	
	//以Controller结尾的类下的所有方法
	@Pointcut("execution (* com.zgc.mtl..*Controller.*(..))")
	public void AspectController() {
	}

	/**
	 * 前置通知
	 *
	 * @param joinPoint
	 *            切点
	 */
	@Before("AspectController()")
	public void doBefore(JoinPoint joinPoint) {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes == null) {
			return;
		}
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		logger.info("===========request start==============");
		try {
			String targetName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Class targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			// String operationType = "";
			// String operationName = "";
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					// operationType = method.getAnnotation(Log.class).operationType();
					// operationName = method.getAnnotation(Log.class).operationName();
					break;
				}
			}
			String params = "";
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				params += JSONObject.toJSONString(joinPoint.getArgs());
			}
			String ip = NetUtil.getIp(request);
			logger.info("请求 IP:{}", ip);
			logger.info("请求方法:{}, 请求参数：{}",
					(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"),
					params);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	// 配置环绕通知,使用在方法aspect()上注册的切入点
	// @Around("Aspect1()")
	public void around(JoinPoint joinPoint) {
		System.out.println("==========开始执行环绕通知===============");
		long start = System.currentTimeMillis();
		try {
			((ProceedingJoinPoint) joinPoint).proceed();
			long end = System.currentTimeMillis();
			System.out.println("==========结束执行环绕通知===============");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 后置通知
	 *
	 * @param joinPoint
	 *            切点
	 */
	// @After("Aspect1()")
	public void after(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String ip = NetUtil.getIp(request);
		try {
			String targetName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			// Object[] arguments = joinPoint.getArgs();
			Class targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			String operationType = "";
			String operationName = "";
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					// Class[] clazzs = method.getParameterTypes();
					// if (clazzs.length == arguments.length) {
					operationType = method.getAnnotation(Log.class).operationType();
					operationName = method.getAnnotation(Log.class).operationName();
					break;
					// }
				}
			}
			// *========控制台输出=========*//
			System.out.println("=====后置通知开始=====");
			System.out.println("请求方法:"
					+ (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")
					+ "." + operationType);
			System.out.println("方法描述:" + operationName);
			System.out.println("请求IP:" + ip);
			System.out.println("=====后置通知结束=====");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 配置返回通知,使用在方法aspect()上注册的切入点
	@AfterReturning(value = "AspectController()", returning = "result")
	public void afterReturn(JoinPoint joinPoint, Object result) {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		//非浏览器请求，不处理
		if (requestAttributes == null) {
			return;
		}
		//void方法不打印返回值日志
		if(result == null) {
			return;
		}
		logger.info("返回结果集：{}", JSONObject.toJSONString(result));
		logger.info("===========request end==============");
	}

	/**
	 * 异常通知 用于拦截记录异常日志
	 *
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "Aspect1()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		// HttpServletRequest request = ((ServletRequestAttributes)
		// RequestContextHolder.getRequestAttributes()).getRequest();
		// 获取用户请求方法的参数并序列化为JSON格式字符串
		String params = "";
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				params += JSONObject.toJSONString(joinPoint.getArgs()[i]) + ";";
			}
		}
		try {

			String targetName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs();
			Class targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			// String operationType = "";
			// String operationName = "";
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						// operationType = method.getAnnotation(Log.class).operationType();
						// operationName = method.getAnnotation(Log.class).operationName();
						break;
					}
				}
			}
		} catch (Exception ex) {
			e.printStackTrace();
		}
		logger.error("发生异常！！！");
		logger.error("异常方法:{}，异常类:{}，异常信息:{}，方法入参:{}",
				joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName(),
				e.getClass().getName(), e.getMessage(), params);
	}
}
