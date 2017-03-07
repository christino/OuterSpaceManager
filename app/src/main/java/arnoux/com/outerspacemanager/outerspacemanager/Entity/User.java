package arnoux.com.outerspacemanager.outerspacemanager.Entity;

/**
 * Created by White on 07/03/2017.
 */

public class User {
    private String username;
    private String password;
    private String token;
    private int points;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getToken() {
        return this.token;
    }

    public int getPoints() {
        return this.points;
    }
}
