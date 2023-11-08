package droidsCollections.createdDroids;

import droid.Droid;

import java.util.ArrayList;
public class DroidCollection {
    private ArrayList<Droid> hillerList = new ArrayList<>();
    private ArrayList<Droid> fighterList = new ArrayList<>();
    private ArrayList<Droid> defenderList = new ArrayList<>();
    private ArrayList<Droid> shooterList = new ArrayList<>();

    //region -==- PRPOs -==-

    public int getAllListSize(){
        return hillerList.size() + fighterList.size() + defenderList.size() +
                shooterList.size();
    }

    public int getHillerListSize(){
        return hillerList.size();
    }

    public int getFighterListSize(){
        return fighterList.size();
    }

    public int getDefenderListSize(){
        return defenderList.size();
    }

    public int getShooterListSize(){
        return shooterList.size();
    }

    public void addDroidToCollection(Droid droid) {
        if (droid.getType() == "hiller".toLowerCase()){
            hillerList.add(droid);
        } else if (droid.getType() == "fighter".toLowerCase()) {
            fighterList.add(droid);
        } else if (droid.getType() == "defender".toLowerCase()) {
            defenderList.add(droid);
        } else if (droid.getType() == "shooter".toLowerCase()) {
            shooterList.add(droid);
        }
    }
    public ArrayList<Droid> getHillerList() {
        return hillerList;
    }

    public ArrayList<Droid> getFighterList() {
        return fighterList;
    }

    public ArrayList<Droid> getDefenderList() {
        return defenderList;
    }


    public ArrayList<Droid> getShooterList() {
        return shooterList;
    }
    //endregion
}
