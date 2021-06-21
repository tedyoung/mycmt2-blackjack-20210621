package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

    @Test
    public void whenInitialDealThenPlayerAndDealerHaveTwoCards() throws Exception {
        Game game = new Game(new Deck());

        game.initialDeal();

        assertThat(game.playerHand().cards())
                .hasSize(2);
        assertThat(game.dealerHand().cards())
                .hasSize(2);
    }

    @Test
    public void whenPlayerBeatsDealerOutcomeIsPlayerBeatsDealer() throws Exception {
        Deck deck = new StubDeck(Rank.TEN,   Rank.EIGHT,
                                 Rank.QUEEN, Rank.JACK);
        Game game = new Game(deck);
        game.initialDeal();

        game.playerStands();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BEATS_DEALER);
    }

    @Test
    public void whenPlayerHitsGoesBustThenOutcomeIsPlayerBusted() throws Exception {
        Deck deck = new StubDeck(Rank.TEN,  Rank.EIGHT,
                                 Rank.NINE, Rank.QUEEN,
                                 Rank.FIVE);
        Game game = new Game(deck);
        game.initialDeal();

        game.playerHits();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BUSTED);
    }
}