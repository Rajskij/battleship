package battleship;

public class ValidInput {
    private String[][] str;
    private int lengthOfShip;
    private int row1;
    private int col1;
    private int row2;
    private int col2;

    public ValidInput(String[][] str, int row1, int col1, int row2, int col2, int lengthOfShip) {
        this.lengthOfShip = lengthOfShip;
        this.str = str;
        this.row1 = row1;
        this.col1 = col1;
        this.row2 = row2;
        this.col2 = col2;
    }
    public boolean checkSheepLength(String s) {
        boolean answer = true;
        if (row1 == row2 && Math.max(col1,col2) - Math.min(col1,col2) != lengthOfShip - 1
                || col1 == col2 && Math.max(row1,row2) - Math.min(row1,row2) != lengthOfShip - 1) {
            System.out.printf("Error! Wrong length of the %s! Try again:\n", s.replaceAll(" \\(\\b.+", ""));
            answer = false;
        }
        return answer;
    }
    public boolean checkLocation() {
        boolean answer = true;
        if (row1 != row2 && col1 != col2) {
            System.out.println("Error! Wrong ship location! Try again:");
            answer = false;
        }
        return answer;
    }
    public boolean checkIfAvailable() {
        boolean answer = true;
        for (int i = 0; i < str.length && answer; i++) {
            for (int j = 0; j < str[0].length && answer; j++) {
                if (answer && j >= Math.min(col1, col2) - 1 && j <= Math.max(col1, col2) - 1
                        && i >= Math.min(row1, row2) - 1 && i <= Math.max(row1, row2) - 1) {

                    int n = str.length;
                    int down = i == n - 1 ? i : (i + 1) % n;    //if "i" or "j" is bigger then
                    int up = i == 0 ? i : (i + n - 1) % n;      //array length, we left the count on the edge
                    int right = j == n - 1 ? j : (j + 1) % n;
                    int left = j == 0 ? j : (j + n - 1) % n;

                    if (str[i][right].equals(" O") || str[i][left].equals(" O") //horizontal check
                            || str[up][j].equals(" O") || str[down][j].equals(" O") //vertical check
                            || str[down][right].equals(" O") || str[down][left].equals(" O") //diagonal top
                            || str[up][right].equals(" O") || str[up][left].equals(" O") ) { //diagonal bottom
                        answer = false;
                        System.out.println("Error! You placed it too close to another one. Try again:");
                    }
                }
            }
        }
        return answer;
    }
}
