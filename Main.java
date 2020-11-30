package battleship;

public class Main {
    public static void main(String[] args) {
        String[][] player1Field = new String[10][10];
        String[][] player2Field = new String[10][10];

        String[][] player1FogField = new String[10][10];
        String[][] player2FogField = new String[10][10];

        MakeField.CreateField(player1Field, 1);
        MakeField.CreateField(player2Field, 2);

        Model.makeField(player1FogField);
        Model.makeField(player2FogField);

        while (true) {
            if (!checkIfAlive(player1Field)) {
                break;
            } else {
                Model.printField(player1FogField);
                System.out.println("---------------------");
                Model.printField(player1Field);
                BattleField.makeShot(player2Field, player1FogField, 1);
            }
            if (!checkIfAlive(player2Field)) {
                break;
            } else {
                Model.printField(player2FogField);
                System.out.println("---------------------");
                Model.printField(player2Field);
                BattleField.makeShot(player1Field, player2FogField, 2);
            }
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
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
}