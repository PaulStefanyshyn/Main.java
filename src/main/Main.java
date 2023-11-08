package main;
import menu.Menu;

public class Main {
    public static void main(String[] args) {
        Menu droidMenu = new Menu();

    }

    private Integer MenuItem(String menuItem){
        int result = 0;

        try {
            result = Integer.parseInt(menuItem);
        } catch (NumberFormatException e){
            System.out.println(e);
        }

        return result;
    }

}