/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.pahaloom.bjplayer.strategy;

import ee.pahaloom.bjplayer.Card;
import ee.pahaloom.bjplayer.Hand;
import ee.pahaloom.bjplayer.Move;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Ensure that Basic strategy provides correct actions based on given hands.
 *
 * @author Ploom
 */
@Tag("fast")
public class BasicStrategyTest {

    public BasicStrategyTest() {
    }

    /**
     * Test of getNextMove method, of class BasicStrategy.
     */
    @Test
    public void testHard() {
        for (Card.Rank dealerR : Card.Rank.values()) {
            Hand dealerH = new Hand(new Card[]{new Card(Card.Suit.CLUBS, dealerR)});

            Hand playerH5 = new Hand(new String[]{"H3", "S2"});
            assertEquals(Move.H, BasicStrategy.getNextMove(dealerH, playerH5));
            Hand playerH6 = new Hand(new String[]{"H2", "S2", "C2"});
            assertEquals(Move.H, BasicStrategy.getNextMove(dealerH, playerH6));
            Hand playerH7 = new Hand(new String[]{"H3", "S4"});
            assertEquals(Move.H, BasicStrategy.getNextMove(dealerH, playerH7));
            Hand playerH8 = new Hand(new String[]{"H3", "S5"});
            assertEquals(Move.H, BasicStrategy.getNextMove(dealerH, playerH8));

            int dealerSum = dealerH.getBJSum();

            Hand playerH9 = new Hand(new String[]{"H4", "S5"});
            Move nextMove9 = BasicStrategy.getNextMove(dealerH, playerH9);
            if (dealerSum == 2 || dealerSum > 6) {
                assertEquals(Move.H, nextMove9);
            } else {
                assertEquals(Move.D, nextMove9);
            }

            Hand playerH10 = new Hand(new String[]{"H2", "H8"});
            Move nextMove10 = BasicStrategy.getNextMove(dealerH, playerH10);
            Hand playerH11 = new Hand(new String[]{"H2", "H9"});
            Move nextMove11 = BasicStrategy.getNextMove(dealerH, playerH11);
            if (dealerSum > 9) {
                assertEquals(Move.H, nextMove10);
                assertEquals(Move.H, nextMove11);
            } else {
                assertEquals(Move.D, nextMove10);
                assertEquals(Move.D, nextMove11);
            }

            Hand playerH12 = new Hand(new String[]{"H10", "H2"});
            Move nextMove12 = BasicStrategy.getNextMove(dealerH, playerH12);
            if (dealerSum < 4 || dealerSum > 6) {
                assertEquals(Move.H, nextMove12);
            } else {
                assertEquals(Move.S, nextMove12);
            }

            Hand playerH13 = new Hand(new String[]{"H10", "H3"});
            Move nextMove13 = BasicStrategy.getNextMove(dealerH, playerH13);
            Hand playerH14 = new Hand(new String[]{"H10", "H4"});
            Move nextMove14 = BasicStrategy.getNextMove(dealerH, playerH14);
            Hand playerH15 = new Hand(new String[]{"H10", "H5"});
            Move nextMove15 = BasicStrategy.getNextMove(dealerH, playerH15);
            Hand playerH16 = new Hand(new String[]{"H10", "H6"});
            Move nextMove16 = BasicStrategy.getNextMove(dealerH, playerH16);
            if (dealerSum > 6) {
                assertEquals(Move.H, nextMove13);
                assertEquals(Move.H, nextMove14);
                assertEquals(Move.H, nextMove15);
                assertEquals(Move.H, nextMove16);
            } else {
                assertEquals(Move.S, nextMove13);
                assertEquals(Move.S, nextMove14);
                assertEquals(Move.S, nextMove15);
                assertEquals(Move.S, nextMove16);
            }

            Hand playerH17 = new Hand(new String[]{"H10", "H7"});
            Move nextMove17 = BasicStrategy.getNextMove(dealerH, playerH17);
            assertEquals(Move.S, nextMove17);

            Hand playerH18 = new Hand(new String[]{"H10", "H8"});
            Move nextMove18 = BasicStrategy.getNextMove(dealerH, playerH18);
            assertEquals(Move.S, nextMove18);

            Hand playerH19 = new Hand(new String[]{"H10", "H9"});
            Move nextMove19 = BasicStrategy.getNextMove(dealerH, playerH19);
            assertEquals(Move.S, nextMove19);
        }
    }

