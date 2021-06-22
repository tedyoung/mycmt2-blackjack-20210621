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
        Deck deck = StubDeck.playerBeatsDealer();
        Game game = new Game(deck);
        game.initialDeal();

        game.playerStands();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BEATS_DEALER);
    }

    @Test
    public void whenPlayerHitsGoesBustThenOutcomeIsPlayerBusted() throws Exception {
        Deck deck = StubDeck.playerHitsAndGoesBust();
        Game game = new Game(deck);
        game.initialDeal();

        game.playerHits();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BUSTED);
    }

    @Test
    public void playerDealtBlackjackThenOutcomeIsWinsBlackjack() throws Exception {
        Deck playerWinsBlackjack = new StubDeck(Rank.ACE,  Rank.NINE,
                                                Rank.JACK, Rank.TEN);
        Game game = new Game(playerWinsBlackjack);

        game.initialDeal();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_WIN_BLACKJACK);
    }


}