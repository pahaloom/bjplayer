package ee.pahaloom.bjplayer.strategy;

import ee.pahaloom.bjplayer.Card;
import ee.pahaloom.bjplayer.Hand;
import ee.pahaloom.bjplayer.Move;

/**
 * Another strategy found on the internet (in haskell):  <code>
 * -- very simple player for the time being
 * playerNextMove :: Hand -> Card -> Move
 * playerNextMove playerHand dealerVisibleCard
 * | playerScore > Value 16 = Stand
 * | playerScore > Value 12 && dealerScore < Value 7 = Stand
 * | playerScore == Value 11 || playerScore == Value 10 = DoubleDown
 * | otherwise = Hit
 * where playerScore = handScore playerHand
 * dealerScore = handScore [dealerVisibleCard]
 * </code>
 * <p>
 * Source: https://github.com/Wilfred/Blackjack/blob/master/Blackjack.hs
 *
 * @author Ploom
 */
public class SimpleStrategy implements IPlayerStrategy {

    @Override
    public Move nextMove(Hand dealerHand, Hand playerHand) {
        return SimpleStrategy.getNextMove(dealerHand, playerHand);
    }

    private static int dealerScore(Card.Rank rank) {
        switch (rank) {
            case ACE:
                return 11;
            case TWO:
            case THREE:
            case FOUR:
            case FIVE:
            case SIX:
            case SEVEN:
            case EIGHT:
            case NINE:
            case TEN:
                return rank.ordinal() + 2;
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            default:
                // Should never happen
                throw new IllegalArgumentException("Unknown card rank: " + rank);
        }
    }

    private static Move getNextMove(Hand dealerHand, Hand playerHand) {
        int playerScore = playerHand.getBJSum();
        if (playerScore > 16) {
            return Move.S;
        } else if (playerScore > 12 && dealerScore(dealerHand.get(0).getRank()) < 7) {
            return Move.S;
        } else if (playerScore == 11 || playerScore == 10) {
            return Move.D;
        }
        return Move.H;
    }
}
