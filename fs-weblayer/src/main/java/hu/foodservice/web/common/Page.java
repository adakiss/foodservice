package hu.foodservice.web.common;

public enum Page {
	
	LOGIN("login.jsp", "Login"),
	ERROR("error.jsp", "Error"),
	MAIN("EmployeeMainPanel.html", "Main panel");
	
	private final String jspName;
	private final String url;
	
	private Page(String jspName, String url) {
		this.jspName = jspName;
		this.url = url;
	}
	
	public String getJspName() {
		return jspName;
	}
	public String getUrl() {
		return url;
	}
	
}
