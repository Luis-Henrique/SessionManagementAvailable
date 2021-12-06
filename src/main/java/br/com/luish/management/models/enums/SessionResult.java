package br.com.luish.management.models.enums;

public enum SessionResult {

	APROVADO(1, "aprovado"), REPROVADO(2, "reprovado"), ANDAMENTO(3, "andamento"), SEMVOTOS(4, "sem votos computados"),
	EMPATADO(5, "empatado");

	private int code;
	private String description;

	private SessionResult(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static SessionResult toEnum(Integer code) {

		if (code == null) {
			return null;
		}

		for (SessionResult type : SessionResult.values()) {

			if (code.equals(type.getCode())) {
				return type;
			}

		}

		throw new IllegalArgumentException("Invalid code" + code);

	}

}
