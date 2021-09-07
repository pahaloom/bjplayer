package ee.pahaloom.bjplayer;

/**
 * BlackJack player.
 * Manage bet and hands
 *
 * @author Ploom
 */
class Player {
    Hand playerHand = new Hand();
    Hand splitHand = null;

    Hand currentHand = playerHand;

    int initialBet;
    int totalBet;

    public Player(int initialBet) {
        this.initialBet = initialBet;
        this.totalBet = initialBet;
    }

    boolean hasMoves() {
        return currentHand != null && !currentHand.isComplete();
    }

    void stand() {
        currentHand.setIsStood(true);
        currentHand = splitHand;
    }

    void hit(Shoe shoe) {
        currentHand.add(shoe.pop());
        if (currentHand.isComplete()) {
            currentHand = splitHand;
        }
    }

    void doubleOrHit(Shoe shoe) {
        if (currentHand.canDouble()) {
            totalBet += initialBet;
            currentHand.setCanDouble(false);
            currentHand.add(shoe.pop());
            currentHand.setIsStood(true);
            currentHand = splitHand;
        } else {
            hit(shoe);
        }
    }

    void doubleOrStand(Shoe shoe) {
        if (currentHand.canDouble()) {
            totalBet += initialBet;
            currentHand.setCanDouble(false);
            currentHand.add(shoe.pop());
            currentHand.setIsStood(true);
        } else {
            stand();
        }
    }

    void split(Shoe shoe) {
        if (currentHand.isSplittable()) {
            totalBet += initialBet;
            splitHand = playerHand.split();
            playerHand.add(shoe.pop());
            splitHand.add(shoe.pop());
        } else {
            hit(shoe);
        }
    }

    boolean isBust() {
        if (splitHand != null) {
            return playerHand.isBust() && splitHand.isBust();
        }
        return playerHand.isBust();
    }
}
