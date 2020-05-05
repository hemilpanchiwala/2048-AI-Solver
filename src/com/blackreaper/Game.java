package com.blackreaper;

import java.util.Scanner;

public class Game {

    public static void showMenu() {
        System.out.println("Welcome to AI-2048 Solver");
        System.out.println("Here are the options:");
        System.out.println("1. Play the game");
        System.out.println("2. Get accuracy of the solver");
        System.out.println("3. Quit");
        System.out.println();
        System.out.println("Enter here\n");
    }

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
                switch (option){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;

                }
            }catch (Exception e){
                System.out.println("Enter valid option!!!");
            }
        }
    }

    public static void play() {

        System.out.println("Play 2048 Game");
        System.out.println("Use W for UP");
        System.out.println("Use S for DOWN");
        System.out.println("Use A for LEFT");
        System.out.println("Use D for RIGHT");

        Board gameBoard = new Board();

    }

}
