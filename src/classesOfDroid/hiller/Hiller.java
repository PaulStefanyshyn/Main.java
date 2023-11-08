package classesOfDroid.hiller;

import droid.Droid;

public class Hiller extends Droid {

    //region -==- Class Values -==-
    private Integer rescueHP;
    private Integer rescueNumberOfDroid;

    private Integer minRescueHP;
    private Integer maxRescueHP;

    //endregion

    //region -==- Set Start Data -==-

    @Override
    protected void InitializedStartData(){
        setMinDamage(0);
        setMaxDamage(0);

        setMinHP(20);
        setMaxHP(50);

        setType("hiller");

        setMinRescueHP(1);
        setMaxRescueHP(20);
    }

    //endregion

    //region -==- CTORs -==-

    public Hiller(String name, Integer HP, Integer damage, Integer rescueHP) {
        super(name, HP, damage);
        setRescueHP(rescueHP);
        setRescueNumberOfDroid(1);
    }

    public Hiller() { }
    //endregion

    //region -==- PROPs -==-

    public Integer getRescueHP() {
        return rescueHP;
    }

    public Hiller setRescueHP(Integer rescueHP) {
        if (!IsValueNull(rescueHP)){
            if (IsInTheRange(rescueHP, minRescueHP, maxRescueHP)){
                this.rescueHP = rescueHP;
            }
            else {
                this.rescueHP = SetExtraData(rescueHP, minRescueHP, maxRescueHP);
            }
        }
        else {
            this.rescueHP = minRescueHP;
        }
        return this;
    }

    public Integer getRescueNumberOfDroid() {
        return rescueNumberOfDroid;
    }

    public Hiller setRescueNumberOfDroid(Integer rescueNumberOfDroid) {
        this.rescueNumberOfDroid = MustBeGreaterThanZero(rescueNumberOfDroid);
        return this;
    }

    public Integer getMinRescueHP() {
        return minRescueHP;
    }

    public void setMinRescueHP(Integer minRescueHP) {
        this.minRescueHP = minRescueHP;
    }

    public Integer getMaxRescueHP() {
        return maxRescueHP;
    }

    public void setMaxRescueHP(Integer maxRescueHP) {
        this.maxRescueHP = maxRescueHP;
    }


    //endregion

    //region -==- Overload Methods -==-

    @Override
    public Droid setDamage(Integer damage) {

        if (!IsInTheRange(damage, minDamage, maxDamage)){
            damage = SetExtraData(damage, minDamage, maxDamage);
        }

        if (IsValueNull(HP)){
            this.damage = damage;
        }
        else {
            this.damage = CalculateDamage();
        }

        return this;
    }

    @Override
    public String toString() {
        return super.toString().replace('}', ' ') +
                ", rescueHP=" + rescueHP +
                ", rescueNumberOfDroid=" + rescueNumberOfDroid +
                ", minRescueHP=" + minRescueHP +
                ", maxRescueHP=" + maxRescueHP +
                '}';
    }

    protected void levelUp(){
        int nextLevel = getLevel() + 1;
        setLevel(nextLevel);

        int newMaxHP = getMaxHP() + 10;
        setMaxHP(newMaxHP);

        int newHP = getHP() + 10;
        setHP(newHP);

        int newMaxRescueHP = (int)(getMaxRescueHP() * 1.5);
        setRescueHP(newMaxRescueHP);

        int newRescueHP = (int)(getRescueHP() * 1.5);
        setRescueHP(newRescueHP);


    }
    //endregion
}
