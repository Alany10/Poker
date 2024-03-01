package ps;

import cardutils.Card;
import cardutils.Deck;
import cardutils.Pile;

import java.util.ArrayList;

public class PsLogic implements IPsLogic {
    private Deck cards;
    private ArrayList<Pile> piles;
    private Card nextCard;
    private int[] pilePoints;


    public PsLogic() {
        cards = new Deck();
        piles = new ArrayList<>();
        nextCard = null; // We start with no next card.
        pilePoints = new int[5];
    }


    public void initNewGame(){
        cards.fill();
        cards.shuffleCards();
        piles.clear();
        for (int i = 0; i < 5; i++) {
            Pile pile = new Pile();  // Create a new Pile object
            piles.add(pile); // Add the new Pile to the ArrayList
        }
        nextCard = null;
    }

    public int getCardCount(){
        return (52 - cards.getSize());
    }

    private boolean isValidNextCard(Card nextCard){
        return nextCard != null;
    }
    private boolean isValidPile(Pile pile){return (pile.getSize() >= 0 && pile.getSize() < 5); }

    public Card pickNextCard(){
        if(!isValidNextCard(nextCard)){
            nextCard = cards.dealCard();
            return nextCard;
        }
        else {
            throw new IllegalStateException("Two cards in pickNextCard");
        }
    }

    public boolean addCardToPile(int pileNr) {
        try {
            if (isValidNextCard(nextCard)) {
                Pile pile = null;
                switch (pileNr) {
                    case 1:
                        pile = piles.get(0);
                        break;
                    case 2:
                        pile = piles.get(1);
                        break;
                    case 3:
                        pile = piles.get(2);
                        break;
                    case 4:
                        pile = piles.get(3);
                        break;
                    case 5:
                        pile = piles.get(4);
                        break;
                }

                if (isValidPile(pile)) {
                    pile.add(nextCard);
                    nextCard = null;
                    return true;
                } else {
                    throw new IllegalStateException("Invalid pile state");
                }
            } else {
                return false;
            }
        } catch (IllegalStateException e) {
            // Catch the IllegalStateException and return false
            return false;
        }
    }



    public ArrayList<Pile> getPiles() {
        return new ArrayList<>(piles);
    }

    public boolean isGameOver(){
        if(cards.getSize() == 27){
            return true;
        }
        else if (cards.getSize() > 27){
            return false;
        }
        else throw new IllegalStateException("Wrong amount of cards in IsGameOver");
    }

    public int getPoints() {
        int points = 0;
        int index = 0;

        for (Pile pile : piles) {
            PokerCombo combo = PokerHands.getPokerCombo(pile.getCards());
            int pointsToAdd = 0;

            switch (combo) {
                case STRAIGHT_FLUSH:
                    pointsToAdd = 15;
                    break;
                case FOUR_OF_A_KIND:
                    pointsToAdd = 8;
                    break;
                case FLUSH:
                    pointsToAdd = 7;
                    break;
                case STRAIGHT:
                    pointsToAdd = 6;
                    break;
                case FULL_HOUSE:
                    pointsToAdd = 5;
                    break;
                case THREE_OF_A_KIND:
                    pointsToAdd = 3;
                    break;
                case TWO_PAIRS:
                    pointsToAdd = 2;
                    break;
                case PAIR:
                    pointsToAdd = 1;
                    break;
                default:
                    pointsToAdd = 0;
                    break;
            }
            points += pointsToAdd;
            pilePoints[index] = pointsToAdd;
            index++;
        }
        return points;
    }

    public PokerCombo combo(int points){
        switch (points){
            case 15: return PokerCombo.STRAIGHT_FLUSH;
                case 8: return PokerCombo.FOUR_OF_A_KIND;
            case 7: return PokerCombo.FLUSH;
                case 6: return PokerCombo.STRAIGHT;
            case 5: return PokerCombo.FULL_HOUSE;
                case 3: return PokerCombo.THREE_OF_A_KIND;
            case 2: return PokerCombo.TWO_PAIRS;
                case 1: return PokerCombo.PAIR;
            default: return PokerCombo.NONE;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < piles.size(); i++) {
            stringBuilder.append("\n")
                    .append(i + 1)
                    .append(": ")
                    .append(piles.get(i).toString());
        }

        return stringBuilder.toString();
    }

    public String toStringWithPoints() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < piles.size(); i++) {
            stringBuilder.append(String.format("\n%d: %s, points: %d, %s", i + 1, piles.get(i).toString(), pilePoints[i], formatString(combo(pilePoints[i]).toString())));
        }

        return stringBuilder.toString();
    }

    private String formatString(String input) {
        String[] parts = input.toLowerCase().split("_");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1));
        }
        return result.toString();
    }
}
