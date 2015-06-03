package ee.pahaloom.bjplayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * BlackJack hand
 * 
 * @author Ploom
 */
public class Hand implements Iterable<Card> {
    private final List<Card> cards = new ArrayList<>();
    private boolean isStood = false;
    private boolean canDouble = true;
    private boolean isSplittable = true;

    public Hand() {}

    public Hand(Card[] initialCards) {
        for (Card c : initialCards) {
            addCard(c);
        }
    }

    public Hand(String[] initialCards) {
        for (String cardStr : initialCards) {
            addCard(Card.parse(cardStr));
        }
    }

    private void addCard(Card c) {
        cards.add(c);
    }

    public void add(Card c) {
        addCard(c);
    }

    public int getBJSum() {
        int sum = 0;
        int numAces = 0;

        for (Card c : cards) {
            switch (c.getRank()) {
                case TWO:
                case THREE:
                case FOUR:
                case FIVE:
                case SIX:
                case SEVEN:
                case EIGHT:
                case NINE:
                case TEN:
                    sum += c.getRank().ordinal() + 2;
                    break;
                case JACK:
                case QUEEN:
                case KING:
                    sum += 10;
                    break;
                case ACE:
                    sum += 11;
                    numAces++;
                    break;
            }
        }
        for (int i = 0 ; i < numAces ; i++) {
            if (sum > 21) {
                sum -= 10;
            }
        }
        return sum;
    }

    public Hand split() {
        Hand other = new Hand();
        other.add(cards.remove(1));
        other.setSplittable(false);
        setSplittable(false);
        return other;
    }

    public boolean isComplete() {
        return isStood() || isBust();
    }

    public void setIsStood(boolean isStood) {
        this.isStood = isStood;
    }

    public boolean isStood() {
        return isStood;
    }

    public boolean isBust() {
        return getBJSum() > 21;
    }

    boolean canDouble() {
        return canDouble;
    }

    void setCanDouble(boolean canDouble) {
        this.canDouble = canDouble;
    }

    boolean isSplittable() {
        return isSplittable;
    }

    void setSplittable(boolean isSplittable) {
        this.isSplittable = isSplittable;
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public Card get(int i) {
        return cards.get(i);
    }

    public int size() {
        return cards.size();
    }

    public boolean hasAce() {
        for (Card c : cards) {
            if (c.getRank() == Card.Rank.ACE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
