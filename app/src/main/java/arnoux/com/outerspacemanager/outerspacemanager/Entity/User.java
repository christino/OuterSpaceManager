package arnoux.com.outerspacemanager.outerspacemanager.Entity;

/**
 * Created by White on 07/03/2017.
 */

public class User {
    private String username;
    private String email;
    private String password;
    private String token;
    private Double points;
    private Double gas;
    private Double minerals;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
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
