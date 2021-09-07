package ee.pahaloom.bjplayer;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing the Game class.
 *
 * @author Ploom
 */
@Tag("fast")
public class GameTest {

    public GameTest() {
    }

    @Test
    public void testDealerBJ() {
        Game g = new Game(null);
        g.dealerHand.add(Card.parse("H10"));
        g.dealerHand.add(Card.parse("SA"));
        assertEquals(0, g.calculateWin());
    }

    @Test
    public void testPlayer21Doubled() {
        Game g = new Game(null);
        g.dealerHand.add(Card.parse("H10"));
        g.dealerHand.add(Card.parse("D7"));
        g.player.initialBet = 2;
        g.player.totalBet = 4;
        g.player.currentHand.setCanDouble(false);
        g.player.currentHand.add(Card.parse("H7"));
        g.player.currentHand.add(Card.parse("H3"));
        g.player.currentHand.add(Card.parse("HA"));
        assertEquals(6, g.calculateWin());
    }

    @Test
    public void testSplittedDoubled21() {
        Game g = new Game(null);
        g.dealerHand.add(Card.parse("H10"));
        g.dealerHand.add(Card.parse("D7"));
        g.player.initialBet = 2;
        g.player.totalBet = 8;
        g.player.currentHand.setCanDouble(false);
        g.player.currentHand.add(Card.parse("H10"));
        g.player.currentHand.add(Card.parse("HA"));
        g.player.currentHand = g.player.splitHand = new Hand();
        g.player.currentHand.setCanDouble(false);
        g.player.currentHand.add(Card.parse("H10"));
        g.player.currentHand.add(Card.parse("HA"));
        assertEquals(12, g.calculateWin());
    }
}
