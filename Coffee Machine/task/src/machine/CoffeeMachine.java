package machine;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class CoffeeMachine {

    private static int water = 400;
    private static int milk = 540;
    private static int coffee = 120;
    private static int cups = 9;
    private static int money = 550;
    private static MachineState state = MachineState.READY;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (state != MachineState.OFF) {

            System.out.print(state.getMessage());
            doSomeWork(scanner.nextLine());

        }

    }

    private static void doSomeWork(String action) {

        switch (state) {
            case READY:
                doReadyWork(action);
                break;
            case CHOOSE_COFFEE:
                buy(action);
                break;
            case ADD_WATER:
                addWater(action);
                break;
            case ADD_MILK:
                addMilk(action);
                break;
            case ADD_COFFEE:
                addCoffee(action);
                break;
            case ADD_CUPS:
                addCups(action);
                break;
            default:
                System.out.println("ERROR!");
                state = MachineState.READY;
                break;
        }
    }

    private static void doReadyWork(String action) {
        switch (action) {
            case "buy":
                state = MachineState.CHOOSE_COFFEE;
                break;
            case "fill":
                state = MachineState.ADD_WATER;
//                System.out.println();
//                fill(scanner);
//                System.out.println();
                break;
                // следующие двое ни на что не влияют
            case "take":
                take();
                break;
            case "remaining":
                printState();
                break;
            case "exit":
                state = MachineState.OFF;
                return;
            default:
                System.out.println("ERROR!");
                break;
        }
    }

    private static void fill(Scanner scanner) {

        System.out.println("Write how many ml of water do you want to add:");
        addWater(scanner);
        System.out.println("Write how many ml of milk do you want to add:");
        addMilk(scanner);
        System.out.println("Write how many grams of coffee beans do you want to add:");
        addCoffee(scanner);
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        addCups(scanner);

    }

    private static void addCups(Scanner scanner) {
        cups += Integer.parseInt(scanner.nextLine());
    }

    private static void addCups(String action) {
        cups += Integer.parseInt(action);
        state = MachineState.READY;
    }

    private static void addCoffee(Scanner scanner) {
        coffee += Integer.parseInt(scanner.nextLine());
    }

    private static void addCoffee(String action) {
        coffee += Integer.parseInt(action);
        state = MachineState.ADD_CUPS;
    }

    private static void addMilk(Scanner scanner) {
        milk += Integer.parseInt(scanner.nextLine());
    }

    private static void addMilk(String action) {
        milk += Integer.parseInt(action);
        state = MachineState.ADD_COFFEE;
    }

    private static void addWater(Scanner scanner) {
        water += Integer.parseInt(scanner.nextLine());
    }

    private static void addWater(String action) {
        water += Integer.parseInt(action);
        state = MachineState.ADD_MILK;
    }

    private static void take() {
        System.out.println();
        System.out.println("I gave you $" + money);
//        System.out.println();
        money = 0;
        state = MachineState.READY;
    }

    private static void buy(Scanner scanner) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");

        switch (scanner.nextLine()) {
            case "1":
                if (enoughForEspresso()) {
                    makeEspresso();
                }
                break;
            case "2" :
                if (enoughForLatte()) {
                    makeLatte();
                }
                break;
            case "3":
                if (enoughForCappuccino()) {
                    makeCappuccino();
                }
                break;
            default:
                System.out.println("ERROR!");
                break;
        }

    }

    private static void buy(String chosenCoffee) {

        switch (chosenCoffee) {
            case "1":
                if (enoughForEspresso()) {
                    makeEspresso();
                }
                break;
            case "2" :
                if (enoughForLatte()) {
                    makeLatte();
                }
                break;
            case "3":
                if (enoughForCappuccino()) {
                    makeCappuccino();
                }
                break;
            default:
                System.out.println("ERROR!");
                break;
        }

        state = MachineState.READY;

    }

    private static void makeCappuccino() {
        water -= 200;
        milk -= 100;
        coffee -= 12;
        cups -= 1;
        money += 6;
    }

    private static boolean enoughForCappuccino() {
        if (water - 200 < 0) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (milk - 100 < 0) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (coffee - 12 < 0) {
            System.out.println("Sorry, not enough coffee!");
            return false;
        }
        if (cups - 1 < 0) {
            System.out.println("Sorry, not enough cups!");
            return false;
        }
        System.out.println("I have enough resources, making you a coffee!");
        return true;
    }

    private static void makeLatte() {
        water -= 350;
        milk -= 75;
        coffee -= 20;
        cups -= 1;
        money += 7;
    }

    private static boolean enoughForLatte() {
        if (water - 350 < 0) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (milk - 75 < 0) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (coffee - 20 < 0) {
            System.out.println("Sorry, not enough coffee!");
            return false;
        }
        if (cups - 1 < 0) {
            System.out.println("Sorry, not enough cups!");
            return false;
        }
        System.out.println("I have enough resources, making you a coffee!");
        return true;
    }

    private static void makeEspresso() {
        water -= 250;
        coffee -= 16;
        cups -= 1;
        money += 4;
    }

    private static boolean enoughForEspresso() {
        if (water - 250 < 0) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (coffee - 16 < 0) {
            System.out.println("Sorry, not enough coffee!");
            return false;
        }
        if (cups - 1 < 0) {
            System.out.println("Sorry, not enough cups!");
            return false;
        }
        System.out.println("I have enough resources, making you a coffee!");
        return true;
        //I have enough resources, making you a coffee!
    }

    private static void printState() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffee + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
        state = MachineState.READY;
