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
        String[][] fogField = new String[10][10];
        makeField(fogField);
        printField(fogField);
        System.out.println("\nTake a shot!\n");
        while (checkIfAlive(field)) {
            String shot = sc.next();
            int rowShot = stringToInt(shot) - 1;
            int colShot = Integer.parseInt(shot.substring(1)) - 1;

            while (shot.matches("[K-Z].") || colShot > 10) {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
                shot = sc.next();
                rowShot = stringToInt(shot) - 1;
                colShot = Integer.parseInt(shot.substring(1)) - 1;
            }
            if (field[rowShot][colShot].equals(" O")) {
                fogField[rowShot][colShot] = " X";
                field[rowShot][colShot] = " X";
            } else if (field[rowShot][colShot].equals(" ~")) {
                fogField[rowShot][colShot] = " M";
            }
            printField(fogField);
            if (checkIfSank(field, rowShot, colShot) && fogField[rowShot][colShot].equals(" X")) {
                System.out.println("You sank a ship! Specify a new target:");
            } else {
                System.out.println(fogField[rowShot][colShot].equals(" X") ? "\nYou hit a ship! Try again:\n"
                        : "\nYou missed! Try again:\n");
            }
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }
    public static boolean checkIfSank(String[][] field, int rs, int cs) {
        boolean isSank = true;
        int n = field.length - 1;
        int up = rs == n ? n : rs + 1;
        int down = rs == 0 ? 0 : rs - 1;
        int right = cs == n ? n : cs + 1;
        int left = cs == 0 ? 0 : cs - 1;

        if (field[rs][right].equals(" O")
                || field[rs][left].equals(" O")
                || field[up][cs].equals(" O")
                || field[down][cs].equals(" O")) {
            isSank = false;
        }
        return isSank;
    }
    public static boolean checkIfAlive(String[][] field) {
        boolean isAlive = false;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j].equals(" O")) {
                    isAlive = true;
                    break;
                }
            }
        }
        return isAlive;
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