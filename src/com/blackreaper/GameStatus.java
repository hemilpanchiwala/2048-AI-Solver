package com.blackreaper;

public enum GameStatus {

    WIN(0, "You win!!"),

    LOSE(1, "Sorry!!!"),

    CONTINUE(2, "Nice move! Go on to win!"),

    INVALID(3, "Invalid move!");

    int code;
    String gameStatus;

    GameStatus(int code, String gameStatus) {
        this.code = code;
        this.gameStatus = gameStatus;
    }

    public String getGameStatus() {
        return gameStatus;
    }
}
