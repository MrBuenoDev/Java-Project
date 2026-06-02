import data.*;

public class MainTest {
    public static void main(String[] args) {

        DataManager dm = new DataManager();

        // TEST SAVE
        User u1 = new User("Ahmad", 85);
        dm.saveData(u1);

        User u2 = new User("Ali", 70);
        dm.saveData(u2);

        // TEST DISPLAY
        dm.displayLeaderboard();
    }
}