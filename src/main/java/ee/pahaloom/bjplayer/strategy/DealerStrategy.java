package ee.pahaloom.bjplayer.strategy;

import ee.pahaloom.bjplayer.Hand;
import ee.pahaloom.bjplayer.Move;

/**
 * Hits until 17
 *
 * @author Ploom
 */
public class DealerStrategy implements IPlayerStrategy {

    @Override
    public Move nextMove(Hand dealerHand, Hand playerHand) {
        return DealerStrategy.getNextMove(dealerHand, playerHand);
    }

    public static Move getNextMove(Hand dealerHand, Hand playerHand) {
        if (playerHand.getBJSum() < 17) {
            return Move.H;
        }
        return Move.S;
    }
}
