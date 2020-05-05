package com.blackreaper;

import java.util.List;
import java.util.Random;

public class Board {

    public static final int boardSize = 4;

    public int target = 2048;

    public int minWinScore = 18432;

    Random randomValue;

    private int[][] board;

    private int currentScore = 0;

    Board() {
        board = new int[boardSize][boardSize];
        randomValue = new Random();

        addRandomCell();
        addRandomCell();
    }

    private boolean addRandomCell() {

        List<Integer> emptyCells = null;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == 0) {
                    assert false;
                    emptyCells.add(i * boardSize + j);
                }
            }
        }

        assert false;
        int size = emptyCells.size();

        if (size == 0) {
            return false;
        }

        int randomPosValue = emptyCells.get(randomValue.nextInt(size));
        int random = (randomValue.nextDouble() < 0.9) ? 2 : 4;

        int i = randomPosValue / boardSize;
        int j = randomPosValue % boardSize;

        if (board[i][j] != 0) {
            board[i][j] = random;
        }

        return true;

    }

    private void rotateClockwise() {

        int[][] rotatedArray = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                rotatedArray[i][j] = board[boardSize - 1 - j][i];
            }
        }

        board = rotatedArray;
    }

    private void rotateAntiClockwise() {

        int[][] rotatedArray = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                rotatedArray[i][j] = board[j][boardSize - 1 - i];
            }
        }

        board = rotatedArray;
    }

    public int move(Direction direction) {

        if (direction == Direction.DOWN) {
            rotateClockwise();
        }

        if (direction == Direction.RIGHT) {
            rotateClockwise();
            rotateClockwise();
        }

        if (direction == Direction.UP) {
            rotateAntiClockwise();
        }

        int points = solveLeftMove();
        currentScore += points;

        if (direction == Direction.DOWN) {
            rotateAntiClockwise();
        }

        if (direction == Direction.RIGHT) {
            rotateAntiClockwise();
            rotateAntiClockwise();
        }

        if (direction == Direction.UP) {
            rotateClockwise();
        }

        return points;
    }

    private int solveLeftMove() {
        int points = 0;
        for (int i = 0; i < boardSize; i++) {
            int lastMergePos = 0;
            for (int j = 1; j < boardSize; j++) {

                if (board[i][j] == 0) {
                    continue;
                }

                int previousPos = j - 1;
                while ((previousPos > lastMergePos) && (board[i][previousPos] == 0)) {
                    previousPos--;
                }

                if (board[i][previousPos] == 0) {
                    board[i][previousPos] = board[i][j];
                    board[i][j] = 0;
                } else if (board[i][previousPos] == board[i][j]) {
                    board[i][previousPos] *= 2;
                    board[i][j] = 0;
                    points += board[i][previousPos];
                    lastMergePos = previousPos + 1;
                } else if ((board[i][previousPos] != board[i][j]) && ((previousPos + 1) != j)) {
                    board[i][previousPos + 1] = board[i][j];
                    board[i][j] = 0;
                }

            }
        }

        return points;
    }

    public boolean hasWon() {

        if (currentScore < minWinScore) {
            return false;
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == target) {
                    return true;
                }
            }
        }

        return false;

    }

    public List<Integer> getEmptyCells() {

        List<Integer> emptyCellsList = null;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == 0) {
                    emptyCellsList.add(i * boardSize + j);
                }
            }
        }

        return emptyCellsList;

    }

    public int getNoOfEmptyCells() {

        int totalEmptyCells = 0;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == 0) {
                    totalEmptyCells++;
                }
            }
        }

        return totalEmptyCells;

    }

    public int[][] getBoard() {
        return board;
    }

    public void setEmptyValue(int i, int j, int value) {
        if (board[i][j] == 0) {
            board[i][j] = value;
        }
    }

    public boolean isGameOver() {

        boolean terminated = false;

        if (hasWon()) {
            terminated = true;
        } else {
            if (getNoOfEmptyCells() == 0) {
                Board clonedBoard = new Board();

                int[][] clone = new int[boardSize][boardSize];
                for (int i = 0; i < boardSize; i++) {
                    System.arraycopy(board[i], 0, clone[i], 0, boardSize);
                }

                clonedBoard.board = clone;

                if ((clonedBoard.move(Direction.UP) == 0) && (clonedBoard.move(Direction.DOWN) == 0)
                        && (clonedBoard.move(Direction.LEFT) == 0) && (clonedBoard.move(Direction.RIGHT) == 0)) {
                    terminated = true;
                }
            }
        }

        return terminated;
    }

    public boolean isEqual(int[][] first, int[][] second) {

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (first[i][j] != second[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public GameStatus takeAction(Direction direction) {

        GameStatus gameStatus = GameStatus.CONTINUE;

        int[][] beforeMoveBoard = board;
        int points = move(direction);
        int[][] afterMoveBoard = board;

        boolean isnewCellAdded = false;
        if (!isEqual(beforeMoveBoard, afterMoveBoard)) {
            isnewCellAdded = addRandomCell();
        }

        if (points == 0 && !isnewCellAdded) {
            if (isGameOver()) {
                gameStatus = GameStatus.LOSE;
            } else {
                gameStatus = GameStatus.INVALID;
            }
        } else {
            if (points >= target) {
                gameStatus = GameStatus.WIN;
            } else {
                if (isGameOver()) {
                    gameStatus = GameStatus.LOSE;
                }
            }
        }

        return gameStatus;

    }


}
