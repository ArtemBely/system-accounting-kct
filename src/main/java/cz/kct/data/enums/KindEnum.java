package cz.kct.data.enums;

public enum KindEnum {
    DEVELOPMENT("Vývoj SA"),
    SUPPORT("Support SA"),
    VISPART("");
    private final String value;

    KindEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static KindEnum fromValue(String value) {
        for (KindEnum enumConstant : KindEnum.values()) {
            if (enumConstant.getValue().equals(value)) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException("No enum constant with value: " + value);
    }
}
