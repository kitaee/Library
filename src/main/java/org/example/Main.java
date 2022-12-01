package org.example;


import org.example.post.Board;

public class Main {

    static Board board = new Board();
    public static void main(String[] args) {
//        board.createBoard("haha");
//        board.readBoard("haha");
//        board.updateBoard(2L, "huhu");
        board.deleteBoard(2L);
    }
}