    @Test
    public void testSoft() {
        for (Card.Rank dealerR : Card.Rank.values()) {
            Hand dealerH = new Hand(new Card[]{new Card(Card.Suit.CLUBS, dealerR)});
            int dealerSum = dealerH.getBJSum();

            Hand player2 = new Hand(new String[]{"CA", "H2"});
            Move nextMove2 = BasicStrategy.getNextMove(dealerH, player2);
            if (dealerSum < 5 || dealerSum > 6) {
                assertEquals(Move.H, nextMove2);
            } else {
                assertEquals(Move.D, nextMove2);
            }

            Hand player3 = new Hand(new String[]{"H3", "HA"});
            Move nextMove3 = BasicStrategy.getNextMove(dealerH, player3);
            if (dealerSum < 5 || dealerSum > 6) {
                assertEquals(Move.H, nextMove3);
            } else {
                assertEquals(Move.D, nextMove3);
            }

            Hand player4 = new Hand(new String[]{"CA", "H4"});
            Move nextMove4 = BasicStrategy.getNextMove(dealerH, player4);
            if (dealerSum < 4 || dealerSum > 6) {
                assertEquals(Move.H, nextMove4);
            } else {
                assertEquals(Move.D, nextMove4);
            }

            Hand player5 = new Hand(new String[]{"H5", "HA"});
            Move nextMove5 = BasicStrategy.getNextMove(dealerH, player5);
            if (dealerSum < 4 || dealerSum > 6) {
                assertEquals(Move.H, nextMove5);
            } else {
                assertEquals(Move.D, nextMove5);
            }

            Hand player6 = new Hand(new String[]{"H6", "HA"});
            Move nextMove6 = BasicStrategy.getNextMove(dealerH, player6);
            if (dealerSum < 3 || dealerSum > 6) {
                assertEquals(Move.H, nextMove6);
            } else {
                assertEquals(Move.D, nextMove6);
            }

            Hand player7 = new Hand(new String[]{"H7", "HA"});
            Move nextMove7 = BasicStrategy.getNextMove(dealerH, player7);
            if (dealerSum < 3) {
                assertEquals(Move.S, nextMove7);
            } else if (dealerSum < 7) {
                assertEquals(Move.DS, nextMove7);
            } else if (dealerSum < 9) {
                assertEquals(Move.S, nextMove7);
            } else {
                assertEquals(Move.H, nextMove7);
            }

            Hand player8 = new Hand(new String[]{"H8", "HA"});
            Move nextMove8 = BasicStrategy.getNextMove(dealerH, player8);
            assertEquals(Move.S, nextMove8);

            Hand player9 = new Hand(new String[]{"H9", "HA"});
            Move nextMove9 = BasicStrategy.getNextMove(dealerH, player9);
            assertEquals(Move.S, nextMove9);
        }
    }

    @Test
    public void testSoftThreeCard() {
        for (Card.Rank dealerR : Card.Rank.values()) {
            Hand dealerH = new Hand(new Card[]{new Card(Card.Suit.CLUBS, dealerR)});
            int dealerSum = dealerH.getBJSum();

            Hand player13 = new Hand(new String[]{"CA", "DA", "HA"});
            Move nextMove13 = BasicStrategy.getNextMove(dealerH, player13);
            if (dealerSum < 5 || dealerSum > 6) {
                assertEquals(Move.H, nextMove13);
            } else {
                assertEquals(Move.D, nextMove13);
            }
        }
    }

    @Test
    public void testPair() {
        for (Card.Rank dealerR : Card.Rank.values()) {
            Hand dealerH = new Hand(new Card[]{new Card(Card.Suit.CLUBS, dealerR)});

            Hand player2 = new Hand(new String[]{"C2", "H2"});
            Move nextMove2 = BasicStrategy.getNextMove(dealerH, player2);
            if (dealerH.getBJSum() > 7) {
                assertEquals(Move.H, nextMove2);
            } else {
                assertEquals(Move.P, nextMove2);
            }

            Hand player3 = new Hand(new String[]{"H3", "H3"});
            Move nextMove3 = BasicStrategy.getNextMove(dealerH, player3);
            if (dealerH.getBJSum() > 7) {
                assertEquals(Move.H, nextMove3);
            } else {
                assertEquals(Move.P, nextMove3);
            }

            Hand player4 = new Hand(new String[]{"H4", "H4"});
            Move nextMove4 = BasicStrategy.getNextMove(dealerH, player4);
            if (dealerH.getBJSum() < 5 || dealerH.getBJSum() > 6) {
                assertEquals(Move.H, nextMove4);
            } else {
                assertEquals(Move.P, nextMove4);
            }

            Hand player5 = new Hand(new String[]{"H5", "H5"});
            Move nextMove5 = BasicStrategy.getNextMove(dealerH, player5);
            if (dealerH.getBJSum() > 9) {
                assertEquals(Move.H, nextMove5);
            } else {
                assertEquals(Move.D, nextMove5);
            }

            Hand player6 = new Hand(new String[]{"H6", "H6"});
            Move nextMove6 = BasicStrategy.getNextMove(dealerH, player6);
            if (dealerH.getBJSum() > 6) {
                assertEquals(Move.H, nextMove6);
            } else {
                assertEquals(Move.P, nextMove6);
            }

            Hand player7 = new Hand(new String[]{"H7", "H7"});
            Move nextMove7 = BasicStrategy.getNextMove(dealerH, player7);
            if (dealerH.getBJSum() > 7) {
                assertEquals(Move.H, nextMove7);
            } else {
                assertEquals(Move.P, nextMove7);
            }

            Hand player8 = new Hand(new String[]{"H8", "H8"});
            Move nextMove8 = BasicStrategy.getNextMove(dealerH, player8);
            if (dealerH.getBJSum() > 9) {
                assertEquals(Move.H, nextMove8);
            } else {
                assertEquals(Move.P, nextMove8);
            }

            Hand player9 = new Hand(new String[]{"H9", "H9"});
            Move nextMove9 = BasicStrategy.getNextMove(dealerH, player9);
            if (dealerH.getBJSum() == 7 || dealerH.getBJSum() > 9) {
                assertEquals(Move.S, nextMove9);
            } else {
                assertEquals(Move.P, nextMove9);
            }

            Hand player10 = new Hand(new String[]{"HJ", "HJ"});
            Move nextMove10 = BasicStrategy.getNextMove(dealerH, player10);
            assertEquals(Move.S, nextMove10);

            Hand player11 = new Hand(new String[]{"HA", "HA"});
            Move nextMove11 = BasicStrategy.getNextMove(dealerH, player11);
            if (dealerH.getBJSum() > 10) {
                assertEquals(Move.H, nextMove11);
            } else {
                assertEquals(Move.P, nextMove11);
            }
        }
    }
}
