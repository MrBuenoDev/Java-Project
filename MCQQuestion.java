/**
 * Class: MCQQuestion
 * SDG 3 - Good Health and Well-Being Application
 * Creator: Member 3  |  Tester: Member 5
 *
 * Multiple-choice question with 4 options.
 * Demonstrates: inheritance (extends Question), method overriding,
 *               method overloading (checkAnswer with int index).
 */
public class MCQQuestion extends Question {
    private String[] options;
    private int      correctIndex;   // 0-based index into options

    public MCQQuestion(int id, String text, String[] options, int correctIndex) {
        super(id, text, 5);
        this.options      = options;
        this.correctIndex = correctIndex;
    }

    public MCQQuestion(int id, String text, String[] options, int correctIndex, int pts) {
        super(id, text, pts);
        this.options      = options;
        this.correctIndex = correctIndex;
    }

    /** Overrides: accepts the letter "A"/"B"/"C"/"D" as a string answer. */
    @Override
    public boolean checkAnswer(String answer) {
        if (answer == null || answer.trim().isEmpty())
            throw new InvalidAnswerException("Answer cannot be empty.", answer); 
        int idx = answer.trim().toUpperCase().charAt(0) - 'A';
        return idx == correctIndex;
    }

    /** Overloaded: accepts a 0-based integer index directly. */
    public boolean checkAnswer(int selectedIndex) {
        return selectedIndex == correctIndex;
    }

    @Override
    public String[] getOptions() { return options; }

    @Override
    public String getType() { return "MCQ"; }

    public int getCorrectIndex()    { return correctIndex; }
    public String getCorrectLetter(){ return String.valueOf((char)('A' + correctIndex)); }
    public String getCorrectAnswer(){ return options[correctIndex]; }
}
