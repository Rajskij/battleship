package battleship;

import java.util.Scanner;

public class BattleField {
    public static void makeShot(String[][] field, String[][] fogField, int player) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("\nPlayer %s, it's your turn:\n", player);

        String shot = sc.next();
        int rowShot = Model.stringToInt(shot) - 1;
        int colShot = Integer.parseInt(shot.substring(1)) - 1;

        while (shot.matches("[K-Z].") || colShot > 10) {
            System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            shot = sc.next();
            rowShot = Model.stringToInt(shot) - 1;
            colShot = Integer.parseInt(shot.substring(1)) - 1;
        }
        if (field[rowShot][colShot].equals(" O")) {
            fogField[rowShot][colShot] = " X";
            field[rowShot][colShot] = " X";
        } else if (field[rowShot][colShot].equals(" ~")) {
            fogField[rowShot][colShot] = " M";
        }
        if (checkIfSank(field, rowShot, colShot) && fogField[rowShot][colShot].equals(" X")) {
            System.out.println("You sank a ship! Specify a new target:");
            Model.promptEnterKey();
        } else if (!Main.checkIfAlive(field)){
            System.out.println("You sank the last ship. You won. Congratulations!");
        } else {
            System.out.println(fogField[rowShot][colShot].equals(" X") ? "\nYou hit a ship! Try again:\n"
                    : "\nYou missed! Try again:\n");
            Model.promptEnterKey();
        }
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
}
