package classes;

import java.util.Random;

public class GameLogic {

    private Game game;
    private PlayerState[] playerStates;
    private Card[] cards;

    public GameLogic(Game game, PlayerState[] playerStates, Card[] cards) {
        this.game = game;
        this.playerStates = playerStates;
        this.cards = cards;
    }

    public boolean isStart() {
        return game.getCardId() == -1;
    }

    public boolean isStage1() {
        for (int i = 0; i < playerStates.length; i++) {
            if (getPlayerNumOfCards(i) < 4) {
                return true;
            }
        }
        return false;
    }

    public void startGame() {
        Random rand = new Random();
        int firstCard = rand.nextInt(51);
        int firstPlayer = getPlayerIndexTurn(0);
        setCardToPlayer(firstCard, firstPlayer);
    }

    public void setCardToPlayer(int cardIndex, int playerIndex) {
        game.setCardId(cards[cardIndex].getId());
        cards[cardIndex].setPlayerId(playerStates[playerIndex].getId());
        cards[cardIndex].setPlayerOrder(getPlayerNumOfCards(playerIndex));
    }

    public int getPlayerNumOfCards(int playerIndex) {
        int counter = 0;
        for (Card card :
                cards) {
            if (card.getPlayerId() == playerStates[playerIndex].getId()) {
                counter++;
            }
        }
        return counter;
    }

    public int getPlayerIndexTurn(int turn) {
        for (int i = 0; i < playerStates.length; i++) {
            if (playerStates[i].getTurn() == turn) {
                return i;
            }
        }
        return -1;
    }

    public int getPlayerIndexId(long id) {
        for (int i = 0; i < playerStates.length; i++) {
            if (playerStates[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }


    // returns the index of the current player's turn
//    public int getWhichPlayer() {
////        for (int i = 1; i < playerStates.length; i++) {
////            if (getPlayerNumOfCards(i - 1) > getPlayerNumOfCards(i)) {
////                return i;
////            }
////        }
////        return 0;
//        return getPlayerIndexTurn(game.getPlayerId());
//    }

    public boolean makeChoice(int choice) {
        // check if player needs to drink
        // getting all cards in Cards where playerId = currPlayerId
        long playerId = game.getPlayerId();
        return true;
        // calculating which order we are on
        // set the next card/player
        // return true or false
    }

    public Game getGame() {
        return game;
    }
    public PlayerState[] getPlayerStates() {
        return playerStates;
    }
    public Card[] getCards() {
        return cards;
    }
}
