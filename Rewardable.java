/*
 * Developed By:
 * Ahmad Fakhrurizzudin Bin Ahmad Fairuz
 */

import java.util.List;

public interface Rewardable {

    // Award a badge
    void awardBadge(String name);

    // Add points
    void addPoints(int points);

    // Get leaderboard data
    List<String> getLeaderboard() throws FileReadException;

    // Get user rank
    String getRank();
}