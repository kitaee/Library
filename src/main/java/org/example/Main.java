package org.example;


import org.example.book.Book;
import org.example.book.Borrow;
import org.example.post.Board;
import org.example.user.User;

import java.util.Scanner;

public class Main {
    static Dummy dummy = new Dummy();
    static Board board = new Board();
    static User user = new User();
    static Book book = new Book();
    static Borrow borrow = new Borrow();

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
        printMenu();
        Scanner choice = new Scanner(System.in);

        while (true){
            int c = choice.nextInt();
            switch (c){
                //책 리스트
                case 1:
                    book.selectAll();
                    break;

                //유저 데이터 출력
                case 2:
                    user.selectAll();
                    break;
                case 3:
                    borrow.selectAll();
                    break;
                //유저 더미 데이터
                case 4:
                    System.out.print("num : ");
                    int num1 = choice.nextInt();
                    dummy.createUserDummy(num1);
                    System.out.println();
                    break;
                //책 관련 더미 데이터
                case 5:
                    System.out.print("num : ");
                    int num2 = choice.nextInt();
                    dummy.createBookRelatedDummy(num2);
                    System.out.println();
                    break;
                //책 빌리기
                //빌리는중 BORROWING, 반납 RETURNED
                case 6:
                    System.out.print("user id : ");
                    Long userID = choice.nextLong();
                    choice.nextLine();
                    System.out.print("book id : ");
                    String bookID = choice.nextLine();
                    borrow.create("BORROWING", userID, bookID);
                    break;
            }
            printMenu();
        }

    }

    public static void printMenu(){
        System.out.println("1.book list");
        System.out.println("2.user list");
        System.out.println("3.borrow list");
        System.out.println("4.add dummy users");
        System.out.println("5.add dummy books");
        System.out.println("6.borrow books");
        System.out.print("choice : ");
    }
}