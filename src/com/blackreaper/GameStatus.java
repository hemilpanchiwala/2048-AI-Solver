package com.blackreaper;

public enum GameStatus {

    WIN(0, "You win!!"),

    LOSE(1, "Sorry!!!"),

    CONTINUE(2, "Nice move! Go on to win!"),

    INVALID(3, "Invalid move!");

    int code;
    String gameStatus;

    GameStatus(int code, String gameStatus){
        this.code = code;
        this.gameStatus = gameStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
}
