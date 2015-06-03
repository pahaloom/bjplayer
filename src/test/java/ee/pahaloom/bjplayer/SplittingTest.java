/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.pahaloom.bjplayer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SplittingTest {
    /**
     * D: [H4, H10, H3]: 17
     * Pa: [C2, S3, D2, S2, S7]: 16
     * Pb: [S2, S5]: 7
     */
    @Test
    public void splitScenario1() {
        Shoe s = new Shoe(new String[] {"C2", "H4", "S2", "H10"
                , "S3", "S5"
                , "D2", "S2", "S7"
                , "H10", "H3"});
        Game game = new Game(s);
        game.run();
        System.out.println(game.dealerHand);
        System.out.println(game.player.playerHand);
        System.out.println(game.player.splitHand);
    }

    /**
     * D: [D7, C8, C2]: 17
     * Pa: [H2, S4, D2, S2, SA]: 21
     * Pb: [C2, D3]: 5
     */
    @Test
    public void splitScenario2() {
        Shoe s = new Shoe(new String[] {"H2", "D7", "C2", "C8"
                , "S4", "D3"
                , "D2", "S2", "SA"
                , "C2"
                , "CK", "DK"
        });
        Game game = new Game(s);
        game.run();
        System.out.println(game.dealerHand);
        System.out.println(game.player.playerHand);
        System.out.println(game.player.splitHand);
    }

    /**
     * B=2 W=0 D: [S4, S5, D7, D3]: 19
     * Pa: [H9, D8]: 17
     */
    @Test
    public void scenario1() {
        Shoe s = new Shoe(new String[] {"H9", "S4", "D8", "S5"
                , "D7", "D3"
        });
        Game game = new Game(s);
        game.run();
        System.out.println(game.dealerHand);
        System.out.println(game.player.playerHand);
        System.out.println(game.player.splitHand);
    }
}
