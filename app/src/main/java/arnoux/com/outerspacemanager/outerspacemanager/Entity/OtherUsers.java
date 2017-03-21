package arnoux.com.outerspacemanager.outerspacemanager.Entity;

/**
 * Created by White on 14/03/2017.
 */

public class OtherUsers {
    private String username;
    private Double points;

    public OtherUsers(String username, Double points) {
        this.username = username;
        this.points = points;
    }

    public String getUsername() {
        return this.username;
    }

    public Double getPoints() {
        return this.points;
    }
}
