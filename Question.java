/**
 * Abstract Class: Question
 * SDG 3 - Good Health and Well-Being Application
 * Creator: Member 3  |  Tester: Member 4
 *
 * Abstract parent for all question types.
 * Demonstrates: abstraction, polymorphism (subclasses override checkAnswer).
 */
public abstract class Question implements Answerable {
    protected String questionText;
    protected int    questionId;
    protected int    points;

    public Question(int id, String text, int points) {
        this.questionId   = id;
        this.questionText = text;
        this.points       = points;
    }

    // Concrete methods
    @Override public String getQuestion() { return questionText; }
    @Override public int    getPoints()   { return points; }
    public    int           getId()       { return questionId; }

    // Abstract methods — subclasses MUST override
    @Override public abstract boolean  checkAnswer(String answer);
    @Override public abstract String[] getOptions();
    @Override public abstract String   getType();

    @Override
    public String toString() {
        return "[Q" + questionId + "] (" + getType() + ") " + questionText;
    }
}
