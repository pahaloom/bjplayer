package ee.pahaloom.bjplayer;

/**
 * Card. Immutable.
 * @author Ploom
 */
public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(suit);
        b.append(rank);
        return b.toString();
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    static Card parse(String cardStr) {
        return new Card(Suit.parse(cardStr.substring(0, 1)), Rank.parse(cardStr.substring(1)));
    }

    public enum Suit {
        CLUBS {
            @Override
            public String toString() {
                return "C";
            }
        }, DIAMONDS {
            @Override
            public String toString() {
                return "D";
            }
        }, HEARTS {
            @Override
            public String toString() {
                return "H";
            }
        }, SPADES {
            @Override
            public String toString() {
                return "S";
            }
        };

        static Suit parse(String suitStr) {
            switch (suitStr) {
                case "C":
                    return CLUBS;
                case "D":
                    return DIAMONDS;
                case "H":
                    return HEARTS;
                case "S":
                    return SPADES;
            }
            throw new IllegalArgumentException("Invalid suit: " + suitStr);
        }
    }

    public enum Rank {
        TWO {
            @Override
            public String toString() {
                return "2";
            }
        }, THREE {
            @Override
            public String toString() {
                return "3";
            }
        }, FOUR {
            @Override
            public String toString() {
                return "4";
            }
        }, FIVE {
            @Override
            public String toString() {
                return "5";
            }
        }, SIX {
            @Override
            public String toString() {
                return "6";
            }
        }, SEVEN {
            @Override
            public String toString() {
                return "7";
            }
        }, EIGHT {
            @Override
            public String toString() {
                return "8";
            }
        }, NINE {
            @Override
            public String toString() {
                return "9";
            }
        }, TEN {
            @Override
            public String toString() {
                return "10";
            }
        }, JACK {
            @Override
            public String toString() {
                return "J";
            }
        }, QUEEN {
            @Override
            public String toString() {
                return "Q";
            }
        }, KING {
            @Override
            public String toString() {
                return "K";
            }
        }, ACE {
            @Override
            public String toString() {
                return "A";
            }
        };

        static Rank parse(String rankStr) {
            switch (rankStr) {
                case "2":
                    return TWO;
                case "3":
                    return THREE;
                case "4":
                    return FOUR;
                case "5":
                    return FIVE;
                case "6":
                    return SIX;
                case "7":
                    return SEVEN;
                case "8":
                    return EIGHT;
                case "9":
                    return NINE;
                case "10":
                    return TEN;
                case "J":
                    return JACK;
                case "Q":
                    return QUEEN;
                case "K":
                    return KING;
                case "A":
                    return ACE;
            }
            throw new IllegalArgumentException("Invalid rank: " + rankStr);
        }
    }
}
