package classesOfDroid.fighter;

import droid.Droid;
public class Fighter extends Droid {

    //region -==- Set Start Data -==-

    @Override
    protected void InitializedStartData(){
        setMinDamage(10);
        setMaxDamage(45);

        setMinHP(20);
        setMaxHP(60);

        setType("fighter");
    }


    //endregion

    //region -==- CTORs -==-

    public Fighter(String name, Integer HP, Integer damage) {
        super(name, HP, damage);
    }

    //endregion

    //region -==- Overload Methods -==-

    protected void levelUp(){
        int nextLevel = getLevel() + 1;
        setLevel(nextLevel);

        int newMaxHP = getMaxHP() + 20;
        setMaxHP(newMaxHP);

        int newHP = getHP() + 20;
        setHP(newHP);

        int newMaxDamage = (int)(getMaxDamage() * 1.5);
        setDamage(newMaxDamage);

        int newDamage = (int)(getDamage() * 1.5);
        setDamage(newDamage);

    }

    //endregion
}
