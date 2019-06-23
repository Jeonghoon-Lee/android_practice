package ca.jeonghoon.jeonghoonproject.Model;

public enum MathOperator {
    Add (0),
    Subtract(1),
    Multiply(2),
    Divide(3);

    private final int value;

    MathOperator(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static MathOperator getValueFromInt(int value) {
        for (MathOperator type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
