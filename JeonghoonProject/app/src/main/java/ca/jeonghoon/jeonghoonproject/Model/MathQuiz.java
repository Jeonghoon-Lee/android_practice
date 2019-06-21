package ca.jeonghoon.jeonghoonproject.Model;

import java.io.Serializable;
import java.util.Objects;

public class MathQuiz implements Serializable {
    private MathOperator operator;

    private int operand1;
    private int operand2;
    private double expectedAnswer;
    private double userAnswer;

    public MathQuiz() { }

    public MathQuiz(MathOperator operator, int operand1, int operand2) {
        this.operator = operator;
        this.operand1 = operand1;
        this.operand2 = operand2;

        calculateAnswer();
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

    public double getExpectedAnswer() {
        return expectedAnswer;
    }

    public void setExpectedAnswer(double expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }

    public double getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(double userAnswer) {
        this.userAnswer = userAnswer;
    }

    public void calculateAnswer() {
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
                expectedAnswer = operand1 / operand2;
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathQuiz mathQuiz = (MathQuiz) o;
        return operand1 == mathQuiz.operand1 &&
                operand2 == mathQuiz.operand2 &&
                Double.compare(mathQuiz.expectedAnswer, expectedAnswer) == 0 &&
                operator == mathQuiz.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, operand1, operand2, expectedAnswer);
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

    public boolean isValidAnswer() {
        if (expectedAnswer == userAnswer)
            return true;
        return false;
    }
}
