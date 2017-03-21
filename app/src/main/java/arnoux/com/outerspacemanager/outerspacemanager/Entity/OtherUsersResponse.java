package arnoux.com.outerspacemanager.outerspacemanager.Entity;

import java.util.ArrayList;

/**
 * Created by White on 21/03/2017.
 */

public class OtherUsersResponse {
    public final int size;
    public final ArrayList<OtherUsers> users;

    public OtherUsersResponse(int size, ArrayList<OtherUsers> users) {
        this.size = size;
        this.users = users;
    }
}
