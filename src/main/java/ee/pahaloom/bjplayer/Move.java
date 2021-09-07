/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.pahaloom.bjplayer;

/**
 * Basic strategy moves.
 *
 * @author Ploom
 */
public enum Move {
    S {
        @Override
        void apply(Shoe shoe, Player player) {
            player.stand();
        }
    }, H {
        @Override
        void apply(Shoe shoe, Player player) {
            player.hit(shoe);
        }
    }, D {
        @Override
        void apply(Shoe shoe, Player player) {
            player.doubleOrHit(shoe);
        }
    }, DS {
        @Override
        void apply(Shoe shoe, Player player) {
            player.doubleOrStand(shoe);
        }
    }, P {
        @Override
        void apply(Shoe shoe, Player player) {
            player.split(shoe);
        }
    };

    abstract void apply(Shoe shoe, Player player);
}
