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
}
