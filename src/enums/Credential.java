package enums;

public enum Credential {
	ACCOUNT("me.karbal@gmail.com"),
	PASSWORD("mot de passe");

	private String accPass;

	private Credential(String crAccount) {
		this.accPass = crAccount;
	}

	public String getAccPass() {
		return accPass;
	}

}
