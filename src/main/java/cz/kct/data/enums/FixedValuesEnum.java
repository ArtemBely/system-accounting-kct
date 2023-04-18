package cz.kct.data.enums;

public enum FixedValuesEnum {
    HOLIDAY(100),
    SICK_DAY(110),
    EMERGENCY(701),
    INVOICED_DAY(800),
    NOT_INVOICED_DAY(810);
    private final int value;
    FixedValuesEnum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}