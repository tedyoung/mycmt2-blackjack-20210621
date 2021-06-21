package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

    @Test
    public void whenInitialDealThenPlayerAndDealerHaveTwoCards() throws Exception {
        Game game = new Game();

        game.initialDeal();

        assertThat(game.playerHand().cards())
                .hasSize(2);
        assertThat(game.dealerHand().cards())
                .hasSize(2);
    }

    @Disabled
    @Test
    public void whenPlayerBeatsDealerOutcomeIsPlayerBeatsDealer() throws Exception {
        Game game = new Game();
        game.initialDeal();

        game.playerStands();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualTo("You beat the Dealer! 💵");
    }

}