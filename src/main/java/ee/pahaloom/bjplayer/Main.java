package ee.pahaloom.bjplayer;

import ee.pahaloom.bjplayer.strategy.BasicStrategy;
import ee.pahaloom.bjplayer.strategy.IPlayerStrategy;
import ee.pahaloom.bjplayer.strategy.SimpleStrategy;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

public class Main {
    private static final int DEFAULT_ROUNDS = 10;
    private static final int DEFAULT_ITERATIONS = 1000000;

    private static Tasker TASK_BUILDER = Tasker.SILENT;

    public static void usage(int status) {
        var out = System.out;
        out.printf("Usage: %s [-v] [rounds] [iterations]%n%n", Main.class.getSimpleName());
        out.printf("By default %d rounds of %d BlackJack games will be played using different strategies%n", DEFAULT_ROUNDS, DEFAULT_ITERATIONS);
        out.printf("Output will contain total bet amount, win amount and win percentage.%n%n");
        out.println("Optionally -v will enable verbose output to stderr.");
        out.println("Giving -h or --help as first argument will result displaying usage instructions.");
        System.exit(status);
    }

    /**
     * Play some BlackJack games with each available strategy.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String[] params = args;
        if (args.length > 0 && "-v".equals(args[0])) {
            TASK_BUILDER = Tasker.VERBOSE;
            params = new String[args.length - 1];
            System.arraycopy(args, 1, params, 0, params.length);
        } else if (args.length > 0 && ("-h".equals(args[0]) || "--help".equals(args[0]))) {
            usage(0);
        }

        int rounds = DEFAULT_ROUNDS;
        int iterations = DEFAULT_ITERATIONS;
        try {
            switch (params.length) {
                case 2:
                    iterations = Integer.parseInt(params[1]);
                case 1:
                    rounds = Integer.parseInt(params[0]);
                case 0:
                    break;
                default:
                    usage(0);
                    break;
            }
        } catch (NumberFormatException e) {
            usage(-1);
        }

        System.out.printf("Running %d rounds of %d iterations%n", rounds, iterations);

        var es = Executors.newFixedThreadPool(4);
        var tasks = scheduleTasks(es, rounds, iterations);
        es.shutdown();
        for (var f : tasks) {
            f.get().report(System.out);
        }
        if (es.awaitTermination(10L, TimeUnit.DAYS)) {
            System.out.println("All tasks completed successfully.");
        } else {
            System.err.println("Waited really too long!");
            System.exit(1);
        }
    }

    private static Collection<Future<GameResult>> scheduleTasks(ExecutorService es, int rounds, int iterations) {
        var results = new ArrayList<Future<GameResult>>(rounds * 2);
        for (int i = 0; i < rounds; i++) {
            for (IPlayerStrategy strategy : new IPlayerStrategy[]{new SimpleStrategy(), new BasicStrategy()}) {
                results.add(
                    es.submit(
                        TASK_BUILDER.build(i, iterations, strategy)));
            }
        }
        return results;
    }

    enum Tasker {
        SILENT {
            GameRunner.Silent build(int id, int iterations, IPlayerStrategy strategy) {
                return new GameRunner.Silent(id, iterations, strategy);
            }
        },
        VERBOSE {
            GameRunner.Silent build(int id, int iterations, IPlayerStrategy strategy) {
                System.err.println("Submitting: " + id + " " + strategy.getClass().getSimpleName());
                return new GameRunner.Chatty(id, iterations, strategy);
            }
        };

        abstract GameRunner.Silent build(int id, int iterations, IPlayerStrategy strategy);
    }
}
