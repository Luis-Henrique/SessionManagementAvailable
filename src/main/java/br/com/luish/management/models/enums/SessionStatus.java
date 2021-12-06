package br.com.luish.management.models.enums;

public enum SessionStatus {

	ABERTA(1, "aberta"), ENCERRADA(2, "encerrada");

	private int code;
	private String description;

	private SessionStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static SessionStatus toEnum(Integer code) {

		if (code == null) {
			return null;
		}

		for (SessionStatus type : SessionStatus.values()) {

			if (code.equals(type.getCode())) {
				return type;
			}

		}

		throw new IllegalArgumentException("Invalid code" + code);

	}

}
