package ee.pahaloom.bjplayer;

import java.io.PrintStream;

public class GameResult {
    protected final int id;
    protected final GameRunner runner;

    public GameResult(int id, GameRunner gameRunner) {
        this.id = id;
        this.runner = gameRunner;
    }

    public void report(PrintStream out) {
        out.printf("%03d %-20s: %d %d %.5f%n"
            , id
            , runner.playerStrategy.getClass().getSimpleName()
            , runner.totalBet
            , runner.totalWin
            , (double) runner.totalWin / (double) runner.totalBet);
    }
}
