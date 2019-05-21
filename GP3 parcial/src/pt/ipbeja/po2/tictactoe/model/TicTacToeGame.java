package pt.ipbeja.po2.tictactoe.model;

import pt.ipbeja.po2.tictactoe.View;
import pt.ipbeja.po2.tictactoe.gui.TicTacToeGUI;

/**
 * @author Sascha Geng, Diogo Pina Manique
 * @version 24-05-2018
 */

public class TicTacToeGame {

    public final int SIZE = 3;

    private final View VIEW;
    // 2-dimensional array of type "Place" (see the enum) - Ex. 3
    private final Place[][] boardData;
    private int turnCounter;

    /**
     * Constructor
     * @param view The TicTacToeGUI View (observer)
     */
    public TicTacToeGame(View view) {
        this.VIEW = view;
        this.boardData = new Place[this.SIZE][this.SIZE];
        this.fillBoard();
    }

    /**
     * Creates the game board by populating each Place with an EMPTY value
     */
    private void fillBoard() {
        System.out.format("Creating a %dx%d board\n", this.SIZE, this.SIZE);

        for (int line = 0; line < this.SIZE; line++) {
            for (int col = 0; col < this.SIZE; col++) {
                this.boardData[line][col] = Place.EMPTY;
            }
        }
        // OU
        // for(Place[] row : this.boardData) {
        //    Arrays.fill(row, Place.EMPTY);
        //}
    }

    /**
     * Plays on the specified board coordinates
     * @param line line coordinate
     * @param col column coordinate
     */
    public void placeSelected(int line, int col) {
        this.updateBoardState(line, col);
        this.turnCounter++;
        this.checkBoardState(line, col);
    }

    /**
     * Check if the board cell is free (no player)
     * @param line Line coordinate
     * @param col Column coordinate
     * @return true if the cell is free
     */
    public boolean isFree(int line, int col) {
        return boardData[line][col] == Place.EMPTY;
    }

    /**
     * Get the Place for coordinates
     * @param line Line coordinate
     * @param col Column coordinate
     * @return the Place at the specified coordinates
     */
    public Place getPlace(int line, int col) {
        return boardData[line][col];
    }

    private void updateBoardState(int line, int col) {
        if (this.turnCounter % 2 == 0) {
            this.boardData[line][col] = Place.X;
        } else {
            this.boardData[line][col] = Place.O;
        }
        // ou boardData[line][col] = (turnCounter % 2 == 0) ? Place.X : Place.O;
    }

    private void checkBoardState(int line, int col) {
        if (inWinnableTurn(this.turnCounter)) {
            // Só vale a pena começar a verificar condições de vitória a partir do número mínimo de jogadas para algum dos jogadores vencer
            // Ex. para um tabuleiro 3x3, é necessário haver pelo menos 5 jogadas para vencer

            if (isWinPosition(line, col)) {
                this.VIEW.playerWins((this.turnCounter - 1) % 2); // 0 ou 1
            } else if (isDrawPosition()) {
                this.VIEW.draw();
            }
        }
    }

    /**
     * Checks if it is possible to win in this turn
     * @param turn the turn to check
     * @return true if can win false otherwise
     */
    private boolean inWinnableTurn(int turn)
    {
        return turn >= (this.SIZE * 2 - 1);
    }

    /**
     * Check if the game ended in a draw
     * @return true if the draw condition was found
     */
    private boolean isDrawPosition() {
        return turnCounter == this.SIZE * this.SIZE;
    }

    /**
     * Check for winning condition
     * @param line
     * @param col
     * @return true if a winning condition was found
     */
    private boolean isWinPosition(int line, int col) {
        // Verificar se a jogada resultou numa sequência vencedora
        // Os métodos são invocados sequencialmente e quando um deles devolve 'true' os seguintes já não são invocados
        return (hasWinningLine(line) ||
                hasWinningColumn(col) ||
                hasWinningMainDiagonal(line, col) ||
                hasWinnningAntiDiagonal(line, col));
        // Por exemplo:: Se hasWinningLine -> false ...continua... se hasWinningColumn -> true,
        // termina aqui e devolve true.
    }

    /**
     * Check line for winning condition
     * @param line
     * @return true if a winning condition was found
     */
    private boolean hasWinningLine(int line) {
        // Verificar se a linha onde a jogada ocorreu têm uma sequência vencedora
        for (int i = 0; i < SIZE - 1; i++) {
            Place current = boardData[line][i];
            Place next = boardData[line][i + 1];
            if (current != next) {
                return false; // Se os valores diferem, podemos terminar aqui e devolver false
            }
        }
        return true; // Só chegamos a este ponto se de facto todos os valores forem iguais
    }

    /**
     * Check column for winning condition
     * @param col
     * @return true if a winning condition was found
     */
    private boolean hasWinningColumn(int col) {
        // Verificar se a coluna onde a jogada ocorreu têm uma sequência vencedora
        for (int i = 0; i < SIZE - 1; i++) {
            if (boardData[i][col] != boardData[i + 1][col]) {
                return false;
            }
        }
        return true;
    }


    /**
     * Check diagonal for winning condition
     * @param line
     * @param col
     * @return true if a winning condition was found
     */
    private boolean hasWinningMainDiagonal(int line, int col) {
        // Se jogada ocorreu na diagonal, verificar se a linha onde a jogada ocorreu têm uma sequência vencedora
        if (this.inMainDiagonal(line, col)) {
            for (int i = 0; i < SIZE - 1; i++) {
                if (boardData[i][i] != boardData[i + 1][i + 1]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check antidiagonal for winning condition
     * @param line
     * @param col
     * @return true if a winning condition was found
     */
    private boolean hasWinnningAntiDiagonal(int line, int col) {
        // Se jogada ocorreu na antidiagonal, verificar se a linha onde a jogada ocorreu têm uma sequência vencedora
        if (this.inAntiDiagonal(line, col)) { // Se a soma dos valores da linha e da coluna for igual ao tamanho da grelha -1, estamos na antidiagonal
            for (int i = 0; i < SIZE - 1; i++) {
                int j = SIZE - i - 1;

                if (boardData[i][j] != boardData[i + 1][j - 1]) {
                    return false;
                }

            }
            return true;
        }
        return false;
    }

    /**
     * Se os valores da linha e coluna forem os mesmos, sabemos estar numa diagonal
     * @param line line value
     * @param col column value
     * @return true if (line, col) is in main diagonal false otherwise
     */
    private boolean inMainDiagonal(int line, int col) {
        return line == col;
    }

    /**
     * Se os valores da linha e coluna forem os mesmos, sabemos estar numa diagonal
     * @param line line value
     * @param col column value
     * @return true if (line, col) is in anti diagonal false otherwise
     */
    private boolean inAntiDiagonal(int line, int col)
    {
        return (line + col) == (SIZE - 1);
    }
}







