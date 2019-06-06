package pt.ipbeja.po2.connectfour.model;

import pt.ipbeja.po2.connectfour.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Tiago Silva 16237
 * @version 06-05-2019
 */

public class ConnectFourModel {

    private final View VIEW;
    public final int SIZE_COL = 7;
    public final int SIZE_LINE = 6;
    public Cell[][] boardData;
    public int turnCounter;
    private int countPlayPlayer1;
    private int countPlayPlayer2;
    public boolean finish;
    private List<Position> playsUndo;
    private List<HistoryVictory> historyVictories;

    /**
     * The class constructor
     * Initialize elements and is the initiator of game on the model side
     */
    public ConnectFourModel(View view) {
        this.historyVictories = new ArrayList<>();
        this.playsUndo = new ArrayList<>();
        this.finish = false;
        this.VIEW = view;
        this.turnCounter = 0;
        this.countPlayPlayer1 = 0;
        this.countPlayPlayer2 = 0;
        this.boardData = new Cell[this.SIZE_LINE][this.SIZE_COL];
        this.fillBoard();

    }

    /**
     * Create a new game again
     */
    public void newGame() {
        this.finish = false;
        this.turnCounter = 0;
        this.countPlayPlayer1 = 0;
        this.countPlayPlayer2 = 0;
        this.fillBoard();
        this.VIEW.clearBoard();
    }

    /**
     * Fill this array of array (boarDate) with empty cells
     */
    public void fillBoard() {
        for (int line = 0; line < this.SIZE_LINE; line++) {
            for (int col = 0; col < this.SIZE_COL; col++) {
                this.boardData[line][col] = Cell.EMPTY;
            }
        }
    }

    /**
     * Is the event after the button has been pressed
     *
     * @param line is one of the coordenates of button select
     * @param col  is another of the coordenates of button select
     */
    public void placeSelected(int line, int col) {
        if (this.finish == false && this.isFree(line, col)) {
            int bLine = this.bestLine(col);
            this.updateBoardState(bLine, col);
            this.turnCounter++;
            this.playsUndo.add(new Position(bLine, col));
            this.checkBoardState(bLine, col);
        }

    }

    /**
     * Checks to see it there is a winner acroos the best line and the selectes column
     *
     * @param bLine the line where we play
     * @param col   the column where we play
     * @return a boleean, if true so won else not
     */
    private boolean isWinPosition(int bLine, int col) {
        return (horizontal(bLine) || vertical(bLine, col) || diagonal(bLine, col) || antiDiagonal(bLine, col));
    }

    /**
     * Consonant the selected column we calculate the line where to play
     *
     * @param col the column select
     * @return the line where to play
     */
    public int bestLine(int col) {
        int bLine = 0;
        for (int i = this.SIZE_LINE - 1; i >= 0; i--) {
            if (this.isFree(i, col)) {
                bLine = i;
                break;
            }
        }
        return bLine;
    }

    /**
     * Checks if the Cell clicked is free
     *
     * @param line the line of button clicked
     * @param col  the column of button clicked
     * @return a boolean
     */
    public boolean isFree(int line, int col) {
        return boardData[line][col] == Cell.EMPTY;
    }

    /**
     * Returns a selected cell
     *
     * @param line the line of the cell
     * @param col  the column of the cell
     * @return a Cell
     */
    public Cell getCell(int line, int col) {
        return boardData[line][col];
    }

    /**
     * Through turncounter we will associate with the boarddata a player and updated the board
     *
     * @param line the line where we play
     * @param col  the column selected
     */
    private void updateBoardState(int line, int col) {
        if (this.turnCounter % 2 == 0) {
            this.boardData[line][col] = Cell.PLAYER1;
            this.countPlayPlayer1++;
        } else {
            this.boardData[line][col] = Cell.PLAYER2;
            this.countPlayPlayer2++;
        }

        this.VIEW.update(this.getCell(line, col), line, col);
    }

    /**
     * Check the state of board, we verificated is exist a winner or ir a draw
     *
     * @param bLine the line where we play
     * @param col   the column selected
     */
    private void checkBoardState(int bLine, int col) {
        if (minPlays(this.turnCounter)) {
            if (isWinPosition(bLine, col)) {



                this.historyVictories.add(new HistoryVictory(this.VIEW.winAlert(), score()));
                this.orderListOfWins();
                this.VIEW.playerWin(boardData[bLine][col], score(), (ArrayList) this.historyVictories);
                this.finish = true;


                System.out.println(this.historyVictories.toString());
            } else if (isDrawPosition(bLine, col)) {
                this.VIEW.draw();
                this.finish = true;
            }
        }
    }

