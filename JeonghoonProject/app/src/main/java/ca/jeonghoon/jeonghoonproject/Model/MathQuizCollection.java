package ca.jeonghoon.jeonghoonproject.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MathQuizCollection implements Serializable {
    private final List<MathQuiz> quizList = new ArrayList<>();

    public List<MathQuiz> getQuizList() {
        return quizList;
    }
}
