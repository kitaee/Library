package org.example;


import org.example.post.Board;

import java.util.Scanner;

public class Main {
    static Dummy dummy = new Dummy();
    static Board board = new Board();

    public static void main(String[] args) {
        System.out.println("\n" +
                " _      _____ ______ ______   ___  ______ __   __\n" +
                "| |    |_   _|| ___ \\| ___ \\ / _ \\ | ___ \\\\ \\ / /\n" +
                "| |      | |  | |_/ /| |_/ // /_\\ \\| |_/ / \\ V / \n" +
                "| |      | |  | ___ \\|    / |  _  ||    /   \\ /  \n" +
                "| |____ _| |_ | |_/ /| |\\ \\ | | | || |\\ \\   | |  \n" +
                "\\_____/ \\___/ \\____/ \\_| \\_|\\_| |_/\\_| \\_|  \\_/  \n" +
                "                                                 \n" +
                "                                                 \n");
        Scanner choice = new Scanner(System.in);

        while (true){
            choice.nextInt();
        }
    }

    public static void printMenu(){
        System.out.println(1.);
        System.out.println(2.);
        System.out.println(3.);
        System.out.println(4.);
    }
}