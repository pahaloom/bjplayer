package ee.pahaloom.bjplayer;

import ee.pahaloom.bjplayer.strategy.BasicStrategy;
import ee.pahaloom.bjplayer.strategy.IPlayerStrategy;

class GameRunner {
    long totalBet = 0, totalWin = 0;
    IPlayerStrategy playerStrategy;

    public GameRunner() {
        this(new BasicStrategy());
    }

    public GameRunner(IPlayerStrategy playerStrategy) {
        this.playerStrategy = playerStrategy;
    }

    private void runGame(Shoe shoe) {
        Game game = new Game(shoe, 10, playerStrategy);
        game.run();
        totalBet += game.player.totalBet;
        totalWin += game.calculateWin();
    }

    public static void play(int iterations, IPlayerStrategy playerStrategy) {
        Shoe shoe = new Shoe(6);
        shoe.shuffle();
        GameRunner runner = new GameRunner(playerStrategy);
        for (int i = 0; i < iterations; i++) {
            runner.runGame(shoe);
            if ((float) shoe.getIndex() / (float) shoe.getSize() > 0.8F) {
                // 80% of shoe played
                shoe.shuffle();
                shoe.reset();
            }
        }
        System.out.printf("Done %d %-20s: %d %d %.5f%n"
            , iterations
            , playerStrategy.getClass().getSimpleName()
            , runner.totalBet
            , runner.totalWin
            , (double) runner.totalWin / (double) runner.totalBet);
    }
}
