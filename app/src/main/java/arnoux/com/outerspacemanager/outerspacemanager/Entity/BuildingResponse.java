package arnoux.com.outerspacemanager.outerspacemanager.Entity;

import java.util.ArrayList;

/**
 * Created by White on 14/03/2017.
 */

public class BuildingResponse {
    public final int size;
    public final ArrayList<Building> buildings;

    public BuildingResponse(int size, ArrayList<Building> buildings) {
        this.size = size;
        this.buildings = buildings;
    }
}
