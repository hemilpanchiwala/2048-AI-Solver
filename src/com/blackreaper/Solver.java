package com.blackreaper;

import java.util.List;
import java.util.Map;

public class Solver {

    public enum Player {
        COMPUTER,
        HUMAN
    }

    public static Direction findBestMove(Board board, int depth) {

        Map<String, Object> result = alphaBetaPruning(board, depth, Player.HUMAN, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return (Direction) result.get("direction");

    }

    private static int heuristicScore(int actualScore, int noOfEmptyCells, int clusteringScore) {

        int score = actualScore + (int) Math.log(actualScore) * noOfEmptyCells - clusteringScore;
        int actScore = Math.min(actualScore, 1);
        return Math.max(score, actScore);

    }

    private static Map<String, Object> alphaBetaPruning(Board board, int depth, Player player, int alpha, int beta) {

        Map<String, Object> result = null;

        Direction bestPossibleDirection = null;
        int bestPossibleScore = 0;

        if (board.isGameOver()) {

            if (board.hasWon()) {
                bestPossibleScore = Integer.MAX_VALUE;
            } else {
                bestPossibleScore = Math.min(board.getCurrentScore(), 1);
            }

        } else if (depth == 0) {

            bestPossibleScore = heuristicScore(board.getCurrentScore(), board.getNoOfEmptyCells(), getClusteringScore(board.getBoard()));

        } else {
            if (player == Player.HUMAN) {
                for (Direction direction : Direction.values()) {

                    Board tempBoard = new Board();

                    int points = tempBoard.move(direction);

                    if (points == 0 && tempBoard.isEqual(board.getBoard(), tempBoard.getBoard())) {

                        continue;

                    }

                    Map<String, Object> currResult = alphaBetaPruning(tempBoard, depth - 1, Player.COMPUTER, alpha, beta);
                    int currScore = (int) currResult.get("score");

                    if (currScore > alpha) {
                        alpha = currScore;
                        bestPossibleDirection = direction;
                    }

                    if (beta <= alpha) {
                        break;
                    }

                }
                bestPossibleScore = alpha;

            } else {

                List<Integer> possibleMoves = board.getEmptyCells();
                int[] possibleValues = {2, 4};

                loop:
                for (int id = 0; id < possibleMoves.size(); id++) {
                    int row = id / Board.boardSize;
                    int column = id % Board.boardSize;

                    for (int possibleValue : possibleValues) {
                        Board tempBoard = new Board();
                        tempBoard.setEmptyValue(row, column, possibleValue);

                        Map<String, Object> currResult = alphaBetaPruning(tempBoard, depth - 1, Player.HUMAN, alpha, beta);
                        int currScore = (int) currResult.get("score");

                        if (currScore < beta) {
                            beta = currScore;
                        }

                        if (beta <= alpha) {
                            break loop;
                        }

                    }
                }

                bestPossibleScore = beta;

                if (possibleMoves.isEmpty()) {
                    bestPossibleScore = 0;
                }
            }
        }

        result.put("score", bestPossibleScore);
        result.put("directtion", bestPossibleDirection);

        return result;
    }

    private static int getClusteringScore(int[][] board) {

        int clusteringScore = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    continue;
                }

                int tempScore = 0;
                int totalNeighbours = 0;

                for (int k = -1; k <= 1; k++) {
                    int p = i + k;
                    if (p < 0 || p > board.length) {
                        continue;
                    }

                    for (int l = -1; l <= 1; l++) {
                        int q = j + l;
                        if (q < 0 || q > board[i].length) {
                            continue;
                        }

                        if (k != 0 && l != 0) {
                            if (board[p][q] > 0) {
                                totalNeighbours++;
                                tempScore += (Math.abs(board[i][j] - board[p][q]));
                            }
                        }
                    }
                }
                clusteringScore += (tempScore / totalNeighbours);
            }
        }
        return clusteringScore;
    }

}
