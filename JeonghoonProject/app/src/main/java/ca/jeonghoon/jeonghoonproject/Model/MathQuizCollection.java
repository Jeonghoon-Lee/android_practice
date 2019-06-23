package ca.jeonghoon.jeonghoonproject.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MathQuizCollection implements Serializable {
    private final List<MathQuiz> quizList = new ArrayList<>();

    public List<MathQuiz> getQuizList() {
        return quizList;
    }

    public int getScore() {
        int rightAnswerCount = 0;

        for (MathQuiz quiz : quizList) {
            if (quiz.isValidAnswer()) {
                rightAnswerCount++;
            }
        }
        // return score
        return (int) (((double) rightAnswerCount) / quizList.size() * 100.);
    }
}
