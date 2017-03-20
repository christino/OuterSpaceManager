package arnoux.com.outerspacemanager.outerspacemanager.Entity;

/**
 * Created by White on 20/03/2017.
 */

public class Research {

    private final int amountOfEffectByLevel;
    private final int amountOfEffectLevel0;
    private final boolean building;
    private final String effect;
    private final int gasCostByLevel;
    private final int gasCostLevel0;
    private final int level;
    private final int mineralCostByLevel;
    private final int mineralCostLevel0;
    private final String name;
    private final int timeToBuildByLevel;
    private final int timeToBuildLevel0;
    private final int searchId;

    public Research(int amountOfEffectByLevel, int amountOfEffectLevel0, boolean building, String effect, int gasCostByLevel, int gasCostLevel0, int level, int mineralCostByLevel, int mineralCostLevel0, String name, int timeToBuildByLevel, int timeToBuildLevel0, int searchId) {
        this.amountOfEffectByLevel = amountOfEffectByLevel;
        this.amountOfEffectLevel0 = amountOfEffectLevel0;
        this.building = building;
        this.effect = effect;
        this.gasCostByLevel = gasCostByLevel;
        this.gasCostLevel0 = gasCostLevel0;
        this.level = level;
        this.mineralCostByLevel = mineralCostByLevel;
        this.mineralCostLevel0 = mineralCostLevel0;
        this.name = name;
        this.timeToBuildByLevel = timeToBuildByLevel;
        this.timeToBuildLevel0 = timeToBuildLevel0;
        this.searchId = searchId;
    }

    public int getAmountOfEffectByLevel() {
        return amountOfEffectByLevel;
    }

    public int getAmountOfEffectLevel0() {
        return amountOfEffectLevel0;
    }

    public boolean isBuilding() {
        return building;
    }

    public String getEffect() {
        return effect;
    }

    public int getGasCostByLevel() {
        return gasCostByLevel;
    }

    public int getGasCostLevel0() {
        return gasCostLevel0;
    }

    public int getLevel() {
        return level;
    }

    public int getMineralCostByLevel() {
        return mineralCostByLevel;
    }

    public int getMineralCostLevel0() {
        return mineralCostLevel0;
    }

    public String getName() {
        return name;
    }

    public int getTimeToBuildByLevel() {
        return timeToBuildByLevel;
    }

    public int getTimeToBuildLevel0() {
        return timeToBuildLevel0;
    }

    public int getSearchId() {
        return searchId;
    }
}