package com.pluralsight;

import com.pluralsight.data.ContractDataManager;
import com.pluralsight.model.Dealership;
import com.pluralsight.ui.UserInterface;

import java.util.*;
import java.math.*;

import static java.lang.Thread.sleep;

public class App {

    public static Scanner scanner = new Scanner(System.in);
    public static Dealership dealership;

    public static void main(String[] args) {
        ContractDataManager.readFromFile();
        UserInterface userInterface = new UserInterface();
        userInterface.displayMainMenu();
        ContractDataManager.writeToFile(true);
        System.out.print("thank you for using this program");
        scanner.close();
    }

    public static int promptToInt(String prompt) {
        do {
            try {
                System.out.print(prompt);
                return Integer.parseInt(App.scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("command is invalid\n");
                stallRunTime();
            }
        } while (true);
    }

    private static void stallRunTime() {
        try {
            sleep(900);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String promptToString(String prompt) {
        while (true) {
            System.out.print(prompt);
            boolean hasPipe = false;
            String enteredCommand=scanner.nextLine().trim();

            try {
                if (enteredCommand.isEmpty()) {
                    int integerOfCommand = Integer.parseInt(enteredCommand);
                    System.out.println(integerOfCommand);
                }
                String[] splitString = enteredCommand.split("");
                StringBuilder enteredCommandBuilder = new StringBuilder();

                for (String character : splitString) {
                    if (character.equals("|")) {character = "/"; hasPipe = true;}
                    enteredCommandBuilder.append(character);
                }
                enteredCommand = enteredCommandBuilder.toString();

                if (hasPipe) {
                    System.out.println("character(s) found to be in conflict with .csv format. '|' is overwritten with '/'");
                }
                return enteredCommand;
            } catch (NumberFormatException e) {
                System.out.println("Error invalid command! Please enter a valid command");
            }
        }
    }
    public static BigDecimal promptToBigDecimal(String prompt) {
        do {
            try {
                System.out.print(prompt);
                return new BigDecimal(App.scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("command is invalid\n");
                stallRunTime();
            }
        } while (true);
    }

    public static double getDecimal(BigDecimal bigDecimal) {
        return Double.parseDouble(String.valueOf(bigDecimal));}

    public static boolean canParseStringToInt(String prompt) {
        try {
            Integer.parseInt(prompt);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean canParseStringToDecimal(String prompt) {
        try {
            Double.parseDouble(prompt);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}