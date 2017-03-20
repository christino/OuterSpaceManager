package arnoux.com.outerspacemanager.outerspacemanager.Entity;

import java.util.ArrayList;

/**
 * Created by White on 20/03/2017.
 */

public class ResearchResponse {

    public final int size;
    public final ArrayList<Research> searches;

    public ResearchResponse(int size, ArrayList<Research> researches) {
        this.size = size;
        this.searches = researches;
    }
}
