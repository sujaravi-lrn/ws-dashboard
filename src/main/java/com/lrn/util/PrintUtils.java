package com.lrn.util;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class PrintUtils {

	public static StringBuffer printObject(Object object) {
		
		StringBuffer buf = new StringBuffer();
		try {
			Field[] fields = object.getClass().getDeclaredFields();
			
			buf.append("{");
			for(Field field : fields) {
				field.setAccessible(true);
				Object fieldObj = field.get(object);
				String value = fieldObj != null ? fieldObj.toString() : null;
				
				Class fieldClass = (fieldObj != null ? fieldObj.getClass() : null);
				//System.out.println("Class :: " + (fieldClass != null ? fieldClass.getName() : null));
				
				if(fieldClass != null && fieldClass.getName().startsWith("java.util.ArrayList")) {
					ArrayList fieldList = (ArrayList) fieldObj;
					for(Object fieldInd : fieldList) {
						buf.append(printObject(fieldInd));
					}
				
				} else if(fieldClass != null && fieldClass.getName().startsWith("com.lrn.")) {
					buf.append(printObject(fieldObj));
					//System.out.println(printObject(fieldObj));
				
				} else {
				
					if(!field.getName().equals("serialVersionUID"))
						buf.append(field.getName()).append("=").append(value).append(", ");
				}
			}
			//buf.delete(buf.length()-2, buf.length());
			buf.append("}");
			
			//System.out.println(buf.toString());
			
		} catch(Exception ex) {	
			ex.printStackTrace();
		}
		
		return buf;
	}
}
