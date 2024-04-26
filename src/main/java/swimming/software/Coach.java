package swimming.software;

public class Coach {
    // Attributes for Swimming Coach
    private int coachKey;
    private String coachName;
    private int coachRating;

    // Constructor for Swimming Coach
    public Coach(int coachKey, String coachName, int coachRating) {
        this.coachKey = coachKey;
        this.coachName = coachName;
        this.coachRating = coachRating;
    }

    // Getters and Setters for Swimming Coach
    public int getCoachKey() {
        return coachKey;
    }

    public void setCoachKey(int coachKey) {
        this.coachKey = coachKey;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getCoachRating() {
        return coachRating;
    }

    public void setCoachRating(int coachRating) {
        this.coachRating = coachRating;
    }
}
