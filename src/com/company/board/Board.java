package com.company.board;

import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;





public class Board {



 public int m;
 public int n;
 public int[][] firstBoard;


    Thread run = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                try {
                    checkBoard(firstBoard);
                    Thread.sleep(1000); //1000 - 1 сек
                } catch (InterruptedException ex) {
                }
            }
        }
    });





    public void txtMethod() throws IOException {
        File file = new File("./src/com/company/properties");

        HashMap<String, String> prop = new HashMap<String, String>();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            String[] parts = line.replace(" ", "").split("=", 2);
            if (parts.length >= 2)
            {
                String key = parts[0];
                String value = parts[1];
                prop.put(key, value);
            } else {
                System.out.println("ignoring line: " + line);
            }
        }
        reader.close();
        m = Integer.parseInt(prop.get("m"));
        n = Integer.parseInt(prop.get("n"));
        createBoard();
        run.start();
    }




    public void consoleMethod(){
        System.out.println("Введите m");
        m = new Scanner(System.in).nextInt();
        System.out.println("Введите n");
        n = new Scanner(System.in).nextInt();
        createBoard();
        run.start();
    }




    public void createBoard() {
        int[][] board = new int[m][n];
        Random rand = new Random();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = rand.nextInt(2);
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        firstBoard = board;
    }


        public void checkBoard( int[][] board){
            int[][] tempBoard = new int[m][n];



            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int temp =
                            (i == 0 && j == 0) ? board[i][j+1] + board[i+1][j] + board[i+1][j+1] :
                                    (i == m-1 && j == n-1) ? board[i][j-1] + board[i-1][j] + board[i-1][j-1] :

                                            (i == 0 && j == n-1) ? board[i][j-1] + board[i+1][j] + board[i+1][j-1] :
                                                    (i == m-1 && j == 0) ? board[i][j+1] + board[i-1][j] + board[i-1][j+1] :

                                                            (i == 0) ? board[i][j+1] + board[i][j-1] + board[i+1][j+1] + board[i+1][j] + board[i+1][j-1] :
                                                                    (i == m-1) ? board[i][j+1] + board[i][j-1] + board[i-1][j+1] + board[i-1][j] + board[i-1][j-1] :

                                                                            (j == 0) ? board[i+1][j] + board[i-1][j] + board[i-1][j+1] + board[i][j+1] + board[i+1][j+1] :
                                                                                    (j == n-1) ? board[i+1][j] + board[i-1][j] + board[i-1][j-1] + board[i][j-1] + board[i+1][j-1] :

                                                                                            board[i-1][j-1] + board[i-1][j] + board[i-1][j+1] + board[i][j+1] + board[i+1][j+1] + board[i+1][j] + board[i+1][j-1] + board[i][j-1];


                    if(board[i][j] == 0){
                        tempBoard[i][j] = (temp == 3) ? 1 : 0;
                    }else{
                        switch (temp){
                            case 2:
                            case 3:
                                tempBoard[i][j] = 1;
                                break;
                            default:
                                tempBoard[i][j] = 0;
                                break;
                        }
                    }


                }
            }
            System.out.println("Обновленная доска");
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(tempBoard[i][j] + "\t");
                }
                System.out.println();
            }
            firstBoard = tempBoard;
        }
    }

