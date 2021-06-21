package com.jitterted.ebp.blackjack.domain;

public enum GameOutcome {
    PLAYER_BUSTED("You Busted, so you lose.  💸"),
    DEALER_BUSTED("Dealer went BUST, Player wins! Yay for you!! 💵"),
    PLAYER_BEATS_DEALER("You beat the Dealer! 💵"),
    PLAYER_PUSHES("Push: The house wins, you Lose. 💸"),
    PLAYER_LOSES("You lost to the Dealer. 💸");

    private final String value;

    public String display() {
        return value;
    }

    GameOutcome(String value) {
        this.value = value;
    }
}