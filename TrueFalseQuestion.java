/**
 * Class: TrueFalseQuestion
 * SDG 3 - Good Health and Well-Being Application
 * Creator: Member 3  |  Tester: Member 1
 *
 * True/False question type.
 * Demonstrates: inheritance, method overriding, method overloading.
 */
public class TrueFalseQuestion extends Question {
    private boolean correctAnswer;
    private String  explanation;

    public TrueFalseQuestion(int id, String text, boolean correctAnswer) {
        super(id, text, 3);
        this.correctAnswer = correctAnswer;
        this.explanation   = "";
    }

    public TrueFalseQuestion(int id, String text, boolean correctAnswer, String explanation) {
        super(id, text, 3);
        this.correctAnswer = correctAnswer;
        this.explanation   = explanation;
    }

    /** Overrides: accepts "True"/"False"/"T"/"F" strings. */
    @Override
    public boolean checkAnswer(String answer) {
        if (answer == null || answer.trim().isEmpty())
            throw new InvalidAnswerException("Answer cannot be empty.", answer);
        String a = answer.trim().toLowerCase();
        boolean given = a.equals("true") || a.equals("t");
        return given == correctAnswer;
    }

    /** Overloaded: accepts a boolean directly. */
    public boolean checkAnswer(boolean answer) {
        return answer == correctAnswer;
    }

    @Override
    public String[] getOptions() { return new String[]{"True", "False"}; }

    @Override
    public String getType() { return "True/False"; }

    public boolean getCorrectAnswer() { return correctAnswer; }
    public String  getExplanation()   { return explanation; }
    public String  getCorrectString() { return correctAnswer ? "True" : "False"; }
}
