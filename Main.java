package battleship;

public class Main {
    public static void main(String[] args) {
        String[][] player1Field = new String[10][10];
        String[][] player2Field = new String[10][10];

        String[][] player1FogField = new String[10][10];
        String[][] player2FogField = new String[10][10];

        MakeField.CreateField(player1Field, 1);
        MakeField.CreateField(player2Field, 2);

        SupportingFunction.makeField(player1FogField);
        SupportingFunction.makeField(player2FogField);

        while (true) {
            if (!SupportingFunction.checkIfAlive(player1Field)) {
                break;
            } else {
                SupportingFunction.printField(player1FogField);
                System.out.println("---------------------");
                SupportingFunction.printField(player1Field);
                BattleField.makeShot(player2Field, player1FogField, 1);
            }
            if (!SupportingFunction.checkIfAlive(player2Field)) {
                break;
            } else {
                SupportingFunction.printField(player2FogField);
                System.out.println("---------------------");
                SupportingFunction.printField(player2Field);
                BattleField.makeShot(player1Field, player2FogField, 2);
            }
        }
    }
}