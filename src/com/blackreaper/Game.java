package com.blackreaper;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        System.out.println("The great 2048 AI Solver");
        System.out.println("------------------------");
        System.out.println();
        while (true) {
            showMenu();
            int option;
            try {
                Scanner sc = new Scanner(System.in);
                option = sc.nextInt();
                switch (option) {
                    case 1:
                        play();
                        break;
                    case 2:
                        getAccuracy();
                        break;
                    case 3:
                        return;
                    default:
                        throw new Exception();

                }
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Enter valid option!!!");
            }
        }
    }

    public static void showMenu() {
        System.out.println("Welcome to AI-2048 Solver");
        System.out.println("Here are the options:");
        System.out.println("1. Play the game");
        System.out.println("2. Get accuracy of the solver");
        System.out.println("3. Quit");
        System.out.println();
        System.out.println("Enter here\n");
    }

    public static void getAccuracy() throws CloneNotSupportedException {

        int noOfWins = 0;
        int totalTestGames = 10;

        System.out.println("Running " + totalTestGames + " games");

        for (int i = 0; i < totalTestGames; i++) {

            int depth = 7;
            Board gameBoard = new Board();
            Direction bestDirection = Solver.findBestMove(gameBoard, depth);
            GameStatus gameStatus = GameStatus.CONTINUE;

            while (gameStatus == GameStatus.CONTINUE || gameStatus == GameStatus.INVALID) {

                gameStatus = gameBoard.takeAction(bestDirection);

                if (gameStatus == GameStatus.CONTINUE || gameStatus == GameStatus.INVALID) {

                    bestDirection = Solver.findBestMove(gameBoard, depth);

                }
            }

            if (gameStatus == GameStatus.WIN) {
                noOfWins++;
                System.out.println("Game " + (i + 1) + ": WON");
            } else {
                System.out.println("Game " + (i + 1) + ": LOST");
            }

        }

        System.out.println(noOfWins + " games won from " + totalTestGames + " games");
        System.out.println("Accuracy: " + ((noOfWins * 1.) / totalTestGames));

    }

    public static void play() throws CloneNotSupportedException {

        System.out.println("Play 2048 Game");
        System.out.println("Use W for UP");
        System.out.println("Use S for DOWN");
        System.out.println("Use A for LEFT");
        System.out.println("Use D for RIGHT");

        int depth = 7;
        Board gameBoard = new Board();
        Direction bestDirection = Solver.findBestMove(gameBoard, depth);
        printCurrentBoard(gameBoard.getBoard(), gameBoard.getCurrentScore(), bestDirection);

        try {
            Scanner sc = new Scanner(System.in);
            char input;

            GameStatus gameStatus = GameStatus.CONTINUE;
            while (gameStatus == GameStatus.CONTINUE || gameStatus == GameStatus.INVALID) {

                input = sc.next().charAt(0);

                if(input == '\n' || input == '\r') {
                    continue;
                }else if (input == 'W') {
                    gameStatus = gameBoard.takeAction(Direction.UP);
                } else if (input == 'S') {
                    gameStatus = gameBoard.takeAction(Direction.DOWN);
                } else if (input == 'A') {
                    gameStatus = gameBoard.takeAction(Direction.LEFT);
                } else if (input == 'D') {
                    gameStatus = gameBoard.takeAction(Direction.RIGHT);
                } else if (input == 'h') {
                    gameStatus = gameBoard.takeAction(bestDirection);
                } else if (input == 'q') {
                    System.out.println("Game Over!!!");
                    break;
                } else {
                    System.out.println("INVALID!!!");
                    System.out.println("Use W for UP");
                    System.out.println("Use S for DOWN");
                    System.out.println("Use A for LEFT");
                    System.out.println("Use D for RIGHT");
                    System.out.println("Use SPACE for autoplay");
                    System.out.println("Use q to quit the game");
                    continue;
                }

                if (gameStatus == GameStatus.CONTINUE || gameStatus == GameStatus.INVALID) {
                    bestDirection = Solver.findBestMove(gameBoard, depth);
                } else {
                    bestDirection = null;
                }

                printCurrentBoard(gameBoard.getBoard(), gameBoard.getCurrentScore(), bestDirection);

                if (gameStatus != GameStatus.CONTINUE) {
                    System.out.println(gameStatus.getGameStatus());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void printCurrentBoard(int[][] boardArray, int score, Direction direction) {

        System.out.println("------------------------");
        System.out.println("Score: " + score);
        System.out.println("Best direction: " + direction);

        for (int[] ints : boardArray) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        System.out.println("------------------------");
    }
}
