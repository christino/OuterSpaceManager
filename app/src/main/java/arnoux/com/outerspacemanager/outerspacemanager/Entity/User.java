package arnoux.com.outerspacemanager.outerspacemanager.Entity;

/**
 * Created by White on 07/03/2017.
 */

public class User {
    private String username;
    private String password;
    private String token;
    private Double points;
    private Double gas;
    private Double minerals;

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

    public Double getPoints() {
        return this.points;
    }

    public Double getGas() {
        return this.gas;
    }

    public Double getMinerals() {
        return this.minerals;
    }
}
