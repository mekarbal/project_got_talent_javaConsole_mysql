package enums;

public enum Credential {
	ACCOUNT("Email de l'admin"),
	PASSWORD("mot de passe");

	private String accPass;

	private Credential(String crAccount) {
		this.accPass = crAccount;
	}

	public String getAccPass() {
		return accPass;
	}

}
