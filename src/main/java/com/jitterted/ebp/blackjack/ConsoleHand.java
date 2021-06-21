package com.jitterted.ebp.blackjack;

public class ConsoleHand {

    // Transforms Hand (DOMAIN) -> String (OUTSIDE WORLD)
    static String displayFirstCard(Hand hand) {
        return ConsoleCard.display(hand.cards().get(0));
    }
}
