package com.lrn.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * Created date : 12/20/2012
 * @author Suja.Ravi
 * This class is used to compare strings as numbers. First it will sort by string, then if the string has numbers inside it, 
 * that will be sorted within the string.
 * Eg. Entity 1,Entity 2, Entity 3, Entity 5, Entity 9, Entity 10, Entity 11, Entity 12
 * without this comparator will be sorted by only string as 
 * Entity 1,Entity 10, Entity 11, Entity 12, Entity 2, Entity 3, Entity 5, Entity 9.
 * After running through this comparator it will be sorted right.
 */
public class StringAsNumberComparator implements Comparator<Object> {

	private static final Logger logger = Logger.getLogger(StringAsNumberComparator.class);
	
    public static final String NUMBER_PATTERN = "(\\-?\\d+\\.\\d+)|(\\-?\\.\\d+)|(\\-?\\d+)";

    private String methodName = "";
    
    public StringAsNumberComparator(String methodName) {
    	this.methodName = methodName;
    }
    
    /**
     * Splits strings into parts sorting each instance of a number as a number if there is
     * a matching number in the other String.
     * 
     * For example A1B, A2B, A11B, A11B1, A11B2, A11B11 will be sorted in that order instead
     * of alphabetically which will sort A1B and A11B together.
     */
    public int compare(Object object1, Object object2) {
    	
    	String string1 = null;
    	String string2 = null;
    	
    	try {
	    	Method method1 = object1.getClass().getDeclaredMethod(methodName, null);
	    	string1 = (String) method1.invoke(object1, null);
	    	
	    	Method method2 = object2.getClass().getDeclaredMethod(methodName, null);
	    	string2 = (String) method2.invoke(object2, null);
	    	
    	} catch(Exception ex) {
    		//do nothing
    	}
    	
        if(string1 == null || string2 == null) {
            return 0;
        }

        List<String> split1 = split(string1);
        List<String> split2 = split(string2);
        int diff = 0;

        for(int i = 0; diff == 0 && i < split1.size() && i < split2.size(); i++) {
            String token1 = split1.get(i);
            String token2 = split2.get(i);

            if(token1.matches(NUMBER_PATTERN) && token2.matches(NUMBER_PATTERN)) {
                diff = (int) Math.signum(Double.parseDouble(token1) - Double.parseDouble(token2));
            } else {
                diff = token1.compareToIgnoreCase(token2);
            }
        }
        if(diff != 0) {
            return diff;
        } else {
            return split1.size() - split2.size();
        }
    }

    /**
     * Splits a string into strings and number tokens.
     */
    private List<String> split(String s) {
        List<String> list = new ArrayList<String>();
        Scanner scanner = new Scanner(s);
        int index = 0;
        String num = null;
        while((num = scanner.findInLine(NUMBER_PATTERN)) != null) {
            int indexOfNumber = s.indexOf(num, index);
            if(indexOfNumber > index) {
                list.add(s.substring(index, indexOfNumber));
            }
            list.add(num);
            index = indexOfNumber + num.length();
        }
        scanner.close();
        if(index < s.length()) {
            list.add(s.substring(index));
        }
        return list;
    }
}
