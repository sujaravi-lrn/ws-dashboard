package com.lrn.aop.logging;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

import com.lrn.util.PrintUtils;

public class LoggingAfterMethod implements AfterReturningAdvice {

	private static final Logger logger = Logger.getLogger(LoggingAfterMethod.class);

	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {

		/*if(logger.isDebugEnabled()) {
				
			StringBuffer buf = new StringBuffer();
			
			buf.append("Invoked Method => ");
			buf.append(method.getName()).append("()");
			
			if(args.length > 0) {
				buf.append(" Params => {");
				for(Object arg : args) {
					
					if(arg != null && arg.getClass().getName().equals("java.lang.Long")) 
						buf.append(arg.toString()).append(", ");
					else if(arg != null && arg.getClass().getName().equals("java.lang.String"))
						buf.append(arg.toString()).append(", ");
					else if(arg != null && arg.getClass().getName().startsWith("com.lrn."))
						buf.append(PrintUtils.printObject(arg));
					else if(arg != null)
						buf.append(arg.toString()).append(", ");
					
				}
				buf.delete(buf.length()-2, buf.length());
				buf.append("}");
				logger.debug(buf.toString());
			} else {
				logger.debug(buf.toString());
			}*/
//				StringBuffer buf = new StringBuffer();
//				
//				buf.append("Return Value => ");
//				
//				if(returnValue!= null && returnValue.toString().length() < 10000)
//					buf.append(PrintUtils.printObject(returnValue));
//				
//				logger.debug(buf.toString());
//		}		
	}
	
}
