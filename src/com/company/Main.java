package com.company;

import com.company.board.Board;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Board board = new Board();
        System.out.println("Выберите метод ввода данный: 1 - console или 2 - txt");
        int method = new Scanner(System.in).nextInt();
        if (method == 2){
            board.txtMethod();
        }else if(method == 1){
            board.consoleMethod();
        }else{
            System.out.println("Проверьте корректность ввода");
            return;
        }










    }
}
