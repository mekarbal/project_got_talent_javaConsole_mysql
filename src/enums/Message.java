package enums;

public enum Message {
	ADD("Bien ajout�"),
	ERROR_EMAIL("Veuillez Saisir un Email valide"),
	ERROR_PHONE("Veuillez Saisir un num�ro de telephone valide"),
	ID_SEARCH("Entrez l'identifiant de l'utilisateur que vous recherchez:"),
	UPDATE("Updated"),
	ADMIN_LOGGED("L'administrateur s'est connect� avec succ�s"),
	ADMIN_LOGOUT("L'administrateur s'est d�connect� avec succ�s"),
	PARTI_ACCEPT("la participation a ete bien accept�e"),
	PARTI_DENY("la participation a ete refus�e"),
	PARTI_EXIST("Vous avez choisi un choix n'existe pas"),
	PARTI_NOTEXIST("Cette participation n\'existe pas"),
	WRONG_CREDINTIAL("Wrong Cridentials !");

	private String description;

	private Message(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	
}
