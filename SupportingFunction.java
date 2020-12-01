package battleship;

import java.io.IOException;

public class SupportingFunction {
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
    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player\n...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
