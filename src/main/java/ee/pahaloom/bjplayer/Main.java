package ee.pahaloom.bjplayer;

import ee.pahaloom.bjplayer.strategy.BasicStrategy;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        Shoe s = new Shoe(1);
        for (int i = 0 ; i < Card.Rank.values().length ; i++) {
            System.out.print(" " + s.pop());
        }
        System.out.println();
        s.shuffle();
        s.reset();
        for (int i = 0 ; i < Card.Rank.values().length ; i++) {
            System.out.print(" " + s.pop());
        }
        System.out.println();
        s.reset();

        Hand dh = new Hand();
        Hand ph = new Hand();
        ph.add(s.pop());
        dh.add(s.pop());
        ph.add(s.pop());
        
        System.out.println(ph + ":" + ph.getBJSum());
        System.out.println(dh + ":" + dh.getBJSum());

        Shoe shoe = new Shoe(6);
        shoe.shuffle();
        for (int i = 0; i < 10 ; i++) {
            runGame(shoe);
        }
        */

        GameRunner.play();
    }

    static void runGame(Shoe shoe) {
        Game game = new Game(shoe, 10, new BasicStrategy());
        game.run();
        System.out.println("B=" + game.player.totalBet + " W=" + game.calculateWin() + " D: " + game.dealerHand + ": " + game.dealerHand.getBJSum());
        System.out.println("Pa: " + game.player.playerHand + ": " + game.player.playerHand.getBJSum());
        if (game.player.splitHand != null) {
            System.out.println("Pb: " + game.player.splitHand + ": " + game.player.splitHand.getBJSum());
        }
    }
}
