/**
 * Java1 Home work 4
 *
 * @author Anna Velichko
 * @version 31.10.2021
 */

import java.util.Random;
import java.util.Scanner;

public class HomeWork4 {
    public static final int SIZE = 5;
    final char DOT_X = 'X';
    final char DOT_O = '0';
    final char DOT_EMPTY = '.';
    char[][] map = new char[SIZE][SIZE];
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public static void main(String[] args) {
        new HomeWork4().go();
    }

    void go() {
        initMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Поздравляю, ты победил!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }
            PCTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Выиграл компьютер!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }
        }
        System.out.println("Игра окончена.");
    }

    void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    void humanTurn() {
        int x, y;
        do {
            System.out.println("Выбрать клетку для хода - ввести строку и столбец:");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    void PCTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (isCellValid(x, y));
        map[y][x] = DOT_O;
    }

    void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    boolean checkWin(char dot) {
        int diag1, diag2, hor, ver;
        for (int i = 0; i < SIZE; i++) {
            hor = 0;
            ver = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == dot) {
                    hor++;
                }
                if (map[j][i] == dot) {
                    ver++;
                }
            }
            if (hor == 4 || ver == 4) {
                return true; //
            }
        }
        diag1 = 0;
        diag2 = 0;
        for (int i = 0; i < 4; i++) {
            if (map[i][i] == dot) {
                diag1++;
            }
            if (map[i][4 - i - 1] == dot) {
                diag2++;
            }
        }
        return diag1 == 4 || diag2 == 4;
    }

    boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;

    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) return true;
        return map[y][x] != DOT_EMPTY;
    }
}


