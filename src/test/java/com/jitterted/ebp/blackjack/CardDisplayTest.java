package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CardDisplayTest {
    
    @Test
    public void displayTenAsString() throws Exception {
        Card card = new Card(Suit.HEARTS, Rank.TEN);

        String display = card.display();

        assertThat(display)
                .isEqualTo("[31m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♥    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘");
    }
}
