package ee.pahaloom.bjplayer;

import ee.pahaloom.bjplayer.strategy.BasicStrategy;
import ee.pahaloom.bjplayer.strategy.SimpleStrategy;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
    /**
     * Play millions of rounds of BlackJack games with each available stragegy.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
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

        int rounds = 10;
        int iterations = 1000000;

        switch (args.length) {
            case 3 :
                System.out.println("Ignoring arguments since: " + args[2]);
            case 2 : iterations = Integer.parseInt(args[1]);
            case 1 : rounds = Integer.parseInt(args[0]);
            default:
                break;
        }

        System.out.printf("Running %d rounds of %d iterations%n", rounds, iterations);

        final var finalIterations = iterations;
        final var es = Executors.newFixedThreadPool(4);
        for (int i = 0 ; i < rounds ; i++) {
            es.submit(() -> GameRunner.play(finalIterations, new BasicStrategy()));
            es.submit(() -> GameRunner.play(finalIterations, new SimpleStrategy()));
        }
        es.shutdown();
        if (es.awaitTermination(10L, TimeUnit.DAYS)) {
            System.out.println("Completed successfully.");
        } else {
            System.err.println("Waited really too long!");
            System.exit(1);
        }
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
