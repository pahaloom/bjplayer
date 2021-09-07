package ee.pahaloom.bjplayer;

import ee.pahaloom.bjplayer.strategy.BasicStrategy;
import ee.pahaloom.bjplayer.strategy.IPlayerStrategy;

/**
 * One BlackJack game
 *
 * @author Ploom
 */
public class Game {
    final Shoe shoe;
    final Hand dealerHand = new Hand();
    final Player player;
    private final IPlayerStrategy playerStrategy;

    public Game(Shoe shoe, int initialBet, IPlayerStrategy playerStrategy) {
        this.shoe = shoe;
        this.player = new Player(initialBet);
        this.playerStrategy = playerStrategy;
    }

    public Game(Shoe shoe) {
        this(shoe, 2, new BasicStrategy());
    }

    Game(Shoe shoe, IPlayerStrategy playerStrategy) {
        this(shoe, 2, playerStrategy);
    }

    public void run() {
        player.playerHand.add(shoe.pop());
        dealerHand.add(shoe.pop());
        player.playerHand.add(shoe.pop());
        dealerHand.add(shoe.pop());

        while (player.hasMoves()) {
            Move m = playerStrategy.nextMove(dealerHand, player.currentHand);
            m.apply(shoe, player);
        }

        if (!player.isBust()) {
            while (dealerHand.getBJSum() < 17) {
                dealerHand.add(shoe.pop());
            }
        }
    }

    public int calculateWin() {
        if (player.isBust()) {
            return 0;
        }
        int win = 0;
        win += calculateWin(player.playerHand);
        if (player.splitHand != null) {
            win += calculateWin(player.splitHand);
        }
        return win;
    }

    private int calculateWin(Hand plHand) {
        int plSum = plHand.getBJSum();
        if (plSum > 21) {
            return 0; // Player bust
        }

        int dlSum = dealerHand.getBJSum();
        int handBet = plHand.canDouble() ? player.initialBet : player.initialBet * 2;

        if (plHand.size() == 2 && plSum == 21) {
            // Natural
            if (dlSum == 21 && dealerHand.size() == 2) {
                // Push
                return handBet;
            } else {
                // Player wins
                return handBet * 3 / 2;
            }
        }

        if (dlSum > 21 || plSum > dlSum) {
            // Player wins
            return handBet * 3 / 2;
        }
        if (plSum == dlSum) {
            // Push
            return handBet;
        }
        return 0;
    }
}
