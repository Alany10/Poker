package cardutils;

import cardutils.Card;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;
    private static final ArrayList<Card> protoDeck = new ArrayList<>();

    static {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                protoDeck.add(new Card(suit, rank));
            }
        }
    }

    public Deck() {
        cards = new ArrayList<>();
        fill(); // Fill the deck with prototype cards
    }

    public int getSize() {
        return cards.size();
    }

    public void fill() {
        cards.clear();
        cards.addAll(protoDeck);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No cards left to deal");
        }
        return cards.remove(cards.size() - 1);
    }

    public void shuffleCards() {
        if (!cards.isEmpty()) {
            Collections.shuffle(cards);
        } else {
            throw new IllegalStateException("No cards to shuffle");
        }
    }

    public ArrayList<Card> getCards(){
        return new ArrayList<>(this.cards);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("cardutils.Deck [");
        for (int i = 0; i < cards.size(); i++) {
            sb.append(cards.get(i).toString()); // Call toString() on each cardutils.Card object
            if (i < cards.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}