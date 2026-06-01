import javax.swing.SwingUtilities;

/**
 * Creator: Khiew
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppController().setVisible(true);
        });
    }
}
