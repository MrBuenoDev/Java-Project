import java.awt.*;
import java.util.Stack;
import javax.swing.*;

/**
 * Creator: Khiew
 */
public class AppController extends JFrame implements Navigable {
    
    private CardLayout cardLayout;
    private JPanel containerPanel;
    private Stack<String> screenHistory;

    public AppController() {
        setTitle("SDG 3: Health & Well-Being App");
        setSize(390, 844);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);
        screenHistory = new Stack<>();

        // 1. 实例化你自己的主菜单
        JPanel mainMenu = createMainMenuPanel();
        containerPanel.add(mainMenu, "MainMenu");

        // 2. 实例化其他组员的占位模块，并注入导航
        containerPanel.add(new LessonManager(this), "LearningScreen");
        containerPanel.add(new QuizManager(this), "QuizScreen");
        containerPanel.add(new GamificationManager(this), "LeaderboardScreen");
        containerPanel.add(new DataManager(this), "LoginScreen");

        add(containerPanel);
        
        // 初始页面设为 Elisa 的登录页
        navigateTo("LoginScreen");
    }

    @Override
    public void navigateTo(String screenName) {
        screenHistory.push(screenName);
        cardLayout.show(containerPanel, screenName);
    }

    @Override
    public void goBack() {
        if (screenHistory.size() > 1) {
            screenHistory.pop();
            cardLayout.show(containerPanel, screenHistory.peek());
        }
    }

    @Override
    public String getTitle() {
        return "Main Controller";
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 245, 230)); // 浅绿色符合SDG3健康主题
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("SDG 3 Main Menu");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnLearn = new JButton("1. Go to Lessons");
        JButton btnQuiz = new JButton("2. Take SDG Quiz");
        JButton btnLeaderboard = new JButton("3. View Leaderboard");
        JButton btnLogout = new JButton("4. Logout");

        btnLearn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuiz.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLeaderboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnLearn.addActionListener(e -> navigateTo("LearningScreen"));
        btnQuiz.addActionListener(e -> navigateTo("QuizScreen"));
        btnLeaderboard.addActionListener(e -> navigateTo("LeaderboardScreen"));
        btnLogout.addActionListener(e -> navigateTo("LoginScreen"));

        panel.add(Box.createVerticalStrut(100));
        panel.add(title);
        panel.add(Box.createVerticalStrut(80));
        panel.add(btnLearn); panel.add(Box.createVerticalStrut(20));
        panel.add(btnQuiz); panel.add(Box.createVerticalStrut(20));
        panel.add(btnLeaderboard); panel.add(Box.createVerticalStrut(20));
        panel.add(btnLogout);

        return panel;
    }
}
