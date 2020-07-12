package yummy.servlet;

public class GenericServlet {
	
	public static Integer intOrNull(String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		} else {
			return Integer.parseInt(value);
		}
	}
	
}
