package pt.ipbeja.po2.tictactoe.tui;

import pt.ipbeja.po2.tictactoe.View;
import pt.ipbeja.po2.tictactoe.model.Place;
import pt.ipbeja.po2.tictactoe.model.TicTacToeGame;

import java.util.Locale;
import java.util.Scanner;

/**
 * @author DiogoPM
 * @version 11/04/2018
 */

public class TicTacToeTUI implements View {

    final static Scanner scanner = new Scanner(System.in);
    static { scanner.useLocale(Locale.ENGLISH); }

    private final TicTacToeGame GAME_MODEL;

    public TicTacToeTUI() {
        this.GAME_MODEL = new TicTacToeGame(this);
    }

    public void startGame() {
        this.gameLoop();
    }

    @Override
    public void playerWins(int player) {
        char p = player % 2 == 0 ? 'X' : 'O';
        System.out.println(p + " won the game!");
    }

    @Override
    public void draw() {
        System.out.println("Draw!");
    }

    private void gameLoop() {
        while (true) { // TODO verificar se o jogo terminou (win ou draw)
            printBoard();
            System.out.println("Play: ");
            String play = scanner.next();
            String[] coords = play.split(",");

            // TODO validar coordenadas (se existem e se a posição está livre)
            int line = Integer.parseInt(coords[0]);
            int col = Integer.parseInt(coords[1]);
            GAME_MODEL.placeSelected(line, col);
        }
    }

    private void printBoard() {
        for (int i = 0; i < GAME_MODEL.SIZE; i++) {
            for (int j = 0; j < GAME_MODEL.SIZE; j++) {
                Place place = this.GAME_MODEL.getPlace(i, j);
                if(place == Place.EMPTY) {
                    System.out.print("_");
                }
                else {
                    System.out.print(place);
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        TicTacToeTUI ticTacToeTUI = new TicTacToeTUI();
        ticTacToeTUI.startGame();
    }





    /*
    public static void main(String[] args) {

        String input = scanner.next();
        String[] coords = input.split(",");

        for (String coord : coords) {
            System.out.println(coord);
        }
        int line = Integer.parseInt(coords[0]);
        int col = Integer.parseInt(coords[1]);

        System.out.println(line + " - " + col);


    }*/
}





