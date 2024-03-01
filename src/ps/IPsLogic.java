package ps;

import cardutils.*;

import java.util.List;

public interface IPsLogic {
    abstract public void initNewGame();
    abstract public int getCardCount();
    abstract public Card pickNextCard() throws IllegalStateException;
    abstract boolean addCardToPile(int pileNr);
    abstract public List<Pile> getPiles();
    abstract public boolean isGameOver();
    abstract public int getPoints();

}

