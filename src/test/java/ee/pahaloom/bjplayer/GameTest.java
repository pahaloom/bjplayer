/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.pahaloom.bjplayer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ploom
 */
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
