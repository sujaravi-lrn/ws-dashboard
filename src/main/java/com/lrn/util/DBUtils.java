package com.lrn.util;

import java.math.BigDecimal;

/**
 * @author Suja.Ravi
 * Jun 27, 2016
 */
public class DBUtils {

	public static long getLongFromResultMapObject(Object value) {
		if(value == null)
			return 0;
		
		BigDecimal bd = (BigDecimal) value;
		return bd.longValue();
	}
}
