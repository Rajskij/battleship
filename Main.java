package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] field = new String[10][10];
        String[] ships = {"Aircraft Carrier (5 cells)", "Battleship (4 cells)", "Submarine (3 cells)", "Cruiser (3 cells)", "Destroyer (2 cells)"};
        String nose, tail;
        int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
        boolean correct = false;
        makeField(field);
        printField(field);

        for (String s : ships) {
            System.out.printf("Enter the coordinates of the %s:\n", s);
            int lengthOfShip = Integer.parseInt(s.replaceAll("[^\\d]", ""));

            while (!correct) {
                nose = sc.next();
                tail = sc.next();
                row1 = stringToInt(nose);
                col1 = Integer.parseInt(nose.substring(1));
                row2 = stringToInt(tail);
                col2 = Integer.parseInt(tail.substring(1));
                ValidInput validInput = new ValidInput(field, row1, col1, row2, col2, lengthOfShip);
                correct = validInput.checkIfAvailable() && validInput.checkLocation() && validInput.checkSheepLength(s);
            }
            correct = false;
            fillField(field, row1, col1, row2, col2);
            printField(field);
        }
        System.out.println("\nThe game starts!\n");
        printField(field);
        System.out.println("\nTake a shot!\n");
        String shot = sc.next();
        int rowShot = stringToInt(shot);
        int colShot = Integer.parseInt(shot.substring(1));

        while (shot.matches("[K-Z].") || colShot > 10) {
            System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            shot = sc.next();
            rowShot = stringToInt(shot);
            colShot = Integer.parseInt(shot.substring(1));
        }
        fillShot(field, rowShot, colShot);
        printField(field);
        System.out.println(field[rowShot - 1][colShot - 1].equals(" X") ? "\nYou hit a ship!" : "\nYou missed!");
    }

    public static int stringToInt(String s) {
        String val = "ABCDEFGHIJ";
        return val.indexOf(s.charAt(0)) + 1;
    }

    public static String[][] makeField(String[][] str) {
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str[0].length; j++) {
                str[i][j] = " ~";
            }
        }
        return str;
    }

    public static String[][] fillField(String[][] str, int row1, int col1, int row2, int col2) {
        for (int i = 0; i < str[0].length; i++) {
            for (int j = 0; j < str.length; j++) {
                if (j >= Math.min(col1, col2) - 1
                        && j <= Math.max(col1, col2) - 1
                        && i >= Math.min(row1, row2) - 1
                        && i <= Math.max(row1, row2) - 1) {
                    str[i][j] = " O";
                }
            }
        }
        return str;
    }
    public static String[][] fillShot(String[][] str, int rShot, int cShot) {
        for (int i = 0; i < str[0].length; i++) {
            for (int j = 0; j < str.length; j++) {
                if (j == cShot - 1 && i == rShot - 1) {
                    if (str[i][j].equals(" O")) {
                        str[i][j] = " X";
                    } else {
                        str[i][j] = " M";
                    }
                }
            }
        }
        return str;
    }

    public static void printField(String[][] str) {
        char ch = 65;
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (String[] row : str) {
            System.out.print(ch);
            for (String col : row) {
                System.out.print(col);
            }
            System.out.println();
            ch++;
        }
    }
}
