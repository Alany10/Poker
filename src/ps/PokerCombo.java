package ps;

public enum PokerCombo {
        NONE(0), PAIR(1), TWO_PAIRS(2), THREE_OF_A_KIND(3), FULL_HOUSE(5), STRAIGHT(6), FLUSH(7), FOUR_OF_A_KIND(8), STRAIGHT_FLUSH(15);

        private final int value;

        PokerCombo(int value) {
                this.value = value;
        }

        public int getValue() {
                return value;
        }
}


