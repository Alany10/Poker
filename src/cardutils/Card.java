package cardutils;

public class Card {

    private Rank rank;
    private Suit suit;
    public enum Rank{
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
    }
    public enum Suit{
        HEARTS, DIAMONDS, CLUBS, SPADES;
    }

    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public Rank getRank(){
        return this.rank;
    }

    public Suit getSuit(){
        return this.suit;
    }

    public int getRankValue(){
        return (this.rank.ordinal() + 1);
    }

    public int getSuitValue(){
        return (this.suit.ordinal() + 1);
    }

    @Override
    public String toString() {
        return toShortString();
    }

    private String toShortString() {
        String info = "";

        switch (rank) {
            case ACE:
                info += "\u001B[30;47mA\u001B[0m"; // Black text on white background for ACE
                break;
            case KING:
                info += "\u001B[30;47mK\u001B[0m"; // Black text on white background for KING
                break;
            case QUEEN:
                info += "\u001B[30;47mQ\u001B[0m"; // Black text on white background for QUEEN
                break;
            case JACK:
                info += "\u001B[30;47mJ\u001B[0m"; // Black text on white background for JACK
                break;
            default:
                info += "\u001B[30;47m" + getRankValue() + "\u001B[0m"; // Black text on white background for other ranks
        }

        switch (suit) {
            case SPADES:
                info += "\u001B[30;47m\u2660\u001B[0m"; // Black text on white background for SPADES
                break;
            case HEARTS:
                info += "\u001B[31;47m\u2764\u001B[0m"; // Red text on white background for HEARTS
                break;
            case CLUBS:
                info += "\u001B[30;47m\u2663\u001B[0m"; // Black text on white background for CLUBS
                break;
            case DIAMONDS:
                info += "\u001B[31;47m\u2666\u001B[0m"; // Red text on white background for DIAMONDS
                break;
            default:
                break;
        }

        return info;
    }



}
