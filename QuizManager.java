import java.util.*;

/**
 * Class: QuizManager
 * SDG 3 - Good Health and Well-Being Application
 * Creator: Member 3  |  Tester: Member 5
 *
 * Loads 20+ questions (MCQ + True/False), tracks score, gives feedback.
 * Implements: Answerable (via delegation to Question objects)
 * OOP shown: polymorphism (List<Question> holds MCQ + TF), abstraction,
 *            inheritance chain used, custom exception handling.
 */
public class QuizManager {

    private List<Question> questions;
    private int            currentIndex;
    private int            score;
    private int            totalPoints;
    private boolean        quizFinished;

    public QuizManager() {
        this.questions    = new ArrayList<>();
        this.currentIndex = 0;
        this.score        = 0;
        this.totalPoints  = 0;
        this.quizFinished = false;
        loadQuestions();
    }

    // ── Answer submission ─────────────────────────────────────────────────────

    /**
     * Submits an answer for the current question.
     * Uses polymorphism: calls checkAnswer() on whatever Question subtype is current.
     * @return true if correct
     */
    public boolean submitAnswer(String answer) {
        if (quizFinished) return false;
        Question q = getCurrentQuestion();
        try {
            boolean correct = q.checkAnswer(answer);
            if (correct) {
                score++;
                totalPoints += q.getPoints();
            }
            return correct;
        } catch (InvalidAnswerException e) {
            System.err.println("[QuizManager] Invalid answer: " + e.getMessage());
            return false;
        }
    }

    public boolean nextQuestion() {
        if (currentIndex < questions.size() - 1) {
            currentIndex++;
            return true;
        }
        quizFinished = true;
        return false;
    }

    public void reset() {
        currentIndex = 0;
        score        = 0;
        totalPoints  = 0;
        quizFinished = false;
    }

    // ── Results ───────────────────────────────────────────────────────────────

    public int getScore()          { return score; }
    public int getTotalQuestions() { return questions.size(); }
    public int getTotalPoints()    { return totalPoints; }
    public boolean isFinished()    { return quizFinished || currentIndex >= questions.size() - 1; }

    public int getScorePercent() {
        return (int) Math.round(score * 100.0 / questions.size());
    }

    /** Returns motivational feedback per rubric score ranges. */
    public String getFeedback() {
        int pct = getScorePercent();
        if (pct >= 80) return "Outstanding!";
        if (pct >= 60) return "That's good!";
        if (pct >= 40) return "Good try!";
        if (pct >= 20) return "You can do better!";
        return "Don't give up!";
    }

    public String getResultSummary() {
        return "You scored " + score + " / " + questions.size()
             + "  (" + getScorePercent() + "%)  —  " + getFeedback();
    }

    // ── Current question getters ──────────────────────────────────────────────

    public Question getCurrentQuestion()  { return questions.get(currentIndex); }
    public int      getCurrentIndex()     { return currentIndex; }
    public String   getCurrentQuestionText()  { return getCurrentQuestion().getQuestion(); }
    public String[] getCurrentOptions()   { return getCurrentQuestion().getOptions(); }
    public String   getCurrentType()      { return getCurrentQuestion().getType(); }

    public String getCorrectAnswerForCurrent() {
        Question q = getCurrentQuestion();
        if (q instanceof MCQQuestion)
            return ((MCQQuestion) q).getCorrectLetter() + ") "
                 + ((MCQQuestion) q).getCorrectAnswer();
        if (q instanceof TrueFalseQuestion)
            return ((TrueFalseQuestion) q).getCorrectString();
        return "N/A";
    }

    // ── Question bank (20 questions: 13 MCQ + 7 True/False) ──────────────────

