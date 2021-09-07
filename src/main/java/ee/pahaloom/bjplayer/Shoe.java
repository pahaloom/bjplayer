package ee.pahaloom.bjplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Card shoe containing number of decks.
 *
 * @author Ploom
 */
public class Shoe {
    private List<Card> cards;
    private int index;

    public Shoe(int numDecks) {
        init(numDecks);
    }

    Shoe(String[] cards) {
        this.cards = new ArrayList<>(cards.length);
        for (String cardStr : cards) {
            this.cards.add(Card.parse(cardStr));
        }
    }

    public void init(int numDecks) {
        cards = new ArrayList<>(numDecks * Card.Suit.values().length * Card.Rank.values().length);
        for (int n = 0; n < numDecks; n++) {
            for (Card.Suit s : Card.Suit.values()) {
                for (Card.Rank r : Card.Rank.values()) {
                    cards.add(new Card(s, r));
                }
            }
        }
        index = 0;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card pop() {
        return cards.get(index++);
    }

    void reset() {
        index = 0;
    }

    public int getIndex() {
        return index;
    }

    public int getSize() {
        return cards.size();
    }
}
