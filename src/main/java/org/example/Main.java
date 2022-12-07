package org.example;


import org.example.book.Book;
import org.example.book.Borrow;
import org.example.post.Board;
import org.example.teamRoom.TeamRoomReservation;
import org.example.user.User;
import org.example.user.VisitRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static Dummy dummy = new Dummy();
    static Board board = new Board();
    static User user = new User();
    static Book book = new Book();
    static Borrow borrow = new Borrow();
    static VisitRecord visitRecord = new VisitRecord();
    static TeamRoomReservation teamRoomReservation = new TeamRoomReservation();
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
                //도서관 들어오기
                case 1:
                    System.out.print("user id : ");
                    Long borrowID = choice.nextLong();
                    choice.nextLine();
                    visitRecord.enter(getToday(), borrowID);
                    break;
                //도서관 나가기
                case 2:
                    System.out.print("user id : ");
                    borrowID = choice.nextLong();
                    choice.nextLine();
                    visitRecord.exit(getToday(), borrowID);
                    break;
                //출입 기록
                case 3:
                    visitRecord.selectAll();
                    break;
                //책 리스트
                case 4:
                    book.selectAll();
                    break;

                //유저 데이터 출력
                case 5:
                    user.selectAll();
                    break;
                //책 빌리기
                //빌리는중 BORROWING, 반납 RETURNED
                case 6:
                    Date borrowDay = new Date();
                    Date returnDay = addDays(borrowDay, 7);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    System.out.print("user id : ");
                    Long userID = choice.nextLong();
                    choice.nextLine();
                    System.out.print("book id : ");
                    String bookID = choice.nextLine();
                    borrow.create("BORROW", userID, bookID);
                    break;
                //책 대여 기록 출력
                case 7:
                    borrow.selectAll();
                    break;
                //대여 연장
                case 8:
                    System.out.print("borrow id : ");
                    borrowID = choice.nextLong();
                    choice.nextLine();
                    borrow.extendReturnDate(borrowID);
                    break;
                //대여 연장 기록
                case 9:
                    borrow.selectAllExtendList();
                    break;
                //팀플실 예약
                case 10:
                    System.out.print("team room id : ");
                    Long teamRoomId = choice.nextLong();
                    choice.nextLine();
                    System.out.println("start time : ");
                    String startTime = choice.nextLine();
                    System.out.println("end time : ");
                    String endTime = choice.nextLine();
                    System.out.println("people number : ");
                    int peopleNum = choice.nextInt();
                    choice.nextLine();
                    System.out.println("user id : ");
                    Long userId = choice.nextLong();
                    choice.nextLine();
                    teamRoomReservation.create(startTime,endTime,peopleNum,teamRoomId,userId);
                    break;
                //팀플실 리스트
                case 11:
                    teamRoomReservation.selectAll();
                    break;
                //유저 더미 데이터
                case 20:
                    System.out.print("num : ");
                    int num1 = choice.nextInt();
                    dummy.createUserDummy(num1);
                    System.out.println();
                    break;
                //책 관련 더미 데이터
                case 21:
                    System.out.print("num : ");
                    int num2 = choice.nextInt();
                    dummy.createBookRelatedDummy(num2);
                    System.out.println();
                    break;
                //요금 확인 데이터
                case 30:
                    System.out.print("borrow id : ");
                    Long borrowId= choice.nextLong();
                    borrow.checkFee(borrowId);
                //Monthly Best
                case 31:
                    System.out.print("When month : ");
                    int month = choice.nextInt();
                    book.monthlyBest(month);
                case 100:
                    dummy.truncateAllTable();
                    break;
            }
            printMenu();
        }

    }

    public static void printMenu(){
        System.out.println("1.enter library");
        System.out.println("2.exit library");
        System.out.println("3.visit list");
        System.out.println("4.book list");
        System.out.println("5.user list");
        System.out.println("6.borrow books");
        System.out.println("7.borrow list");
        System.out.println("8.extend return date");
        System.out.println("9.extend list");

        System.out.println("10. team room reservation");
        System.out.println("20.add dummy users");
        System.out.println("21.add dummy books");
        System.out.print("choice : ");
    }
    public static String getToday()
    {
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return dateFormat.format(today);
    }

    public static Date addDays(Date d, int days)
    {
        d.setTime(d.getTime() + (long) days * 1000 * 60 * 60 * 24);
        return d;
    }
}