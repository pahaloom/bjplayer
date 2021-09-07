/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.pahaloom.bjplayer;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@Tag("fast")
public class SplittingTest {

    /**
     * D: [H4, H10, H3]: 17
     * Pa: [C2, S3, D2, S2, S7]: 16
     * Pb: [S2, S5]: 7
     */
    @Test
    public void splitScenario1() {
        Shoe s = new Shoe(new String[]{"C2", "H4", "S2", "H10", "S3", "S5", "D2", "S2", "S7", "H10", "H3"});
        Game game = new Game(s);
        game.run();

        System.out.println(game.dealerHand);
        assertEquals("[H4, H10, H3]", game.dealerHand.toString());

        System.out.println(game.player.playerHand);
        assertEquals("[C2, S3, D2, S2, S7]", game.player.playerHand.toString());

        System.out.println(game.player.splitHand);
        assertEquals("[S2, S5, H10]", game.player.splitHand.toString());
    }

    /**
     * D: [D7, C8, C2]: 17
     * Pa: [H2, S4, D2, S2, SA]: 21
     * Pb: [C2, D3]: 5
     */
    @Test
    public void splitScenario2() {
        Shoe s = new Shoe(new String[]{"H2", "D7", "C2", "C8", "S4", "D3", "D2", "S2", "SA", "C2", "CK", "C2"});
        Game game = new Game(s);
        game.run();

        System.out.println(game.dealerHand);
        assertEquals("[D7, C8, C2]", game.dealerHand.toString());
        assertEquals(17, game.dealerHand.getBJSum());

        System.out.println(game.player.playerHand);
        assertEquals("[H2, S4, D2, S2, SA]", game.player.playerHand.toString());
        assertEquals(21, game.player.playerHand.getBJSum());

        System.out.println(game.player.splitHand);
        assertEquals("[C2, D3, C2, CK]", game.player.splitHand.toString());
        assertEquals(2 + 3 + 2 + 10, game.player.splitHand.getBJSum());
    }

    /**
     * B=2 W=0 D: [S4, S5, D7, D3]: 19
     * Pa: [H9, D8]: 17
     */
    @Test
    public void scenario1() {
        Shoe s = new Shoe(new String[]{"H9", "S4", "D8", "S5", "D7", "D3"});
        Game game = new Game(s);
        game.run();

        System.out.println(game.dealerHand);
        assertEquals("[S4, S5, D7, D3]", game.dealerHand.toString());
        assertEquals(19, game.dealerHand.getBJSum());

        System.out.println(game.player.playerHand);
        assertEquals("[H9, D8]", game.player.playerHand.toString());
        assertEquals(17, game.player.playerHand.getBJSum());

        System.out.println(game.player.splitHand);
        assertNull(game.player.splitHand);
    }
}
