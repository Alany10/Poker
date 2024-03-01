package cardutils;

import java.util.ArrayList;
import java.util.Comparator;

public class Pile {

    private ArrayList<Card> cards;

    public Pile(){
        cards = new ArrayList<>();
        cards.clear();
    }

    public int getSize(){
        return cards.size();
    }

    public void clear(){
        cards.clear();
    }

    public void add(Card card){
        cards.add(card);
    }

    public void add(ArrayList<Card> cards){
        this.cards.addAll(cards);
    }

    public Card get(int index){
        if (isValid(index)){
            return cards.get(index);
        }
        else throw new IllegalArgumentException("Wrong index on card");
    }

    public ArrayList<Card> getCards(){
       return new ArrayList<>(this.cards);
    }

    public Card Remove(int indexToRemove){
        if(isValid(indexToRemove)){
            Card removenCard = cards.get(indexToRemove);
            cards.remove(indexToRemove);
            return removenCard;
        }
        else throw new IllegalArgumentException("Fel index på kortet");
    }

    public boolean remove(Card cardToRemove){
        for (int i = 0; i < cards.size(); i++){
            if (this.cards.get(i).equals(cardToRemove)){
                this.cards.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean contains(Card cardContains){
        for (int i = 0; i < cards.size(); i++){
            if (this.cards.get(i).equals(cardContains)){
                return true;
            }
        }
        return false;
    }

    public boolean remove(ArrayList<Card> cardsToRemove) {
        int removedCards = 0;

        for (int i = cards.size() - 1; i >= 0; i--) {
            if (cardsToRemove.contains(cards.get(i))) {
                cards.remove(i);
                removedCards++;
            }
        }

        return removedCards > 0;
    }

    public int nrOfSuit(Card.Suit suit){
        int foundSuits = 0;
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i).getSuit() == suit){
                foundSuits++;
            }
        }
        return foundSuits;
    }

    public int nrOfRank(Card.Rank rank){
        int foundRanks = 0;
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i).getRank() == rank){
                foundRanks++;
            }
        }
        return foundRanks;
    }

    public void sortByRank() {
        cards.sort(Comparator.comparing(Card::getRank));
    }

    private boolean isValid(int index){
        return this.cards.size() > index;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
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
