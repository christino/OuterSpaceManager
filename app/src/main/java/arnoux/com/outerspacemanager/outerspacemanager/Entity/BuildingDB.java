package arnoux.com.outerspacemanager.outerspacemanager.Entity;

/**
 * Created by White on 20/03/2017.
 */

public class BuildingDB {

    private int id;
    private String name;
    private int level;
    private int timeToBuildByLevel;
    private long timeBuildingLaunched;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getTimeToBuildByLevel() {
        return timeToBuildByLevel;
    }

    public long getTimeBuildingLaunched() {
        return timeBuildingLaunched;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTimeToBuildByLevel(int timeToBuildByLevel) {
        this.timeToBuildByLevel = timeToBuildByLevel;
    }

    public void setTimeBuildingLaunched(long timeBuildingLaunched) {
        this.timeBuildingLaunched = timeBuildingLaunched;
    }
}
