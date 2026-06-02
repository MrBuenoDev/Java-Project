
import java.io.*;
import java.util.*;

public class DataManager {

    private static final String FILE_NAME = "scores.txt";

    // SAVE USER SCORE
    public void saveData(User user) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(user.toString());
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // LOAD ALL USERS
    public List<User> loadData() {
        List<User> users = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return users;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                if (parts.length == 2) {
                    users.add(new User(parts[0], Integer.parseInt(parts[1])));
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading data");
        }

        return users;
    }

    // DISPLAY LEADERBOARD
    public void displayLeaderboard() {
        List<User> users = loadData();

        users.sort((a, b) -> b.getScore() - a.getScore());

        System.out.println("=== LEADERBOARD ===");

        int rank = 1;

        for (User u : users) {
            System.out.println(rank + ". " + u.getUsername() + " - " + u.getScore());
            rank++;
        }
    }

    // FIND USER
    public User findUser(String username) throws UserNotFoundException {

        List<User> users = loadData();

        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }

        throw new UserNotFoundException("User not found: " + username);
    }

    // CLEAR FILE
    public void clearData() {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            fw.write("");
        } catch (IOException e) {
            System.out.println("Error clearing file");
        }
    }
}