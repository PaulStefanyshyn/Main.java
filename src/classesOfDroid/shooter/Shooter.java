package classesOfDroid.shooter;

import droid.Droid;
public class Shooter extends Droid {

    //region -==- Class Values -==-

    private Integer targetsNumber;

    //endregion

    //region -==- Set Start Data -==-

    protected void InitializedStartData(){
        setMinDamage(10);
        setMaxDamage(40);

        setMinHP(20);
        setMaxHP(50);

        setType("shooter");
    }

    //endregion

    //region -==- CTORs -==-

    public Shooter(String name, Integer HP, Integer damage) {
        super(name, HP, damage);
        setTargetsNumber(1);
    }

    //endregion

    //region -==- PROPs -==-

    public Integer getTargetsNumber() {
        return targetsNumber;
    }

    public Shooter setTargetsNumber(Integer targetsNumber) {
        this.targetsNumber = MustBeGreaterThanZero(targetsNumber);
        return this;
    }


    //endregion

    //region -==- Overview Methods -==-

    @Override
    public String toString() {
        return super.toString().replace('}', ' ') +
                "targetsNumber=" + targetsNumber +
                '}';
    }
    protected void levelUp(){
        int nextLevel = getLevel() + 1;
        setLevel(nextLevel);

        int newMaxHP = getMaxHP() + 20;
        setMaxHP(newMaxHP);

        int newHP = getHP() + 20;
        setHP(newHP);

        int newMaxDamage = (int)(getMaxDamage() * 1.4);
        setDamage(newMaxDamage);

        int newDamage = (int)(getDamage() * 1.4);
        setDamage(newDamage);

    }

    //endregion
}
