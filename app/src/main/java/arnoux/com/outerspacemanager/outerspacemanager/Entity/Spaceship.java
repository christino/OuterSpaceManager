package arnoux.com.outerspacemanager.outerspacemanager.Entity;

/**
 * Created by White on 27/03/2017.
 */

public class Spaceship {

    private final int gasCost;
    private final int life;
    private final int maxAttack;
    private final int minAttack;
    private final int mineralCost;
    private final String name;
    private final int shipId;
    private final int shield;
    private final int spatioportLevelNeeded;
    private final int speed;
    private final int timeToBuild;

    public Spaceship(int gasCost, int life, int maxAttack, int minAttack, int mineralCost, String name, int shipId, int shield, int spatioportLevelNeeded, int speed, int timeToBuild) {
        this.gasCost = gasCost;
        this.life = life;
        this.maxAttack = maxAttack;
        this.minAttack = minAttack;
        this.mineralCost = mineralCost;
        this.name = name;
        this.shipId = shipId;
        this.shield = shield;
        this.spatioportLevelNeeded = spatioportLevelNeeded;
        this.speed = speed;
        this.timeToBuild = timeToBuild;
    }

    public int getGasCost() {
        return gasCost;
    }

    public int getLife() {
        return life;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public int getMinAttack() {
        return minAttack;
    }

    public int getMineralCost() {
        return mineralCost;
    }

    public String getName() {
        return name;
    }

    public int getShipId() {
        return shipId;
    }

    public int getShield() {
        return shield;
    }

    public int getSpatioportLevelNeeded() {
        return spatioportLevelNeeded;
    }

    public int getSpeed() {
        return speed;
    }

    public int getTimeToBuild() {
        return timeToBuild;
    }
}
