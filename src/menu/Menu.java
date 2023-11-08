package menu;

import classesOfDroid.defender.Defender;
import classesOfDroid.fighter.Fighter;
import classesOfDroid.hiller.Hiller;
import classesOfDroid.shooter.Shooter;

import droid.Droid;
import droidsCollections.createdDroids.DroidCollection;
import droidsCollections.droidsTeams.DroidsTeams;
import battle.Battle;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Menu {
    public Menu() {
        DroidCollection droidsList = new DroidCollection();

        CreateDroidMenu(droidsList);
        //CreateDroidTeamMenu(droidsList);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayHomeMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createDroidMenu(scanner, droidsList);
                    break;
                case 2:
                    displayCreatedDroids(droidsList);
                    break;
                case 3:
                    battleMenu(scanner, droidsList);
                    //BattleMenu();
                    break;
                case 4:
                    // Read the battle messages from the file (if not empty)
                    ArrayList<String> readMessages = readMessagesFromFile("battle_messages.txt");

                    if (!readMessages.isEmpty()) {
                        System.out.println("Read messages from file:");
                        for (String message : readMessages) {
                            System.out.println(message);
                        }
                    } else {
                        System.out.println("No messages in the file.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }


    }

    private void displayHomeMenu() {
        System.out.println("Home Menu:");
        System.out.println("1. Create Droid");
        System.out.println("2. Look at Created Droids");
        System.out.println("3. Battle");
        System.out.println("4. Battle history");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    public void createDroidMenu(Scanner scanner, DroidCollection droidsList) {
        System.out.println("Create Menu:");
        System.out.println("1. Hiller");
        System.out.println("2. Fighter");
        System.out.println("3. Defender");
        System.out.println("4. Shooter");
        System.out.println("5. Back to Home Menu");
        System.out.print("Enter your choice: ");
        while (true) {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createHiller(scanner, droidsList);
                    break;
                case 2:
                    createFighter(scanner, droidsList);
                    break;
                case 3:
                    createDefender(scanner, droidsList);
                    break;
                case 4:
                    createShooter(scanner, droidsList);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private void createHiller(Scanner scanner, DroidCollection droidsList){
        System.out.println("Create Hiller:");

        System.out.print("Enter name of droid: ");
        String name = scanner.next();

        System.out.print("Enter hp of droid (10 - 20): ");
        int hp = scanner.nextInt();

        System.out.print("Enter rescue power (5 - 20): ");
        int rescueHP = scanner.nextInt();

        Hiller droid = new Hiller(name, hp, 0, rescueHP);

        droidsList.addDroidToCollection(droid);

        System.out.println("Back to Home Menu pressed 5");

    }

    private void createFighter(Scanner scanner, DroidCollection droidsList){
        System.out.println("Create Fighter:");

        System.out.print("Enter name of droid: ");
        String name = scanner.next();

        System.out.print("Enter hp of droid (20 - 60): ");
        int hp = scanner.nextInt();

        System.out.print("Enter damage of droid (10 - 45): ");
        int damage = scanner.nextInt();

        Fighter droid = new Fighter(name, hp, damage);

        droidsList.addDroidToCollection(droid);

        System.out.println("Back to Home Menu pressed 5");
    }

    private void createDefender(Scanner scanner, DroidCollection droidsList){
        System.out.println("Create Defender:");

        System.out.print("Enter name of droid: ");
        String name = scanner.next();

        System.out.print("Enter hp of droid (20 - 200): ");
        int hp = scanner.nextInt();

        System.out.print("Enter damage of droid (1 - 10): ");
        int damage = scanner.nextInt();

        Defender droid = new Defender(name, hp, damage);

        droidsList.addDroidToCollection(droid);

        System.out.println("Back to Home Menu pressed 5");
    }

    private void createShooter(Scanner scanner, DroidCollection droidsList){
        System.out.println("Create Shooter:");

        System.out.print("Enter name of droid: ");
        String name = scanner.next();

        System.out.print("Enter hp of droid (20 - 50): ");
        int hp = scanner.nextInt();

        System.out.print("Enter damage of droid (10 - 40): ");
        int damage = scanner.nextInt();

        Shooter droid = new Shooter(name, hp, damage);

        droidsList.addDroidToCollection(droid);

        System.out.println("Back to Home Menu pressed 5");
    }
    public void displayCreatedDroids(DroidCollection droidsList) {
        // Display the list of created droids
        System.out.println("List of Created Droids:");

        ArrayList<Droid> allDroidsList = new ArrayList<>();
        combineAllListInOne(droidsList, allDroidsList);

        for (int i = 0; i < allDroidsList.size(); i++) {
            System.out.println((i + 1) + ") " + allDroidsList.get(i));
        }
    }

    public void battleMenu(Scanner scanner, DroidCollection droidsList) {
        System.out.println("Battle Menu:");
        System.out.println("1. 1 vs 1");
        System.out.println("2. 2 vs 2");
        System.out.println("3. 4 vs 4");
        System.out.println("4. Back to Home Menu");
        System.out.print("Enter your choice: ");
        while (true) {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    CreateBattleTeams(droidsList, 1, scanner);
                    BattleMenu(1);
                    break;
                case 2:
                    CreateBattleTeams(droidsList, 2, scanner);
                    BattleMenu(2);
                    break;
                case 3:
                    CreateBattleTeams(droidsList, 4, scanner);
                    BattleMenu(4);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //TODO create the class to safe all created droid
    private void CreateDroidMenu(DroidCollection droidsList) {

        // Hiller hillDroid = new Hiller();
        Hiller droidh1 = new Hiller("hiller1", 10, 60, 1);
        /*Hiller droidh2 = new Hiller("hiller2", 10, 60, 1);
        Hiller droidh3 = new Hiller("hiller3", 10, 60, 1);
*/
        droidsList.addDroidToCollection(droidh1);
        /*droidsList.addDroidToCollection(droidh2);
        droidsList.addDroidToCollection(droidh3);*/

        //Fighter fightDroid = new Fighter();
        Fighter droid2 = new Fighter("fighterr2d2", 50, 40);

        droidsList.addDroidToCollection(droid2);

        //Defender defendDroid = new Defender();
        Defender droid3 = new Defender("defenderp3o0", 100, 10);
        droidsList.addDroidToCollection(droid3);

        Shooter droid4 = new Shooter("shootert2d2", 40, 40);
        droidsList.addDroidToCollection(droid4);

        /*Droid droid2 = new Droid.Builder()
                .setName("")
                .setHealth(90)
                .setType("heller")
                .build();*/

        /*System.out.println(droid1.toString());
        System.out.println(droid2.toString());
        System.out.println(droid3.toString());
        System.out.println(droid4.toString());
        System.out.println();
        System.out.println(droidsList.getHillerList());
        System.out.println(droidsList.getFighterList());
        System.out.println(droidsList.getDefenderList());
        System.out.println(droidsList.getShooterList());*/

    }

    private void CreateBattleTeams(DroidCollection droidsList, int teamSize, Scanner scanner){

        ArrayList<Droid> allDroidsList = new ArrayList<>();
        combineAllListInOne(droidsList, allDroidsList);

        displayCreatedDroids(droidsList);

        ArrayList<Droid> team1 = new ArrayList<>();
        ArrayList<Droid> team2 = new ArrayList<>();

        team1 = generateBattleTeams(allDroidsList, team1, team2, teamSize, scanner);
        System.out.print("Enter name of first team: ");
        String team1Name = scanner.next();

        team2 = generateBattleTeams(allDroidsList, team2, team1, teamSize, scanner);
        System.out.print("Enter name of second team: ");
        String team2Name = scanner.next();

        ArrayList<DroidsTeams> teams = new ArrayList<>();

        teams.add(new DroidsTeams(team1Name, team1));
        teams.add(new DroidsTeams(team2Name, team2));
    }

    private ArrayList<Droid> generateBattleTeams(ArrayList<Droid> allDroidsList, ArrayList<Droid> nowTeam, ArrayList<Droid> anotherTeam, int teamSize, Scanner scanner){
        System.out.println("Chose yours droids to first team");
        System.out.println(teamSize - 1);
        for (int i = 0; i < teamSize; i++){
            System.out.println("Chose position of droid from list above");
            int droidPosition = scanner.nextInt();
            if (droidPosition < 0){
                droidPosition = 0;
            }else if (droidPosition > allDroidsList.size()){
                droidPosition = allDroidsList.size() - 1;
            }

            if (isUnique(droidPosition, allDroidsList, nowTeam, anotherTeam)){
                nowTeam.add(allDroidsList.get(droidPosition-1));
            }else {
                System.out.println("this droid already is in team");
                System.out.println();
                nowTeam.clear();
                anotherTeam.clear();
                generateBattleTeams(allDroidsList, nowTeam,anotherTeam, teamSize, scanner);
            }
        }
        return nowTeam;
    }

    private boolean isUnique(int droidPosition, ArrayList<Droid> allDroidsList,  ArrayList<Droid> nowTeam, ArrayList<Droid> anotherTeam){
        for (int i = 0; i < nowTeam.size(); i++){
            if (allDroidsList.get(droidPosition - 1) == nowTeam.get(i)){
                return false;
            }
        }

        if (anotherTeam.size() > 0){
            for (int i = 0; i < anotherTeam.size(); i++) {
                if (allDroidsList.get(droidPosition - 1) == anotherTeam.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }
    private void CreateDroidTeamMenu(DroidCollection droidsList) {
        DroidsTeams droidTeams = new DroidsTeams();

        //int i = 0;

        ArrayList<Droid> allDroidsList = new ArrayList<>();
        combineAllListInOne(droidsList, allDroidsList);

        for (int i = 0; i < allDroidsList.size(); i++) {
            System.out.println((i + 1) + ") " + allDroidsList.get(i));
        }

        ArrayList<DroidsTeams> teams = new ArrayList<>();

        teams.add(new DroidsTeams("1", allDroidsList.get(0), allDroidsList.get(3)));
        teams.add(new DroidsTeams("2", allDroidsList.get(1), allDroidsList.get(2), allDroidsList.get(0)));
        teams.add(new DroidsTeams("3", allDroidsList.get(2), allDroidsList.get(2), allDroidsList.get(0), allDroidsList.get(0), allDroidsList.get(0)));


        /*droidTeams.addDroidToTeam(allDroidsList.get(0), "1");
        droidTeams.addDroidToTeam(allDroidsList.get(2), "1");

        droidTeams.addDroidToTeam(allDroidsList.get(2));
        droidTeams.addDroidToTeam(allDroidsList.get(1));
        droidTeams.addDroidToTeam(allDroidsList.get(0));

        droidTeams.addDroidToTeam(allDroidsList.get(0));
        droidTeams.addDroidToTeam(allDroidsList.get(2));*/

        //System.out.println("Teams:");

        /*System.out.println(teams.get(0).getTeam());
        System.out.println(teams.get(1).getTeam());
        System.out.println(teams.get(2).getTeam());
        System.out.println();*/

        /*System.out.println(droidTeams.getAllTeams());
        System.out.println(droidTeams.getAllTeams().get(0));*/

        /*System.out.println();
        int starthp = teams.get(0).getTeam().get(0).getHP();
        teams.get(0).getTeam().get(0).setHP(0);
        teams.get(0).getTeam().get(1).setHP(0);

        System.out.println(teams.get(0).isDefeated());
        System.out.println("1) "+teams.get(0).getTeam().get(0).getHP());
        System.out.println("2) "+teams.get(0).getTeam().get(1).getHP());*/

        /*System.out.println("Hillers: " + droidsList.getHillerListSize());
        for (int j = 0; j < droidsList.getHillerListSize(); j++){
            System.out.println(i + ") " + droidsList.getHillerList().get(j));
            i++;
        }

        System.out.println("Fighters: ");
        for (int j = 0; j < droidsList.getFighterListSize(); j++){
            System.out.println(i + ") " + droidsList.getFighterList().get(j));
            i++;
        }

        System.out.println("Defenders: ");
        for (int j = 0; j < droidsList.getDefenderListSize(); j++){
            System.out.println(i + ") " + droidsList.getDefenderList().get(j));
            i++;
        }

        System.out.println("Shooters: ");
        for (int j = 0; j < droidsList.getShooterListSize(); j++){
            System.out.println(i + ") " + droidsList.getShooterList().get(j));
            i++;
        }*/
    }

    public void addListDataToAnotherList(ArrayList<Droid> source, ArrayList<Droid> destination) {
        for (Droid item : source) {
            destination.add(item);
        }
    }

    public void combineAllListInOne(DroidCollection source, ArrayList<Droid> destination) {
        addListDataToAnotherList(source.getHillerList(), destination);
        addListDataToAnotherList(source.getFighterList(), destination);
        addListDataToAnotherList(source.getDefenderList(), destination);
        addListDataToAnotherList(source.getShooterList(), destination);
    }

    public void BattleMenu(int teamSize) {
        Battle battle = new Battle();
        int changer = 0;

        int i = 1;
        while (!battle.getTeam1().isDefeated() && !battle.getTeam2().isDefeated()) {
            System.out.println("i: " + i);
            battle.getBattleHistory().add("i: " + i);
            /*System.out.println("battle.getTeam1().isDefeated(): " + battle.getTeam1().isDefeated());
            System.out.println("battle.getTeam1(): " + battle.getTeam1());
            System.out.println("battle.getTeam2().isDefeated(): " + battle.getTeam2().isDefeated());
            System.out.println("battle.getTeam2(): " + battle.getTeam2());*/
            if (battle.getCurrentTurn().getTeam().get(changer).getHP() != 0){
                battle.switchTurn(changer);
                changer++;
            }else {
                changer++;
                battle.switchTurn(changer);
            }
            if (changer == teamSize){
                changer = 0;
            }
            //System.out.println("changer: " + changer);
            System.out.println();

            i++;
        }
        CongratsMessage(battle);

        ReturnStartHP(battle);

        /*System.out.println("Battle history:");
        for (int history = 0; history < battle.getBattleHistory().size(); history++){
            System.out.println(battle.getBattleHistory().get(history));
        }*/

        writeMessagesToFile(battle.getBattleHistory(), "battle_messages.txt");

        System.out.println("4. Back to Home Menu");

    }

    private static void writeMessagesToFile(ArrayList<String> messages, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String message : messages) {
                writer.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> readMessagesFromFile(String filename) {
        ArrayList<String> messages = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                messages.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }

    private void CongratsMessage(Battle battle){
        int xpNumber = 10;

        String victoryMessage;
        if (battle.getTeam1().isDefeated()){
            AddXP(battle.getTeam2(), xpNumber);
            victoryMessage = "team: " + battle.getTeam2().getTeamName() +
                    " victory!"+'\n'+
                    "team: "+battle.getTeam1().getTeamName() +
                    " defeated!";
            System.out.println(victoryMessage);
            battle.getBattleHistory().add(victoryMessage);
        } else if (battle.getTeam2().isDefeated()) {
            AddXP(battle.getTeam1(), xpNumber);
            victoryMessage = "team: " + battle.getTeam1().getTeamName() +
                    " victory!"+'\n'+
                    "team: "+battle.getTeam2().getTeamName() +
                    " defeated!";
            System.out.println(victoryMessage);
            battle.getBattleHistory().add(victoryMessage);
        }
    }

    private void AddXP(DroidsTeams team, int xpNumber){
        int teamLength = team.getTeam().size();
        for (int i = 0; i < teamLength; i++){
            team.getTeam().get(i).setExperience(xpNumber);
        }
    }

    private void ReturnStartHP(Battle team){
        int teamLength = team.getTeam1().getTeam().size();
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < teamLength; j++){
                if (i == 0){
                    int startHP = team.getTeam1StartHP().get(j);
                    team.getTeam1().getTeam().get(j).setHP(startHP);
                } else if (i == 1) {
                    int startHP = team.getTeam2StartHP().get(j);
                    team.getTeam2().getTeam().get(j).setHP(startHP);
                }

            }
        }
    }
}
