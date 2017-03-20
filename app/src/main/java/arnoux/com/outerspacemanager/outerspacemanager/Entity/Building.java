package arnoux.com.outerspacemanager.outerspacemanager.Entity;

/**
 * Created by White on 14/03/2017.
 */

public class Building {

    private final int level;
    private final int amountOfEffectByLevel;
    private final int buildingId;
    private final boolean building;
    private final String effect;
    private final int gasCostByLevel;
    private final int gasCostLevel0;
    private final int mineralCostByLevel;
    private final int mineralCostLevel0;
    private final String imageUrl;
    private final String name;
    private final int timeToBuildByLevel;
    private final int timeToBuildLevel0;

    public Building(int level, int amountOfEffectByLevel, boolean building, int buildingId, String effect, int gasCostByLevel, int gasCostLevel0, int mineralCostByLevel, int mineralCostLevel0, String imageUrl, String name, int timeToBuildByLevel, int timeToBuildLevel0) {
        this.level = level;
        this.amountOfEffectByLevel = amountOfEffectByLevel;
        this.buildingId = buildingId;
        this.building = building;
        this.effect = effect;
        this.gasCostByLevel = gasCostByLevel;
        this.gasCostLevel0 = gasCostLevel0;
        this.mineralCostByLevel = mineralCostByLevel;
        this.mineralCostLevel0 = mineralCostLevel0;
        this.imageUrl = imageUrl;
        this.name = name;
        this.timeToBuildByLevel = timeToBuildByLevel;
        this.timeToBuildLevel0 = timeToBuildLevel0;
    }

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getAmountOfEffectByLevel() {
        return amountOfEffectByLevel;
    }

    public Boolean getInConstruction() {
        return building;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getGasCostByLevel() {
        return gasCostByLevel;
    }

    public Integer getMineralCostByLevel() { return mineralCostByLevel; }

    public Integer getBuildingId() {
        return buildingId;
    }

    public Integer getTimeToBuildByLevel() { return timeToBuildByLevel; }
}
