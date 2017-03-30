package com.xidu.framework.common.util;

public final class QueryUtils {
	private QueryUtils() {
	}

	private static final String LEFT_PARENTHESIS = "(";

	private static final String RIGHT_PARENTHESIS = ")";

	private static final String QUOTA = "'";

	private static final String COMMA = ",";
	public static String generateInOperand(String[] strs){
		StringBuilder sb = new StringBuilder();
		sb.append(LEFT_PARENTHESIS);
		if(null == strs){
			strs = new String[0];
		}
		
		for(int i=0,len = strs.length; i<len; i++){
			if( i == 0 ){
				sb.append(QUOTA).append(strs[i]).append(QUOTA);
			}
			sb.append(COMMA).append(QUOTA).append(strs[i]).append(QUOTA);
		}
		
		sb.append(RIGHT_PARENTHESIS);
		
		return sb.toString();
	}
}
