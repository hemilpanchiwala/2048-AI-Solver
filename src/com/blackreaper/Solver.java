package com.blackreaper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solver {

    public enum Player {
        COMPUTER,
        HUMAN
    }

    public static Direction findBestMove(Board board, int depth) throws CloneNotSupportedException {

        Map<String, Object> result = alphaBetaPruning(board, depth, Player.HUMAN, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return (Direction) result.get("direction");

    }

    private static int heuristicScore(int actualScore, int clusteringScore) {

        int score = actualScore - clusteringScore;
        int actScore = Math.min(actualScore, 1);
        return Math.max(actScore, score);

    }

    private static Map<String, Object> alphaBetaPruning(Board board, int depth, Player player, int alpha, int beta) throws CloneNotSupportedException {

        Map<String, Object> result = new HashMap<>();

        Direction bestPossibleDirection = null;
        int bestPossibleScore;

        if (board.isGameOver()) {

            if (board.hasWon()) {
                bestPossibleScore = Integer.MAX_VALUE;
            } else {
                bestPossibleScore = Math.min(board.getCurrentScore(), 1);
            }

        } else if (depth == 0) {

            bestPossibleScore = heuristicScore(board.getWeightedScore(), getClusteringScore(board.getBoard()));

        } else {
            if (player == Player.HUMAN) {
                for (Direction direction : Direction.values()) {

                    Board tempBoard = (Board) board.clone();

                    int points = tempBoard.move(direction);

                    if (points == 0 && tempBoard.isEqual(board.getBoard(), tempBoard.getBoard())) {
                        continue;
                    }

                    Map<String, Object> currResult = alphaBetaPruning(tempBoard, depth - 1, Player.COMPUTER, alpha, beta);
                    int currScore = ((Number) currResult.get("score")).intValue();

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
                for (Integer id : possibleMoves) {
                    int row = id / Board.boardSize;
                    int column = id % Board.boardSize;

                    for (int possibleValue : possibleValues) {
                        Board tempBoard = (Board) board.clone();
                        tempBoard.setEmptyValue(row, column, possibleValue);

                        Map<String, Object> currResult = alphaBetaPruning(tempBoard, depth - 1, Player.HUMAN, alpha, beta);
                        int currScore = ((Number) currResult.get("score")).intValue();

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
        result.put("direction", bestPossibleDirection);

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
                    if (p < 0 || p >= board.length) {
                        continue;
                    }

                    for (int l = -1; l <= 1; l++) {
                        int q = j + l;
                        if (q < 0 || q >= board[i].length) {
                            continue;
                        }

                        if (board[p][q] > 0) {
                            totalNeighbours++;
                            tempScore += (Math.abs(board[i][j] - board[p][q]));
                        }

                    }
                }
                clusteringScore += (tempScore / totalNeighbours);
            }
        }
        return clusteringScore;
    }

}
