package com.lrn.aop.logging;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.lrn.util.PrintUtils;

public class LoggingAroundMethod implements MethodInterceptor {

	private static final Logger logger = Logger.getLogger(LoggingAroundMethod.class);
	
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		
		try {
			
			long startTime = System.currentTimeMillis();
			
            // proceed to original method call
            Object result = methodInvocation.proceed();
            
            long endTime = System.currentTimeMillis();
            long ms = endTime - startTime;
    		long ss = ms / 1000;
            
			if(logger.isDebugEnabled()) {

				StringBuffer buf = new StringBuffer();
	    		buf.append("Took ");
	    		buf.append((endTime - startTime));
	    		buf.append(" ms (");
	    		buf.append(ss);
	    		buf.append(" sec) to execute ");
	    		buf.append(methodInvocation.getMethod().getName());
	    		
	    		if(methodInvocation.getArguments().length > 0) {
					buf.append("{");
					for(Object arg : methodInvocation.getArguments()) {
						
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
					
				} else {
					buf.append("{}");
				}
	    		logger.debug(buf.toString());
			}
			
            return result;
  
        } catch (Exception ex) {
            throw ex;
        }

	}

}