//        System.out.println();
    }

    private static void calculateAndPrint(int water, int milk, int coffee, int cups) {

//        System.out.println("Write how many ml of water the coffee machine has:");
//        int water = Integer.parseInt(scanner.nextLine());
//        System.out.println("Write how many ml of milk the coffee machine has:");
//        int milk = Integer.parseInt(scanner.nextLine());
//        System.out.println("Write how many grams of coffee beans the coffee machine has:");
//        int coffee = Integer.parseInt(scanner.nextLine());
//        System.out.println("Write how many cups of coffee you will need:");
//        int cups = Integer.parseInt(scanner.nextLine());

        int cupsFromWater = water / 200;
        int cupsFromMilk = milk / 50;
        int cupsFromCoffee = coffee / 15;

        int maxCupsFromThat = Collections.min(Arrays.asList(cupsFromCoffee, cupsFromMilk, cupsFromWater));

        if (maxCupsFromThat > cups) {
            System.out.println("Yes, I can make that amount of coffee (and even " + (maxCupsFromThat - cups) + " more than that)");
        } else if (maxCupsFromThat == cups) {
            System.out.println("Yes, I can make that amount of coffee");
        } else {
            System.out.println("No, I can make only " + maxCupsFromThat + " cup(s) of coffee");
        }

    }

    /**
     * contains 200 ml of water, 50 ml of milk, and 15 g of coffee beans.
     * @param numberOfCups число чашек
     */
    private static void printForQuantity(int numberOfCups) {
        System.out.println("For " + numberOfCups + " cups of coffee you will need:");
        System.out.println(numberOfCups * 200 + " ml of water");
        System.out.println(numberOfCups * 50 + " ml of milk");
        System.out.println(numberOfCups * 15 + " g of coffee beans");
    }

    public enum MachineState {
        OFF("Power Off"),
        READY("\nWrite action (buy, fill, take, remaining, exit): "),
        CHOOSE_COFFEE("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: "),
        ADD_WATER("\nWrite how many ml of water do you want to add: "),
        ADD_MILK("Write how many ml of milk do you want to add: "),
        ADD_COFFEE("Write how many grams of coffee beans do you want to add: "),
        ADD_CUPS("Write how many disposable cups of coffee do you want to add: ");

        String message;

        MachineState(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
