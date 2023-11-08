package droid;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

abstract public class Droid {

    //region -==- Class's Values -==-
    protected String name;
    protected Integer damage;
    protected Integer maxDamage;
    protected Integer minDamage;
    protected Integer HP;
    protected Integer maxHP;
    protected Integer minHP;
    protected String type;
    protected Integer experience = 0;
    protected Integer experienceLimit = 50;
    protected Integer level = 1;


    //endregion

    //region -==- CTORs -==-
    public Droid(){

    }
    public Droid(String name, Integer health, Integer damage){
        InitializedStartData();
        setName(name);
        setHP(health);
        setDamage(damage);
    }

    //endregion

    //region -==- PROPs -==-

    public String getName() {
        return name;
    }

    public Droid setName(String name) {
        if (!IsValueNull(name) && name != ""){
            this.name = name;
        }
        else {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss.ms");

            this.name = "droid."+ formatter.format(currentDateTime);
        }
        return this;
    }

    public Integer getDamage() {
        return damage;
    }

    public Droid setDamage(Integer damage) {

        if (!IsInTheRange(damage, minDamage, maxDamage)){
            damage = SetExtraData(damage, minDamage, maxDamage);
        }

        if (IsValueNull(HP)){
            this.damage = MustBeGreaterThanZero(damage);
        }
        else {
            this.damage = MustBeGreaterThanZero(CalculateDamage());
        }

        return this;
    }

    public Integer getMaxDamage() {
        return maxDamage;
    }

    public Droid setMaxDamage(Integer maxDamage) {
        this.maxDamage = PositiveException(maxDamage);
        return this;
    }

    public Integer getMinDamage() {
        return minDamage;
    }

    public Droid setMinDamage(Integer minDamage) {
        this.minDamage = PositiveException(minDamage);
        return this;
    }

    public Integer getHP() {
        return HP;
    }

    public Droid setHP(Integer HP) {

        /*if (!IsInTheRange(HP, minHP, maxHP)){
            HP = SetExtraData(HP, minHP, maxHP);
        }*/

        if (this.HP == null || this.damage == null){
            if (IsValueNull(damage)){
                this.HP = HP;
            }
            else {
                this.HP = CalculateHP();
            }
        }

        if (HP < 0){
            this.HP = 0;
        }else if (HP > getMaxHP()){
            this.HP = getMaxHP();
        }else {
            this.HP = HP;
        }

        return this;
    }

    public Integer getMaxHP() {
        return maxHP;
    }

    public Droid setMaxHP(Integer maxHP) {
        this.maxHP = MustBeGreaterThanZero(maxHP);
        return this;
    }

    public Integer getMinHP() {
        return minHP;
    }

    public Droid setMinHP(Integer minHP) {
        this.minHP = MustBeGreaterThanZero(minHP);
        return this;
    }

    public String getType() {
        return type;
    }

    public Droid setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getExperience() {
        return experience;
    }

    public Droid setExperience(Integer experience) {
        this.experience = PositiveException(experience);
        return this;
    }

    public Integer getExperienceLimit() {
        return experienceLimit;
    }

