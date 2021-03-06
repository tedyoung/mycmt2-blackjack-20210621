package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.Card;
import com.jitterted.ebp.blackjack.domain.Rank;
import com.jitterted.ebp.blackjack.domain.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CardDisplayTest {
    
    @Test
    public void displayTenAsString() throws Exception {
        Card card = new Card(Suit.HEARTS, Rank.TEN);

        String display = ConsoleCard.display(card);

        assertThat(display)
                .isEqualTo("[31m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♥    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘");
    }

    @Test
    public void displayNonTenAsString() throws Exception {
        Card card = new Card(Suit.CLUBS, Rank.FIVE);

        String display = ConsoleCard.display(card);

        assertThat(display)
                .isEqualTo("[30m┌─────────┐[1B[11D│5        │[1B[11D│         │[1B[11D│    ♣    │[1B[11D│         │[1B[11D│        5│[1B[11D└─────────┘");
    }
}
