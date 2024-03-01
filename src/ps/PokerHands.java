package ps;

import cardutils.Card;
import cardutils.Pile;

import java.util.ArrayList;

public class PokerHands {

    private PokerHands(){}

    public static PokerCombo getPokerCombo(ArrayList<Card> cards) {
        if (hasStraightFlush(cards)){
            return PokerCombo.STRAIGHT_FLUSH;
        }
        else if (hasFourOfAKind(cards)) {
            return PokerCombo.FOUR_OF_A_KIND;
        }
        else if (hasFlush(cards)) {
            return PokerCombo.FLUSH;
        }
        else if (hasStraight(cards)){
            return PokerCombo.STRAIGHT;
        }
        else if (hasFullHouse(cards)){
            return PokerCombo.FULL_HOUSE;
        }
        else if (hasThreeOfAKind(cards)){
            return PokerCombo.THREE_OF_A_KIND;
        }
        else if (hasTwoPairs(cards)){
            return PokerCombo.TWO_PAIRS;
        }
        else if (hasPairs(cards)){
            return PokerCombo.PAIR;
        }
        else {
            return PokerCombo.NONE;
        }
    }

    private static boolean hasStraightFlush(ArrayList<Card> cards) {
        Pile pile = new Pile();
        pile.add(cards);

        if (pile.getSize() < 5) {
            return false;
        }

        pile.sortByRank(); // Sort by rank to easily detect a straight

        for (Card.Suit suit : Card.Suit.values()) {
            int count = 0;
            for (Card card : pile.getCards()) {
                if (card.getSuit() == suit) {
                    count++;
                }
            }
            if (count >= 5) {
                // Check if there's a straight within the suit
                for (int i = 0; i <= count - 4; i++) {
                    boolean isStraight = true;
                    for (int j = i; j < i + 4; j++) {
                        if (pile.get(j).getRank().ordinal() != pile.get(j + 1).getRank().ordinal() - 1) {
                            isStraight = false;
                            break;
                        }
                    }
                    if (isStraight) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    private static boolean hasFourOfAKind(ArrayList<Card> cards) {
        Pile pile = new Pile();
        pile.add(cards);

        for (Card.Rank rank: Card.Rank.values()){
            if (pile.nrOfRank(rank) == 4){
                return true;
            }
        }
        return false;
    }

    private static boolean hasFlush(ArrayList<Card> cards) {
        Pile pile = new Pile();
        pile.add(cards);

        if (pile.getSize() < 5) {
            throw new IllegalStateException("Cant have flush because you dont have 5 or more cards");
        }

        for (Card.Suit suit : Card.Suit.values()) {
            if (pile.nrOfSuit(suit) == 5) { // Check if there are 5 cards of the same suit
                return true;
            }
        }

        return false;
    }

    private static boolean hasStraight(ArrayList<Card> cards) {
        Pile pile = new Pile();
        pile.add(cards);
        boolean isStraight = true;
        if (pile.getSize() < 5) {
            throw new IllegalStateException("Can't have a straight because you don't have 5 or more cards");
        }

        // Sort the cards in the pile by rank
        pile.sortByRank();

        for (int i = 0; i < pile.getSize() - 4; i++) {
            for (int j = i; j < i + 4; j++) {
                if (pile.get(j).getRank().ordinal() + 1 != pile.get(j + 1).getRank().ordinal()) {
                    isStraight = false;
                }
            }
        }

        if (isStraight){
            return true;
        }
        else {
            if (pile.get(0).getRank() == Card.Rank.ACE &&
                pile.get(1).getRank() == Card.Rank.TEN &&
                pile.get(2).getRank() == Card.Rank.JACK &&
                pile.get(3).getRank() == Card.Rank.QUEEN &&
                pile.get(4).getRank() == Card.Rank.KING){
                return true;
            }
        }

        return false;
    }

    private static boolean hasFullHouse(ArrayList<Card> cards) {
        Pile pile = new Pile();
        pile.add(cards);

        boolean hasThree = false;
        boolean hasPair = false;

        for (Card.Rank rank : Card.Rank.values()) {
            int rankCount = pile.nrOfRank(rank);
            if (rankCount == 3) {
                hasThree = true;
            } else if (rankCount == 2) {
                hasPair = true;
            }
        }

        return hasThree && hasPair;
    }


    private static boolean hasThreeOfAKind(ArrayList<Card> cards) {
        Pile pile = new Pile();
        pile.add(cards);

        for (Card.Rank rank: Card.Rank.values()){
            if (pile.nrOfRank(rank) == 3){
                return true;
            }
        }
        return false;
    }
    private static boolean hasTwoPairs(ArrayList<Card> cards) {
        Pile pile = new Pile();
        pile.add(cards);

        int nrOfPairs = 0;

        for (Card.Rank rank : Card.Rank.values()) {
            int rankCount = pile.nrOfRank(rank);
            if (rankCount == 2) {
                nrOfPairs++; // Found a pair
            }
        }

        return nrOfPairs == 2;
    }
    private static boolean hasPairs(ArrayList<Card> cards) {
        Pile pile = new Pile();
        pile.add(cards);

        for (Card.Rank rank: Card.Rank.values()){
            if (pile.nrOfRank(rank) == 2){
                return true;
            }
        }

        return false;
    }

}