   private void loadQuestions() {
        // ── Multiple Choice Questions (Q1–Q12) ──────────────────────────────
        
        questions.add(new MCQQuestion(1,
            "Which Sustainable Development Goal (SDG) specifically focuses on Good Health & Well-Being?",
            new String[]{"A) SDG 1", "B) SDG 3", "C) SDG 5", "D) SDG 10"}, 1));

        questions.add(new MCQQuestion(2,
            "By 2030, how much does the UN aim to reduce premature mortality from non-communicable diseases (NCDs)?",
            new String[]{"A) One-quarter", "B) One-half", "C) One-third", "D) Completely eliminate"}, 2));

        questions.add(new MCQQuestion(3,
            "What does \"NCD\" stand for?",
            new String[]{"A) Non-Contagious Disease", "B) Non-Communicable Disease", "C) National Care Directive", "D) Newly Contracted Disease"}, 1));

        questions.add(new MCQQuestion(4,
            "Which of the following is NOT considered one of the \"Big Four\" NCDs?",
            new String[]{"A) Cardiovascular Diseases", "B) Cancers", "C) Influenza", "D) Diabetes"}, 2));

        questions.add(new MCQQuestion(5,
            "Conditions like Asthma and COPD fall under which major category of NCDs?",
            new String[]{"A) Chronic Respiratory Diseases", "B) Cardiovascular Diseases", "C) Cancers", "D) Mental Health Disorders"}, 0));

        questions.add(new MCQQuestion(6,
            "Which of the following is a primary lifestyle driver that significantly increases the risk of NCDs?",
            new String[]{"A) Drinking enough water", "B) Physical inactivity", "C) Sleeping 8 hours a night", "D) Reading daily"}, 1));

        questions.add(new MCQQuestion(7,
            "According to the lesson, what is the best \"medicine\" against developing an NCD?",
            new String[]{"A) Surgery", "B) Antibiotics", "C) Prevention", "D) Ignoring early symptoms"}, 2));

        questions.add(new MCQQuestion(8,
            "Mental health encompasses our emotional, psychological, and _________ well-being.",
            new String[]{"A) Financial", "B) Social", "C) Educational", "D) Professional"}, 1));

        questions.add(new MCQQuestion(9,
            "Which mental health condition is specifically characterized by persistent sadness and a lack of interest in previously enjoyable activities?",
            new String[]{"A) Anxiety", "B) Depression", "C) COPD", "D) Diabetes"}, 1));

        questions.add(new MCQQuestion(10,
            "How are physical health and mental health connected?",
            new String[]{"A) They are entirely separate from one another.", "B) Only physical health impacts mental health, not vice versa.", "C) Living with a chronic NCD can lead to depression.", "D) Mental health cannot affect the physical body."}, 2));

        questions.add(new MCQQuestion(11,
            "What is the primary barrier that often prevents people from seeking help for mental health conditions?",
            new String[]{"A) Stigma", "B) Overconfidence", "C) Too much free time", "D) Excellent physical health"}, 0));

        questions.add(new MCQQuestion(12,
            "Which of the following is an effective way to manage and treat mental health conditions?",
            new String[]{"A) Ignoring the problem", "B) Avoiding social support systems", "C) Therapy, counseling, and medication", "D) Engaging in harmful alcohol use"}, 2));

        // ── True/False Questions (Q13–Q20) ──────────────────────────────────

        questions.add(new TrueFalseQuestion(13,
            "NCDs are highly contagious and spread easily from person to person.",
            false, "NCDs do not spread from person to person."));

        questions.add(new TrueFalseQuestion(14,
            "Non-Communicable Diseases are the leading cause of death globally.",
            true, "Correct. NCDs account for the majority of global deaths."));

        questions.add(new TrueFalseQuestion(15,
            "Genetics are the only factor that causes NCDs; your lifestyle choices do not matter.",
            false, "Lifestyle choices are the primary drivers."));

        questions.add(new TrueFalseQuestion(16,
            "Routine medical check-ups can drastically lower your risk of developing an NCD.",
            true, "Early detection is key to effective prevention."));

        questions.add(new TrueFalseQuestion(17,
            "Anxiety disorders typically involve excessive fear or worry.",
            true, "This is a core characteristic of anxiety."));

        questions.add(new TrueFalseQuestion(18,
            "Untreated mental health conditions can increase the risk of developing physical diseases like heart disease.",
            true, "Physical and mental health are deeply connected."));

        questions.add(new TrueFalseQuestion(19,
            "We should treat mental health with the exact same urgency as physical health.",
            true, "Mental health is just as important as physical health."));

        questions.add(new TrueFalseQuestion(20,
            "Professional help is effective, and no one has to face mental health challenges alone.",
            true, "Therapy and support systems are highly effective and available."));

        // Shuffle for variety
        Collections.shuffle(questions);
    }
}
