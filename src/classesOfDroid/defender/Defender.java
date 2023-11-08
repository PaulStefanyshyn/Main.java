package classesOfDroid.defender;

import droid.Droid;

public class Defender extends Droid {

    //region -==- Set Start Data -==-

    protected void InitializedStartData(){
        setMinDamage(1);
        setMaxDamage(10);

        setMinHP(50);
        setMaxHP(200);

        setType("defender");
    }

    //endregion

    //region -==- CTORs -==-

    public Defender(String name, Integer HP, Integer damage) {
        super(name, HP, damage);
    }

    //endregion

    //region -==- Overload Methods -==-

    protected void levelUp(){
        int nextLevel = getLevel() + 1;
        setLevel(nextLevel);

        int newMaxHP = getMaxHP() + 50;
        setMaxHP(newMaxHP);

        int newHP = getHP() + 50;
        setHP(newHP);

        int newMaxDamage = (int)(getMaxDamage() * 1.2);
        setDamage(newMaxDamage);

        int newDamage = (int)(getDamage() * 1.2);
        setDamage(newDamage);

    }

    //endregion
}
