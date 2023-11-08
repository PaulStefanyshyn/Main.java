package battle;

import java.util.ArrayList;
import java.util.Random;

import classesOfDroid.hiller.Hiller;
import droidsCollections.droidsTeams.DroidsTeams;
public class Battle {
    private DroidsTeams currentTurn; // Keeps track of the current team's turn
    DroidsTeams team1, team2;

    ArrayList<Integer> team1StartHP = new ArrayList<>();
    ArrayList<Integer> team2StartHP = new ArrayList<>();
    ArrayList<String> battleHistory = new ArrayList<>();

    Integer activeDroid = 0;
    Integer teamSize;

    public Battle() {
        setTeam1(DroidsTeams.getAllTeams().get(0));
        setTeam2(DroidsTeams.getAllTeams().get(1));
        // Initialize the current turn to one of the teams (e.g., team1)
        currentTurn = team1;
    }

    public Battle(String team1Name, String team2Name) {
        int teamsListLength = DroidsTeams.getAllTeams().size();
        DroidsTeams team1 = DroidsTeams.getAllTeams().get(0);
        DroidsTeams team2 = DroidsTeams.getAllTeams().get(1);

        for (int i = 0; i < teamsListLength; i++){

            if (team1Name == DroidsTeams.getAllTeams().get(i).getTeamName()) {
                setTeam1(DroidsTeams.getAllTeams().get(i));
                team1 = DroidsTeams.getAllTeams().get(i);
            } else if (team2Name == DroidsTeams.getAllTeams().get(i).getTeamName()) {
                setTeam2(DroidsTeams.getAllTeams().get(i));
                team2 = DroidsTeams.getAllTeams().get(i);
            }
        }

        currentTurn = team1;
        InitializedTeams(team1, team2);

        System.out.println(getTeam1());
        System.out.println(getTeam2());

    }

    //region -==- PROPs -==-


    public ArrayList<String> getBattleHistory() {
        return battleHistory;
    }

    public DroidsTeams getTeam1() {
        return team1;
    }

    public Battle setTeam1(DroidsTeams team1) {
        this.team1 = team1;

        for (int i = 0; i < team1.getTeam().size(); i++){
            team1StartHP.add(team1.getTeam().get(i).getHP());
        }

        return this;
    }

    public DroidsTeams getTeam2() {
        return team2;
    }

    public Battle setTeam2(DroidsTeams team2) {
        this.team2 = team2;

        for (int i = 0; i < team2.getTeam().size(); i++){
            team2StartHP.add(team2.getTeam().get(i).getHP());
        }

        return this;
    }

    public ArrayList<Integer> getTeam1StartHP() {
        return team1StartHP;
    }

    public ArrayList<Integer> getTeam2StartHP() {
        return team2StartHP;
    }

    public DroidsTeams getCurrentTurn() {
        return currentTurn;
    }

    //endregion
    /*TODO create second constructor with entered team1 and team2
    *  may input only team name and found teams by this name*/
    private void InitializedTeams(DroidsTeams team1, DroidsTeams team2){
        DroidsTeams teams = new DroidsTeams();

        Integer minTeamsSize = Math.min(team1.getTeam().size(), team2.getTeam().size());

        if (team1.getTeam().size() > minTeamsSize){
            ChangeLIstSize(team1, minTeamsSize);
        }else {
            ChangeLIstSize(team2, minTeamsSize);
        }
        teamSize = minTeamsSize;

        /*battleHistory.add(team1.toString());
        battleHistory.add(team2.toString());*/

        String teamDeclaring = team1.getTeamName() + " & " + team2.getTeamName();
        System.out.println(teamDeclaring);
        battleHistory.add(teamDeclaring);
    }

    private void ChangeLIstSize(DroidsTeams team, int newSize){
        while (team.getTeam().size() > newSize){
            team.getTeam().remove(team.getTeam().size() - 1);
        }
    }
    public void switchTurn(int changer) {

        String teamTurn = "It's now " + currentTurn.getTeamName() + "'s turn.";
        System.out.println(teamTurn);

        battleHistory.add(teamTurn);
        //System.out.println("Active droid "+activeDroid);

        if (currentTurn == team1) {
            Attack(getTeam1(), getTeam2());
            currentTurn = team2; // Switch to team2's turn
        } else {
            Attack(getTeam2(), getTeam1());
            currentTurn = team1; // Switch back to team1's turn
        }
        //System.out.println("changer: " + changer);
        if (changer == 1){
            switchActiveDroid();
        }

    }

    private void switchActiveDroid(){
        if (activeDroid >= teamSize - 1){
            activeDroid = 0;
        }else {
            activeDroid++;
        }
    }

    public void Attack(DroidsTeams ally, DroidsTeams enemy){
        int allyTeamLength = ally.getTeam().size();

        //Random random = new Random();
        int activeAllyDroidPosition = activeDroid/*random.nextInt(allyTeamLength)*/;
        System.out.println(activeAllyDroidPosition);

        DroidLogic(ally, enemy, activeAllyDroidPosition, allyTeamLength);
    }

    private void DroidLogic(DroidsTeams ally, DroidsTeams enemy, int activePosition, int teamLength){
        String droidDoAction = "droid " + ally.getTeam().get(activePosition).getName()
                + " from team " + ally.getTeamName() + " do action";
        System.out.println(droidDoAction);

        battleHistory.add(droidDoAction);

        if (ally.getTeam().get(activePosition).getType() == "hiller"){
            HillerBattleLogic(ally, activePosition, teamLength);
        } else if (ally.getTeam().get(activePosition).getType() == "defender") {
            DefenderLogic(ally, enemy, activePosition);
        } else if (ally.getTeam().get(activePosition).getType() == "fighter") {
            FighterLogic(ally, enemy, activePosition);
        } else if (ally.getTeam().get(activePosition).getType() == "shooter") {
            ShooterLogic(ally, enemy, activePosition);
        }
        //System.out.println(ally.getTeam().get(activePosition).getType());
    }