    /**
     * Checks if there is a winning vertical line
     *
     * @param bLine the line where we play
     * @param col   the column selected
     * @return a boolean
     */
    public boolean vertical(int bLine, int col) {
        if (bLine <= 2) {
            int count = 1;
            for (int i = bLine; i <= 4; i++) {
                Cell current = boardData[i][col];
                Cell next = boardData[i + 1][col];
                if (current == next) {
                    count++;
                } else {
                    count = 1;
                }
                if (count == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a winning horicontal line
     *
     * @param bLine the line where we play
     * @return a boolean
     */
    public boolean horizontal(int bLine) {
        int count = 1;
        for (int i = 0; i < SIZE_COL - 1; i++) {
            Cell current = boardData[bLine][i];
            Cell next = boardData[bLine][i + 1];
            if (boardData[bLine][i] != Cell.EMPTY && current == next) {
                count++;

            } else {
                count = 1;
            }
            if (count == 4) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if there is a winning diagonals lines
     *
     * @param bLine the line where we play
     * @param col   the column selected
     * @return a boolean
     */
    public boolean checkDiagonals(int bLine, int col) {
        return (diagonal(bLine, col) || antiDiagonal(bLine, col));
    }

    /**
     * Checks if there is a winning diagonal line
     *
     * @param line the line where we play
     * @param col  the column selected
     * @return a boolean
     */
    private boolean diagonal(int line, int col) {
        int counter = 1;
        boolean up = true, down = true;

        for (int i = 1; i <= 3; i++) {

            if (line + i < this.SIZE_LINE && col + i < this.SIZE_COL) {
                if (this.boardData[line + i][col + i] == this.boardData[line][col] && down) {
                    counter++;
                } else {
                    down = false;
                }
            }


            if (line - i >= 0 && col - i >= 0) {
                if (this.boardData[line - i][col - i] == this.boardData[line][col] && up) {
                    counter++;
                } else {
                    up = false;
                }
            }

        }
        if (counter >= 4) {
            return true;
        }
        return false;
    }

    /**
     * Checks if there is a winning anti diagonal line
     *
     * @param line the line where we play
     * @param col  the column selected
     * @return a boolean
     */
    private boolean antiDiagonal(int line, int col) {
        int counter = 1;
        boolean up = true, down = true;

        for (int i = 1; i <= 3; i++) {

            if (line + i < this.SIZE_LINE && col - i >= 0) {
                if (this.boardData[line + i][col - i] == this.boardData[line][col] && down) {
                    counter++;
                } else {
                    down = false;
                }
            }


            if (line - i >= 0 && col + i < this.SIZE_COL) {
                if (this.boardData[line - i][col + i] == this.boardData[line][col] && up) {
                    counter++;
                } else {
                    up = false;
                }
            }

        }
        if (counter >= 4) {
            return true;
        }
        return false;
    }

    /**
     * @param line
     * @param col
     */
    private void resetPlace(int line, int col) {
        this.boardData[line][col] = Cell.EMPTY;
    }

    /**
     * Undoes the last turn.
     *
     * @return the position of the last turn
     */
    public Position undoLastTurn() {
        if (this.playsUndo.size() > 0) {
            this.turnCounter--;
            Position p = this.playsUndo.get(this.playsUndo.size() - 1);
            this.resetPlace(p.getLine(), p.getCol());
            this.playsUndo.remove(p);
            return p;
        } else {
            return null;
        }

    }

    /**
     * Calculate a number minimum of plays required to won
     *
     * @param turn the number of plays played
     * @return a boolean
     */
    private boolean minPlays(int turn) {
        return turn >= (4 * 2) - 1;
    }

    /**
     * Check if there was a tie in the game
     *
     * @param bLine the line where we play
     * @param col   the column selected
     * @return a boolean
     */
    public boolean isDrawPosition(int bLine, int col) {
        return turnCounter == this.SIZE_LINE * this.SIZE_COL && !(isWinPosition(bLine, col));
    }


    /**
     *
     * @return
     */
    private int score() {
        int turn = 0;
        if (turn % 2 == 0) {
            turn = this.countPlayPlayer1;
        } else {
            turn = this.countPlayPlayer2;
        }
        int maxPlays = this.SIZE_LINE * this.SIZE_COL;
        int score = maxPlays - turn;
        return score;
    }

    /**
     * Order list of winners plays
     */
    private void orderListOfWins() {
        Collections.sort(historyVictories, new Comparator<HistoryVictory>() {
            @Override
            public int compare(HistoryVictory o1, HistoryVictory o2) {
                return o2.getScore() - o1.getScore();

            }
        });
    }
}
