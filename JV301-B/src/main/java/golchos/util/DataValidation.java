package golchos.util;

public class DataValidation {
	
	public static boolean isEmpty(String param) {
		if(param == null || param.length()==0) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Double param) {
		if(param == null) {
			return true;
		}
		return false;
	}
	
	public static String requiredMsg(String param) {
		return param + " is required.";
	}
	
	public static String attached(String s1, String s2) {
		s1 = (s1==null) ? "" : s1;
		s2 = (s2==null) ? "" : s2;
		return s1+s2;
	}
	
	public static String attached(String s1, String s2, String s3) {
		s1 = (s1==null) ? "" : s1;
		s2 = (s2==null) ? "" : s2;
		s3 = (s3==null) ? "" : s3;
		return s1+s2+s3;
	}
	
}