    private void HillerBattleLogic(DroidsTeams ally, int myPosition, int teamLength){
        int currentHP = 0, startHP = 0;
        int leesHPPosition = 0;
        double percentOfHP;
        double lessPercentOfHP = 100;

        //ally.getTeam().get(1).setHP(ally.getTeam().get(1).getHP()-1);


        for (int i = 0; i < teamLength; i++){
            if (i != myPosition){
                currentHP = ally.getTeam().get(i).getHP();

                if (ally.getTeamName() == getTeam1().getTeamName()){
                    startHP = getTeam1StartHP().get(i);
                }else {
                    startHP = getTeam2StartHP().get(i);
                }
                //System.out.println("startHP " + startHP);

                percentOfHP = (currentHP * 100) / startHP;
                //System.out.println("percentOfHP " + percentOfHP);

                if (percentOfHP < lessPercentOfHP){
                    lessPercentOfHP = percentOfHP;
                    leesHPPosition = i;
                }
            }
        }
        String droidAction;

        if (currentHP != 0){
            if (lessPercentOfHP < 100){
                Hiller hiller = (Hiller)ally.getTeam().get(myPosition);
                int hill = hiller.getRescueHP();

                int newHP = currentHP + hill;
                if(newHP >= startHP){
                    newHP = startHP;
                }
            /*System.out.println(hill);
            System.out.println(lessPercentOfHP);
            System.out.println(newHP);*/
                ally.getTeam().get(leesHPPosition).setHP(newHP);

                droidAction = ally.getTeam().get(leesHPPosition).getName()
                        + " was hilled" + '\n' + "Now his hp is " +
                        ally.getTeam().get(leesHPPosition).getHP();
                System.out.println(droidAction);
                battleHistory.add(droidAction);
                //System.out.println(ally.getTeam().get(leesHPPosition).getHP());
            }else {
                droidAction = "noone was hilled";
                System.out.println(droidAction);
                battleHistory.add(droidAction);
            }
        }else {
            droidAction = "No alive ally not found";
            System.out.println(droidAction);
            battleHistory.add(droidAction);
        }


    }

    private void DefenderLogic(DroidsTeams ally, DroidsTeams enemy, int myPosition){
        //System.out.println(enemy.getTeam());
        int enemyPosition = FoundEnemyPosition(enemy);

        int enemyHP = enemy.getTeam().get(enemyPosition).getHP();
        //System.out.println(enemyHP);
        int defenderDamage = ally.getTeam().get(myPosition).getDamage();
        //System.out.println(defenderDamage);
        int newEnemyHP = enemyHP - defenderDamage;
        //System.out.println(newEnemyHP);
        enemy.getTeam().get(enemyPosition).setHP(newEnemyHP);

        String droidAction = enemy.getTeam().get(enemyPosition).getName()
                + " was attacked by " + ally.getTeam().get(myPosition).getName()
                + '\n' + "Now his hp is " +
                enemy.getTeam().get(enemyPosition).getHP();
        System.out.println(droidAction);
        battleHistory.add(droidAction);
    }

    private Integer FoundEnemyPosition(DroidsTeams enemy){
        int enemyPosition = 0;
        for (int i = 0; i < enemy.getTeam().size(); i++){
            int enemeHP = enemy.getTeam().get(i).getHP();
            if (enemeHP != 0){
                enemyPosition = i;
                break;
            }
        }
        return enemyPosition;
    }

    private void FighterLogic(DroidsTeams ally, DroidsTeams enemy, int myPosition){
        int enemyPosition = FoundEnemyPosition(enemy);

        int enemyHP = enemy.getTeam().get(enemyPosition).getHP();
        //System.out.println(enemyHP);
        int fighterDamage = ally.getTeam().get(myPosition).getDamage();
        //System.out.println(defenderDamage);
        int newEnemyHP = enemyHP - fighterDamage;
        //System.out.println(newEnemyHP);
        enemy.getTeam().get(enemyPosition).setHP(newEnemyHP);

        String droidAction = enemy.getTeam().get(enemyPosition).getName()
                + " was attacked by " + ally.getTeam().get(myPosition).getName()
                + '\n' + "Now his hp is " +
                enemy.getTeam().get(enemyPosition).getHP();
        System.out.println(droidAction);
        battleHistory.add(droidAction);
    }

    private void ShooterLogic(DroidsTeams ally, DroidsTeams enemy, int myPosition){
        int enemyPosition = FoundEnemyPosition(enemy);

        int enemyHP = enemy.getTeam().get(enemyPosition).getHP();
        //System.out.println(enemyHP);
        int shooterDamage = ally.getTeam().get(myPosition).getDamage();
        //System.out.println(defenderDamage);
        int newEnemyHP = enemyHP - shooterDamage;
        //System.out.println(newEnemyHP);
        enemy.getTeam().get(enemyPosition).setHP(newEnemyHP);

        String droidAction = enemy.getTeam().get(enemyPosition).getName()
                + " was attacked by " + ally.getTeam().get(myPosition).getName()
                + '\n' + "Now his hp is " +
                enemy.getTeam().get(enemyPosition).getHP();
        System.out.println(droidAction);
        battleHistory.add(droidAction);
    }
}
