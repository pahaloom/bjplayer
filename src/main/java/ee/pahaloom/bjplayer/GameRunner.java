package ee.pahaloom.bjplayer;

import ee.pahaloom.bjplayer.strategy.BasicStrategy;
import ee.pahaloom.bjplayer.strategy.IPlayerStrategy;

import java.util.concurrent.Callable;

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

    public static GameRunner play(int iterations, IPlayerStrategy playerStrategy) {
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
        return runner;
    }

    public static class Silent implements Callable<GameResult> {
        protected final int id;
        protected final int iterations;
        protected final IPlayerStrategy strategy;

        public Silent(int id, int iterations, IPlayerStrategy strategy) {
            this.id = id;
            this.iterations = iterations;
            this.strategy = strategy;
        }

        @Override
        public GameResult call() throws Exception {
            return new GameResult(id, GameRunner.play(iterations, strategy));
        }
    }

    public static class Chatty extends Silent {
        public Chatty(int id, int iterations, IPlayerStrategy strategy) {
            super(id, iterations, strategy);
        }
        @Override
        public GameResult call() throws Exception {
            GameResult result = super.call();
            System.err.println("Finished: " + super.id + " " + super.strategy.getClass().getSimpleName());
            return result;
        }
    }
}
