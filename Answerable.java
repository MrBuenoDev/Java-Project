public interface Answerable {
    boolean checkAnswer(String answer);
    String getQuestion();
    String[] getOptions();
    String getType();
    int getPoints();
}