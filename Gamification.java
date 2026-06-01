/*
 * Developed By:
 * Ahmad Fakhrurizzudin Bin Ahmad Fairuz
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Gamification implements Rewardable {

    // Store user points
    private int points;

    // Store earned badges
    private List<String> badges;

    // Store earned stars
    private int stars;

    // Leaderboard file
    private static final String LEADERBOARD_FILE = "leaderboard.txt";

    // Constructor
    public Gamification() {
        points = 0;
        stars = 0;
        badges = new ArrayList<>();
    }

    // Add points after quiz completion
    @Override
    public void addPoints(int points) {
        this.points += points;
    }

    // Award badge
    @Override
    public void awardBadge(String name) {

        if (!badges.contains(name)) {
            badges.add(name);
        }
    }

    // Overloaded awardBadge method
    public void awardBadge(String name, int requiredPoints) {

        if (points >= requiredPoints &&
                !badges.contains(name)) {

            badges.add(name);
        }
    }

    // Add stars earned from quiz
    public void addStars(int stars) {
        this.stars += stars;
    }

    // Read leaderboard data
    @Override
    public List<String> getLeaderboard()
            throws FileReadException {

        List<String> leaderboard =
                new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    LEADERBOARD_FILE));

            String line;

            while ((line = reader.readLine()) != null) {
                leaderboard.add(line);
            }

            reader.close();

        } catch (IOException e) {

            throw new FileReadException(
                    "Unable to read leaderboard file."
            );
        }

        return leaderboard;
    }

    // Determine user rank based on points
    @Override
    public String getRank() {

        if (points >= 200) {
            return "Quiz Master";
        }
        else if (points >= 150) {
            return "Health Champion";
        }
        else if (points >= 100) {
            return "Wellness Warrior";
        }
        else if (points >= 50) {
            return "Health Explorer";
        }

        return "Beginner";
    }

    // Save badge information to file
    public void saveToBadgeFile(String username)
            throws FileReadException {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(
                                    "badges.txt",
                                    true));

            writer.write(
                    username +
                    " | Rank: " +
                    getRank() +
                    " | Badges: " +
                    badges
            );

            writer.newLine();
            writer.close();

        } catch (IOException e) {

            throw new FileReadException(
                    "Unable to save badge file."
            );
        }
    }

    // Optional getter methods

    public int getPoints() {
        return points;
    }

    public int getStars() {
        return stars;
    }

    public List<String> getBadges() {
        return badges;
    }
}