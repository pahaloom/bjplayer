package ee.pahaloom.bjplayer.strategy;

import ee.pahaloom.bjplayer.Card;
import ee.pahaloom.bjplayer.Hand;
import ee.pahaloom.bjplayer.Move;

import static ee.pahaloom.bjplayer.Move.*;

/**
 * Basic strategy
 * <p>
 * 6 decks, S17, DAS, No Surrender, No Peek
 * Estimated casino edge for these rules: 0.55 %
 * <p>
 * https://www.blackjackinfo.com/blackjack-basic-strategy-engine/?numdecks=6&soft17=s17&dbl=all&das=yes&surr=ns&peek=no
 *
 * @author Ploom
 */
public class BasicStrategy implements IPlayerStrategy {

    @Override
    public Move nextMove(Hand dealerHand, Hand playerHand) {
        return BasicStrategy.getNextMove(dealerHand, playerHand);
    }

    public static Move[][] hardTotals = new Move[][]{
        {H, H, H, H, H, H, H, H, H, H}, // Hard 5
        {H, H, H, H, H, H, H, H, H, H}, // Hard 6
        {H, H, H, H, H, H, H, H, H, H}, // Hard 7
        {H, H, H, H, H, H, H, H, H, H}, // Hard 8
        {H, D, D, D, D, H, H, H, H, H}, // Hard 9
        {D, D, D, D, D, D, D, D, H, H}, // Hard 10
        {D, D, D, D, D, D, D, D, H, H}, // Hard 11
        {H, H, S, S, S, H, H, H, H, H}, // Hard 12
        {S, S, S, S, S, H, H, H, H, H}, // Hard 13
        {S, S, S, S, S, H, H, H, H, H}, // Hard 14
        {S, S, S, S, S, H, H, H, H, H}, // Hard 15
        {S, S, S, S, S, H, H, H, H, H}, // Hard 16
        {S, S, S, S, S, S, S, S, S, S}, // Hard 17
        {S, S, S, S, S, S, S, S, S, S}, // Hard 18+
    };

    public static Move[][] softTotals = new Move[][]{
        {H, H, H, D, D, H, H, H, H, H}, // Ace + 2
        {H, H, H, D, D, H, H, H, H, H}, // Ace + 3
        {H, H, D, D, D, H, H, H, H, H}, // Ace + 4
        {H, H, D, D, D, H, H, H, H, H}, // Ace + 5
        {H, D, D, D, D, H, H, H, H, H}, // Ace + 6
        {S, DS, DS, DS, DS, S, S, H, H, H}, // Ace + 7
        {S, S, S, S, S, S, S, S, S, S}, // Ace + 8
        {S, S, S, S, S, S, S, S, S, S}, // Ace + 9
    };

    public static Move[][] pairs = new Move[][]{
        {P, P, P, P, P, P, H, H, H, H}, // 2,2
        {P, P, P, P, P, P, H, H, H, H}, // 3,3
        {H, H, H, P, P, H, H, H, H, H}, // 4,4
        {D, D, D, D, D, D, D, D, H, H}, // 5,5
        {P, P, P, P, P, H, H, H, H, H}, // 6,6
        {P, P, P, P, P, P, H, H, H, H}, // 7,7
        {P, P, P, P, P, P, P, P, H, H}, // 8,8
        {P, P, P, P, P, S, P, P, S, S}, // 9,9
        {S, S, S, S, S, S, S, S, S, S}, // (T,T)
        {P, P, P, P, P, P, P, P, P, H}, // (A,A)
    };

    public static int getBJRank(Card.Rank rank) {
        switch (rank) {
            case TWO:
            case THREE:
            case FOUR:
            case FIVE:
            case SIX:
            case SEVEN:
            case EIGHT:
            case NINE:
            case TEN:
                return rank.ordinal();
            case JACK:
            case QUEEN:
            case KING:
                return 8;
            case ACE:
                return 9;
            default:
                // Should never happen
                throw new IllegalArgumentException("Unknown card rank: " + rank);
        }
    }

    private static int getSoftBJRank(Card.Rank rank) {
        switch (rank) {
            case ACE:
                return 1;
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

    private static int getSoftBJIdx(Hand playerHand) {
        boolean isAceSkipped = false;
        int softSum = 0;
        for (Card c : playerHand) {
            if (!isAceSkipped && c.getRank() == Card.Rank.ACE) {
                isAceSkipped = true;
                continue;
            }
            softSum += getSoftBJRank(c.getRank());
        }
        return softSum - 2;
    }

    public static Move getNextMove(Hand dealerHand, Hand playerHand) {
        if (playerHand.getBJSum() == 21) {
            return Move.S;
        }
        int dealerIdx = getBJRank(dealerHand.get(0).getRank());
        if (playerHand.size() == 2) {
            Card.Rank playerFirst = playerHand.get(0).getRank();
            Card.Rank playerSecond = playerHand.get(1).getRank();
            if (playerFirst == playerSecond) {
                return pairs[getBJRank(playerFirst)][dealerIdx];
            }
        }
        if (playerHand.hasAce()) {
            int softIdx = getSoftBJIdx(playerHand);
            if (softIdx >= 0 && softIdx < softTotals.length) {
                return softTotals[softIdx][dealerIdx];
            }
        }
        int playerIdx = playerHand.getBJSum() - 5;
        return hardTotals[Math.min(playerIdx, hardTotals.length - 1)][dealerIdx];
    }
}