    public Droid setExperienceLimit(Integer experienceLimit) {
        this.experienceLimit = PositiveException(experienceLimit);
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public Droid setLevel(Integer level) {
        this.level = Math.abs(level);
        return this;
    }

    //endregion

    //region -==- OVERRIDE Methods -==-

    @Override
    public String toString() {
        return "Droid{" +
                "type='" + type + '\'' +
                ", name='" + name +
                ", damage=" + damage +
                ", health=" + HP +
                ", experience=" + experience +
                ", experienceLimit=" + experienceLimit +
                ", minDamage='" + minDamage +
                ", maxDamage='" + maxDamage +
                ", minHP=" + minHP +
                ", maxHP=" + maxHP + '\'' +
                '}';
    }



    //endregion

    //region -==- Abstract methods -==-

    protected abstract void InitializedStartData();

    /*protected abstract Integer Move();
    protected abstract Integer Attack();*/

    protected abstract void levelUp(); /*{
        level++;
        // Adjust attributes or grant additional abilities when leveling up
        hp += 10;
        damage += 5;
    }*/

    //endregion

   //region -==- Non abstract methods -==-

    //region -==- Calculate Methods -==-
    protected Integer CalculateDamage(){

            Integer HPDifferent = HP- minHP;

            Integer HPLimitDifferent = maxHP - minHP;

            Float HPDiv = (float)HPDifferent / HPLimitDifferent;

            Float HPPercent = 1 - HPDiv;

            Integer DroidDamage = (int)(HPPercent * maxDamage);
            return DroidDamage;
        }

    protected Integer CalculateHP(){
        Integer DamageDifferent = damage - minDamage;

        Integer DamageLimitDifferent = maxDamage - minDamage;

        Integer HPLimitDifferent = maxHP - minHP;

        Float HPLimitPercent = (float)HPLimitDifferent / DamageLimitDifferent;

        Float DamagePercent = DamageDifferent * HPLimitPercent;

        Integer DroidHP = (int)(DamagePercent + minHP);

        return  DroidHP;
    }

    //endregion

    //region -==- Check Methods -==-
    private Boolean IsValueNull(String value){
        Boolean isNull = value == null;
        return isNull;
    }

    protected Boolean IsValueNull(Integer value){
        Boolean isNull = value == null;
        return isNull;
    }

    protected Boolean IsValueGreaterThanMinValue(Integer value, Integer minValue){
        Boolean isGreaterThanZero = value >= minValue;
        return  isGreaterThanZero;
    }

    protected Boolean IsValueLowerThanMaxValue(Integer value, Integer maxValue){
        Boolean isGreaterThanMaxValue = value <= maxValue;
        return  isGreaterThanMaxValue;
    }

    protected Boolean IsInTheRange(Integer value, Integer minValue, Integer maxValue){
        Boolean isInTheRange = IsValueGreaterThanMinValue(value, minValue) &&
                IsValueLowerThanMaxValue(value, maxValue);
        return isInTheRange;
    }

    private void checkLevelUp() {
        // Define the logic for leveling up based on experience points
        if (getExperience() >= getExperienceLimit()) {
            levelUp();

            int changeExperienceCount = getExperience() - getExperienceLimit();
            setExperience(changeExperienceCount);

            int newExperienceLimit = getExperienceLimit() * 2;
            setExperienceLimit(newExperienceLimit);
        }
    }

    //endregion

    //region -==- Set Exception Methods -==-
    protected Integer SetExtraData(Integer value, Integer minValue, Integer maxValue){
        if (!IsValueGreaterThanMinValue(value, minValue)){
            value = minValue;
        } else {
            value = maxValue;
        }
        return value;
    }

    protected Integer PositiveException(Integer value){
        if (value < 0){
            value = 0;
        }
        return value;
    }

    protected Integer MustBeGreaterThanZero(Integer value){
        if (value <= 0){
            value = 1;
        }
        return value;
    }

    //endregion

    //endregion

    //region -==- BattleLogic Methods -==-
    protected Boolean isAlive() {
        return getHP() > 0;
    }
    protected Integer IncreaseDamage(int boostDamage){
        setDamage(boostDamage);
        return boostDamage;
    }

    protected Integer gainExperience(int experiencePoints) {
        int newExperience = getExperience() + experiencePoints;
        setExperience(newExperience);
        checkLevelUp();
        return getExperience();
    }



    //endregion

    /*public abstract static class Builder{

        //region -==- Class's Values -==-
        protected String name;
        protected Integer damage;
        protected static Integer maxDamage;
        protected static Integer minDamage;
        protected Integer HP;
        protected static Integer maxHP;
        protected static Integer minHP;
        protected static String type;
        protected Integer Experience = 0;
        protected static Integer ExperienceLimit = 50;

        //endregion

        //region -==- PROPs -==-

        public String getName() {
            return name;
        }

        public Builder setName(String name) {
            if (!IsValueNull(name)){
                this.name = name;
            }
            else {
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                this.name = "droid"+ formatter.format(currentDateTime);
            }
            return this;
        }

        public Integer getDamage() {
            return damage;
        }

        public Builder setDamage(Integer damage) {

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

        public Integer getMaxDamage() {
            return maxDamage;
        }

        public Builder setMaxDamage(Integer maxDamage) {
            this.maxDamage = PositiveException(maxDamage);
            return this;
        }

        public Integer getMinDamage() {
            return minDamage;
        }

        public Builder setMinDamage(Integer minDamage) {
            this.minDamage = PositiveException(minDamage);
            return this;
        }

        public Integer getHP() {
            return HP;
        }

        public Builder setHP(int HP) {

            if (!IsValueNull(HP)){
                if (!IsInTheRange(HP, minHP, maxHP)){
                    HP = SetExtraData(HP, minHP, maxHP);
                }

                if (IsValueNull(damage)){
                    this.HP = HP;
                }
                else {
                    this.HP = CalculateHP();
                }
            }
            else {
                this.HP = minHP;
            }

            return this;
        }

        public Integer getMaxHP() {
            return maxHP;
        }

        public Builder setMaxHP(Integer maxHP) {
            this.maxHP = MustBeGreaterThanZero(maxHP);
            return this;
        }

        public Integer getMinHP() {
            return minHP;
        }

        public Builder setMinHP(Integer minHP) {
            this.minHP = MustBeGreaterThanZero(minHP);
            return this;
        }

        public String getType() {
            return type;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Integer getExperience() {
            return Experience;
        }

        public Builder setExperience(Integer experience) {
            Experience = PositiveException(experience);
            return this;
        }

        public Integer getExperienceLimit() {
            return ExperienceLimit;
        }

        public Builder setExperienceLimit(Integer experienceLimit) {
            ExperienceLimit = PositiveException(experienceLimit);
            return this;
        }


        //endregion

        //region -==- OVERRIDE Methods -==-

        @Override
        public String toString() {
            return "Droid{" +
                    "name='" + name + '\'' +
                    ", damage=" + damage +
                    ", health=" + HP +
                    ", type='" + type + '\'' +
                    '}';
        }

        //endregion

        //region -==- Abstract methods -==-

    protected abstract Integer Move();
    protected abstract Integer Attack();

        //endregion

        //region -==- Non abstract methods -==-


        /*
        protected Integer CalculateDamage(){

            Integer HPDifferent = HP- minHP;

            Integer HPLimitDifferent = maxHP - minHP;

            Float HPDiv = (float)HPDifferent / HPLimitDifferent;

            Float HPPercent = 1 - HPDiv;

            Integer DroidDamage = (int)(HPPercent * maxDamage);
            return DroidDamage;
        }

        protected Integer CalculateHP(){
            Integer DamageDifferent = damage - minDamage;

            Integer DamageLimitDifferent = maxDamage - minDamage;

            Integer HPLimitDifferent = maxHP - minHP;

            Float HPLimitPercent = (float)HPLimitDifferent / DamageLimitDifferent;

            Float DamagePercent = DamageDifferent * HPLimitPercent;

            Integer DroidHP = (int)(DamagePercent + minHP);

            return  DroidHP;
        }

        //endregion

        //region -==- Check Methods -==-
        private Boolean IsValueNull(String value){
            boolean isNull = value == null;
            return isNull;
        }

        private Boolean IsValueNull(Integer value){
            boolean isNull = value == null;
            return isNull;
        }

        private Boolean IsValueGreaterThanMinValue(int value, int minValue){
            boolean isGreaterThanZero = value >= minValue;
            return  isGreaterThanZero;
        }

        private Boolean IsValueLowerThanMaxValue(int value, int maxValue){
            boolean isGreaterThanMaxValue = value <= maxValue;
            return  isGreaterThanMaxValue;
        }

        private Boolean IsInTheRange(int value, int minValue, int maxValue){
            boolean isInTheRange = IsValueGreaterThanMinValue(value, minValue) &&
                    IsValueLowerThanMaxValue(value, maxValue);
            return isInTheRange;
        }

        //endregion

        //region -==- Set Exception Methods -==-
        private Integer SetExtraData(Integer value, Integer minValue, Integer maxValue){
            if (!IsValueGreaterThanMinValue(value, minValue)){
                value = minValue;
            } else {
                value = maxValue;
            }
            return value;
        }

        private Integer PositiveException(Integer value){
            if (value < 0){
                value = 0;
            }
            return value;
        }

        private Integer MustBeGreaterThanZero(Integer value){
            if (value <= 0){
                value = 1;
            }
            return value;
        }

        //endregion

        //endregion

        public abstract Droid build();

    }*/
}
