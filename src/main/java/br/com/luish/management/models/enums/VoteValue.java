package br.com.luish.management.models.enums;

public enum VoteValue {

	SIM(1, "sim"), NAO(2, "nao");

	private int code;
	private String description;

	private VoteValue(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static VoteValue toEnum(Integer code) {

		if (code == null) {
			return null;
		}

		for (VoteValue type : VoteValue.values()) {

			if (code.equals(type.getCode())) {
				return type;
			}

		}

		throw new IllegalArgumentException("Invalid code" + code);

	}

}
