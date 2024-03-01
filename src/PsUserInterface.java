import cardutils.Card;
import ps.PsLogic;

import java.util.Scanner;

public class PsUserInterface {

    private PsLogic psLogic;
    private Scanner scanner;

    PsUserInterface(){
        psLogic = new PsLogic();
        scanner = new Scanner(System.in);
    }

    public void startGame(){
        char choice = 'S';
        String input;

        while (choice != 'X'){
            printMenu();
            input = scanner.nextLine();
            input = input.toUpperCase();
            choice = input.charAt(0);

            if(choice == 'S'){
                runGame();
            }
            else if(choice == 'R'){
                printRules();
            }
            else if(choice == 'X'){
                System.out.println("Exiting...");
                scanner.close();
                break;
            }
            else System.out.println("\u001B[31mWrong input\u001B[0m");

        }
    }

    public void runGame(){
        System.out.println("Starting new Game...");
        psLogic.initNewGame();
        System.out.println("Ready!");

        while (!psLogic.isGameOver()){
            Card nextCard = psLogic.pickNextCard();
            pickACard(nextCard);
        }

        psLogic.getPoints();
        System.out.println(psLogic.toStringWithPoints());

        System.out.println("Game over!");
        System.out.print("You got " + psLogic.getPoints() + "points!, ");
        if (psLogic.getPoints() < 15){
            System.out.println("Better luck next time...");
        }
        else if(psLogic.getPoints() < 30 && psLogic.getPoints() >= 15){
            System.out.println("Well played!");
        }
        else System.out.println("You were awesome!");
    }

    public void pickACard(Card nextCard) {
        System.out.println(psLogic.toString());
        System.out.println("Card given: " + nextCard);
        System.out.print("Add card to pile (1-5): ");

        int pileNr;
        while (true) {
            try {
                pileNr = Integer.parseInt(scanner.nextLine());
                if (pileNr >= 1 && pileNr <= 5) {
                    break;
                } else {
                    System.out.print("\u001B[31mInvalid input. Please enter a number between 1 and 5: \u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.print("\u001B[31mInvalid input. Please enter a number between 1 and 5: \u001B[0m");
            }
        }

        if(!psLogic.addCardToPile(pileNr)){
            System.out.println("\u001B[31mPile is full!\u001B[0m");
            pickACard(nextCard);
        }
    }

    public void printMenu(){
        System.out.println("---Menu---");
        System.out.println("Options:");
        System.out.println("S: Start new game");
        System.out.println("X: Exit");
        System.out.println("R: Rules");
        System.out.println("----------");
    }

    public void printRules(){
        System.out.println("---Rules---");
        System.out.println("Combinations from worst to best:");
        System.out.println(psLogic.combo(15) + ": When you have 5 cards that contains sequential rank, all of the same suit");
        System.out.println(psLogic.combo(8) + ": When you have 4 cards that have the same rank");
        System.out.println(psLogic.combo(7) + ": When you have 5 cards of the same suit");
        System.out.println(psLogic.combo(6) + ": When you have 5 cards that contains sequential rank, not same suit");
        System.out.println(psLogic.combo(5) + ": When you have 3 ranks that are the same and another 2 ranks that are the same");
        System.out.println(psLogic.combo(3) + ": When you have 3 cards with the same rank");
        System.out.println(psLogic.combo(2) + ": When you have 2 cards of the same rank and 2 other cards with another same rank");
        System.out.println(psLogic.combo(1) + ": When you have 2 cards with the same rank");
        System.out.println("----------");
    }

}
