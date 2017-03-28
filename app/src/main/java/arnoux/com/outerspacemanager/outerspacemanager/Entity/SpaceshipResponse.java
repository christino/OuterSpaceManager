package arnoux.com.outerspacemanager.outerspacemanager.Entity;

import java.util.ArrayList;

/**
 * Created by White on 27/03/2017.
 */

public class SpaceshipResponse {

    public final int size;
    public final ArrayList<Spaceship> ships;

    public SpaceshipResponse(int size, ArrayList<Spaceship> ships) {
        this.size = size;
        this.ships = ships;
    }
}
