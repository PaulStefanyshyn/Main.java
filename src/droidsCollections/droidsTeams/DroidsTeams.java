package droidsCollections.droidsTeams;

import droid.Droid;

import java.util.ArrayList;
public class DroidsTeams {
    private String TeamName;
    private Integer maxSize = 4;
    /*private Integer maxSize2 = 2;
    private Integer maxSize3 = 1;*/
    private ArrayList<Droid> team = new ArrayList<>(maxSize);
    /*private ArrayList<Droid> team2 = new ArrayList<>(maxSize2);
    private ArrayList<Droid> team3 = new ArrayList<>(maxSize3);*/

    private static ArrayList<DroidsTeams> allTeams = new ArrayList<>();


    //region -==- CTORs -==-


    public DroidsTeams() {}

    public DroidsTeams(String teamName, ArrayList droids) {
        addDroidToTeam(teamName, droids);
        setAllTeams(this);
    }

    public DroidsTeams(String teamName, Droid... droids) {
        addDroidToTeam(teamName, droids);
        setAllTeams(this);
    }


    //endregion

    //region -==- PROPs -==-

    public void addDroidToTeam(String teamName, Droid... droids) {
        /*if (teamNumber == 1){
            if (team1.size() >= maxSize1){
                team1.remove(0);
            }
            team1.add(droid);
        } else if (teamNumber == 2) {
            if (team2.size() >= maxSize2){
                team2.remove(0);
            }
            team2.add(droid);
        }else{
            if (team3.size() >= maxSize3){
                team3.remove(0);
            }
            team3.add(droid);
        }*/

        if (team.size() >= maxSize){
            team.remove(0);
        }
        for (Droid droid : droids) {
            team.add(droid);
        }

        setTeamName(teamName);

    }

    public void addDroidToTeam(String teamName, ArrayList<Droid> droids) {

        if (team.size() >= maxSize){
            team.remove(0);
        }

        for (int i = 0; i < droids.size(); i++) {
            team.add(droids.get(i));
        }

        setTeamName(teamName);

    }
    public ArrayList<Droid> getTeam() {
        return team;
    }

    public String getTeamName() {
        return TeamName;
    }

    public DroidsTeams setTeamName(String teamName) {
        this.TeamName = teamName;
        return this;
    }

    /*public ArrayList<Droid> getTeam2() {
        return team2;
    }

    public ArrayList<Droid> getTeam3() {
        return team3;
    }*/

    public static ArrayList<DroidsTeams> getAllTeams() {
        return allTeams;
    }

    public static void setAllTeams(DroidsTeams allTeams) {
        DroidsTeams.allTeams.add(allTeams);
    }


    //endregion

    public boolean isDefeated() {
        for (Droid droid : getTeam()) {
            if (droid.getHP() > 0) {
                return false; // At least one droid is still alive
            }
        }
        return true; // All droids in the team are defeated
    }

    @Override
    public String toString() {
        return "DroidsTeams{" +
                "TeamName='" + TeamName + '\'' +
                ", team=" + team +
                '}'+ '\n';
    }
}
