package enums;

public enum ConDb {
	AUTH("mail.smtp.auth", "true"),
	SSL("mail.smtp.starttls.enable", "true"),
	HOST("mail.smtp.host", "smtp.gmail.com"),
	PORT("mail.smtp.port", "587");

	private String props = "";
	private  String value = "";
	
	ConDb(String props,String value) {
		this.props=props;
		this.value=value;
	}

	public String getProps() {
		return props;
	}

	
	public String getValue() {
		return value;
	}



	

	
	
	

	
	

}
