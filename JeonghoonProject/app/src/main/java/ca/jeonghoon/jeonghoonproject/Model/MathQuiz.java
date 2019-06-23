package ca.jeonghoon.jeonghoonproject.Model;

import java.io.Serializable;
import java.util.Objects;

public class MathQuiz implements Serializable {
    private MathOperator operator;

    private int operand1;
    private int operand2;

    private String userAnswer;

    public MathQuiz() { }

    public MathQuiz(MathOperator operator, int operand1, int operand2) {
        this.operator = operator;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public MathOperator getOperator() {
        return operator;
    }

    public void setOperator(MathOperator operator) {
        this.operator = operator;
    }

    public int getOperand1() {
        return operand1;
    }

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public double calculateExpectedAnswer() {
        double expectedAnswer = 0;

        switch (operator) {
            case Add:
                expectedAnswer = operand1 + operand2;
                break;
            case Subtract:
                expectedAnswer = operand1 - operand2;
                break;
            case Multiply:
                expectedAnswer = operand1 * operand2;
                break;
            case Divide:
                expectedAnswer = (double)operand1 / (double)operand2;
                break;
        }

        return expectedAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathQuiz mathQuiz = (MathQuiz) o;
        return operand1 == mathQuiz.operand1 &&
                operand2 == mathQuiz.operand2 &&
                operator == mathQuiz.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, operand1, operand2);
    }

    public String getQuizString() {
        String opratorStr = "";

        switch (operator) {
            case Add:
                opratorStr = "+";
                break;
            case Subtract:
                opratorStr = "-";
                break;
            case Multiply:
                opratorStr = "*";
                break;
            case Divide:
                opratorStr = "/";
                break;
        }

        return String.format("%d %s %d", operand1, opratorStr, operand2);
    }

    @Override
    public String toString() {
        return String.format("%s = %s, %s",
                getQuizString(),
                userAnswer,
                isValidAnswer() ? "Right" : "Wrong"
        );
    }

    public boolean isValidAnswer() {
        double expectedAnswer = calculateExpectedAnswer();

        if (expectedAnswer == Double.valueOf(userAnswer)) {
            return true;
        }
        return false;
    }
}
