//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Random;
import java.util.Scanner;
public class Main {
    private int[][] board = new int[7][6];

    public void runGame(){
        Scanner scanner = new Scanner(System.in);
        int counter = getPlayer();
        boolean gameContinues = true;
        while (gameContinues) {
            int[][] board = getGameBoard();
            int move = getMove(counter);
            if (move == 10) {
                System.out.println("Invalid move, try again");
            } else {
                int row = getRow(move , board);
                if (row == 10) {
                    System.out.println("Invalid move");
                } else {
                    board[move][row] = counter;
                    setGameBoard(board);
                    printBoard(getGameBoard());
                    gameContinues = checkWin(move, row, counter, board);
                    if (gameContinues) {
                        checkDiagonal(move, row, counter, board);
                    }
                    if (counter == 1) {
                        counter = 2;
                    } else {
                        counter = 1;
                    }
                }
            }
        }
    }
    private int getPlayer () {
        int counter;
        Random rand = new Random();
        if (rand.nextInt(2) == 0) {
            counter = 1;
        } else {
            counter = 2;
        }
        return counter;
    }
    private int[][] getGameBoard () {
        return board;
    }
    private void setGameBoard ( int[][] board){
        this.board = board;
    }
    private int getMove ( int counter){
        int move;
        Scanner scanner = new Scanner(System.in);
        System.out.println("It's player " + counter + "'s turn.");
        System.out.println("Please enter the column number (from 0 to 6) : ");
        try {
            move = Integer.parseInt(scanner.next());
            if (move > 6 || move < 0) {
                return 10;
            } else {
                return move;
            }
        } catch (Exception e) {
            return 10;
        }
    }
    private int getRow ( int column, int[][] board){
        int row;
        for (int y = 0; y < 6; y++) {
            if (board[column][y] == 0) {
                row = y;
                return row;
            }
        }
        row = 10;
        return row;
    }
    private void printBoard ( int[][] board){
        for (int y = 5; y >= 0; y--) {
            System.out.println();
            for (int x = 0; x < 7; x++) {
                System.out.print(board[x][y] + " ");
            }
        }
        System.out.println();
    }
    private boolean checkWin ( int moveX, int moveY, int counter, int[][] board){
        int inline = 0;
        for (int y = 0; y < 6; y++) {
            if (board[moveX][y] == counter) {
                inline++;
                if (inline == 4) {
                    System.out.println("Player " + counter + "wins");
                    return false;
                }
            } else {
                inline = 0;
            }
        }
        for (int x = 0; x < 7; x++) {
            if (board[x][moveY] == counter) {
                inline++;
                if (inline == 4) {
                    System.out.println("Player " + counter + " wins");
                    return false;
                }
            } else {
                inline = 0;
            }
        }
        return true;
    }
    private boolean checkDiagonal ( int moveX, int moveY, int counter, int[][] board){
        int x = moveX;
        int y = moveY;
        int inLine = 0;
        boolean edgeNotFound = true;
        while (edgeNotFound) {
            if (y == 0 || x == 0) {
                edgeNotFound = false;
            } else {
                x -= 1;
                y -= 1;
            }
        }
        while ((x < 7 && x > -1) && (y < 6 && y > -1)) {
            if (board[x][y] == counter) {
                inLine += 1;
                if (inLine == 4) {
                    System.out.println("Player " + counter + "wins.");
                    return true;
                }
            } else {
                inLine = 0;
            }
            x += 1;
            y += 1;
            edgeNotFound = true;
            inLine = 0;
            while (edgeNotFound) {
                if (y == 5 || x == 0) {
                    edgeNotFound = false;
                } else {
                    x -= 1;
                    y -= 1;
                }
            }
            while ((x < 7 && x > -1) && (y < 6 && y > -1)) {
                if (board[x][y] == counter) {
                    inLine += 1;
                    if (inLine == 4) {
                        System.out.println("Player " + counter + " wins");
                        return false;
                    }
                } else {
                    inLine = 0;
                }
                x -= 1;
                y -= 1;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        obj.runGame();
    }

    }