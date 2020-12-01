package battleship;

import java.util.Scanner;

public class MakeField {
    public static String[][] CreateField(String[][] field, int player) {
        Scanner sc = new Scanner(System.in);
        String[] ships = {"Aircraft Carrier (5 cells)", "Battleship (4 cells)", "Submarine (3 cells)", "Cruiser (3 cells)", "Destroyer (2 cells)"};
        String nose, tail;
        int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
        boolean correct = false;

        SupportingFunction.makeField(field);
        System.out.printf("Player %s, place your ships on the game field\n\n", player);
        SupportingFunction.printField(field);

        for (String s : ships) {
            System.out.printf("\nEnter the coordinates of the %s:\n", s);
            int lengthOfShip = Integer.parseInt(s.replaceAll("[^\\d]", ""));

            while (!correct) {
                nose = sc.next();
                tail = sc.next();
                row1 = SupportingFunction.stringToInt(nose);
                col1 = Integer.parseInt(nose.substring(1));
                row2 = SupportingFunction.stringToInt(tail);
                col2 = Integer.parseInt(tail.substring(1));
                ValidInput validInput = new ValidInput(field, row1, col1, row2, col2, lengthOfShip);
                correct = validInput.checkIfAvailable() && validInput.checkLocation() && validInput.checkSheepLength(s);
            }
            correct = false;
            fillField(field, row1, col1, row2, col2);
            SupportingFunction.printField(field);
        }
        SupportingFunction.promptEnterKey();
        return field;
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
}
