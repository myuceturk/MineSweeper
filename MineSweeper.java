package LastWork;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int rowNumber;
    int colNumber;
    int size;
    String[][] map;
    String[][] board;
    boolean game = true;

    Random rand = new Random();
    Scanner sc = new Scanner(System.in);

    MineSweeper(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.map = new String[rowNumber][colNumber];
        this.board = new String[rowNumber][colNumber];
        this.size = rowNumber * colNumber;
    }

    public void check(int row, int col) {
        if (Objects.equals(map[row][col], null)) {
            if ((col < this.colNumber - 1) && (Objects.equals(map[row][col + 1], "x"))) {
                board[row][col] = "1";
            }
            if ((row < this.rowNumber - 3) && (Objects.equals(map[row + 1][col], "x"))) {
                board[row][col] = "1";
            }
            if ((row > 0) && (Objects.equals(map[row - 1][col], "x"))) {
                board[row][col] = "1";
            }
            if ((col > 0) && (Objects.equals(map[row][col - 1], "x"))) {
                board[row][col] = "1";
            }

            if (Objects.equals(board[row][col], null)) {
                board[row][col] = "+";
            }
        }
    }

    public void load() {
        int randRow, randCol, count = 0;
        while (count != (this.size / 4)) {
            randRow = rand.nextInt(rowNumber);
            randCol = rand.nextInt(colNumber);
            if (!Objects.equals(map[randRow][randCol], "x")) {
                map[randRow][randCol] = "x";
                count++;
            }
        }
    }

    public void print(String[][] arr) {
        for (String[] strings : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                if (strings[j] == null)
                    System.out.print("-" + " ");
                else if (Objects.equals(strings[j], "x")) {
                    System.out.print("x" + " ");
                } else if (Objects.equals(strings[j], "1")) {
                    System.out.print(strings[j] + " ");
                } else if (Objects.equals(strings[j], "+")) {
                    System.out.print(strings[j] + " ");
                } else
                    System.out.print("0" + " ");
            }
            System.out.println();
        }
    }

    public void run() {
        int row, col, success = 0;
        load();
        print(map);
        System.out.println("======== MAYIN TARLASI OYUNUNA HOŞGELDİNİZ ========");
        while (game) {
            print(board);
            System.out.print("Satır Giriniz: ");
            row = sc.nextInt();
            System.out.print("Sütun Giriniz: ");
            col = sc.nextInt();

            if (row < 0 || row >= rowNumber) {
                System.out.println("Geçersiz koordinat girdiniz!");
                continue;
            }
            if (col < 0 || col >= colNumber) {
                System.out.println("Geçersiz koordinat girdiniz!");
                continue;
            }

            if (!Objects.equals(map[row][col], "x")) {
                check(row, col);
                success++;
                if (success == (size - (size / 4))) {
                    System.out.println("Tebrikler Kazandınız!");
                    game = false;
                }
            } else {
                game = false;
                System.out.println("===== Kaybettiniz :( =====");
                System.out.println("Mayınların dizilimi x ile gösterilmiştir.");
                print(map);
            }
        }
    }

}
