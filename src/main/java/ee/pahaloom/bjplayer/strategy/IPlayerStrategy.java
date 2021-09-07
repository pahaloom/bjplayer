package ee.pahaloom.bjplayer.strategy;

import ee.pahaloom.bjplayer.Hand;
import ee.pahaloom.bjplayer.Move;

/**
 * Player strategy
 *
 * @author Ploom
 */
public interface IPlayerStrategy {
    Move nextMove(Hand dealerHand, Hand playerHand);
}
