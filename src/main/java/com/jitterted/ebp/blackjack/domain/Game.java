package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.domain.port.GameMonitor;

public class Game {

    private final Deck deck;

    private final GameMonitor gameMonitor;

    private final Hand dealerHand = new Hand();
    private final Hand playerHand = new Hand();
    private boolean playerDone;

    public Game(Deck deck) {
        this.deck = deck;
        this.gameMonitor = game -> {}; // no-op (Null Object Pattern) implementation
    }

    public Game(Deck deck, GameMonitor gameMonitor) {
        this.deck = deck;
        this.gameMonitor = gameMonitor;
    }

    public void initialDeal() {
        dealRoundOfCards();
        dealRoundOfCards();
    }

    private void dealRoundOfCards() {
        // why: players first because this is the rule
        playerHand.drawFrom(deck);
        dealerHand.drawFrom(deck);
    }

    public GameOutcome determineOutcome() {
        if (playerHand.isBusted()) {
            return GameOutcome.PLAYER_BUSTED;
        } else if (dealerHand.isBusted()) {
            return GameOutcome.DEALER_BUSTED;
        } else if (playerHand.isBlackjack()) {
            return GameOutcome.PLAYER_WIN_BLACKJACK;
        } else if (playerHand.beats(dealerHand)) {
            return GameOutcome.PLAYER_BEATS_DEALER;
        } else if (playerHand.pushes(dealerHand)) {
            return GameOutcome.PLAYER_PUSHES;
        } else {
            return GameOutcome.PLAYER_LOSES;
        }
    }

    private void dealerTurn() {
        // Dealer makes its choice automatically based on a simple heuristic (<=16, hit, 17>stand)
        if (!playerHand.isBusted()) {
            while (dealerHand.dealerMustDrawCard()) {
                dealerHand.drawFrom(deck);
            }
        }
    }

    // ENCAPSULATE BETTER, e.g., HandView
    public Hand playerHand() {
        return playerHand;
    }

    // ENCAPSULATE BETTER, e.g., HandView
    public Hand dealerHand() {
        return dealerHand;
    }

    public void playerHits() {
        // constraint/guard class: requires player not being done
        playerHand.drawFrom(deck);
        playerDone = playerHand.isBusted();
        if (playerDone) {
            gameMonitor.roundCompleted(this);
        }
    }

    public void playerStands() {
        // constraint/guard class: requires player not being done
        playerDone = true;
        dealerTurn();
        gameMonitor.roundCompleted(this);
    }

    public boolean isPlayerDone() {
        return playerDone;
    }
}
