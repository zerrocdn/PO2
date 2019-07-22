package pt.ipbeja.estig.po2.chess.model;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardModelTest {

    private BoardModel boardModel;
    private List<Position> possibleMoves, possibleTakes;

    void start() {
        this.boardModel = new BoardModel(new DummyView());
        this.possibleMoves = new ArrayList<>();
        this.possibleTakes = new ArrayList<>();

    }

    /* Métodos de Teste Rei*/
    /* Teste das possibleMovesWithoutTakes*/
    @Test
    void PossibleMovesKing() {

        start();

        this.boardModel.resetBoard();

        King king = new King(boardModel, true);

        this.boardModel.setPieceOnBoard(king, 4, 'a');

        List<Position> realMoves = Arrays.asList(new Position(5, 'b'), new Position(5, 'a'), new Position(4, 'b'), new Position(3, 'a'), new Position(3, 'b'));

        List<Position> moves = king.possibleMoves();


        //assertEquals(realMoves, moves);
        assertTrue(realMoves.size() == moves.size() && realMoves.containsAll(moves) && moves.containsAll(realMoves));

        System.out.println("Real King Moves: "+realMoves + "\nPossible King Moves Method: " + moves);
    }

    /* Teste das possibleTakes*/

    @Test
    void PossibleTakesKing() {

        start();

        this.boardModel.resetBoard();

        King king = new King(boardModel, true);
        Bishop bishop = new Bishop(boardModel, false);

        this.boardModel.setPieceOnBoard(king, 4, 'a');
        this.boardModel.setPieceOnBoard(bishop, 5, 'a');

        List<Position> realTakes = Arrays.asList(bishop.getPosition());

        List<Position> takes = king.possibleTakes();



        //assertEquals(realMoves, moves);

        assertTrue(realTakes.size() == takes.size() && realTakes.containsAll(takes) && takes.containsAll(realTakes));

        System.out.println("Real King Take: "+realTakes + "\nPossible King Takes Method: " + takes);

    }

    /* Teste das checkMoveAction*/

    @Test
    void VerificationMoveKing() {

        start();

        this.boardModel.resetBoard();

        King king = new King(boardModel, false);
        this.boardModel.setPieceOnBoard(king, 4, 'a');

        Position end = new Position(3, 'a');
        Position start = king.getPosition();



        this.boardModel.movePiece(start, end);



        assertEquals(end, king.getPosition());

        assertNull(this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));

        System.out.println("Original King Position: "+ start+"\nFinal King Position: "+end);
        System.out.println("Check original position "+start+": "+this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));
    }

    /* Métodos de Teste Bispo*/
    /* Teste das possibleMovesWithoutTakes*/

    @Test
    void PossibleMovesBishop() {

        start();

        this.boardModel.resetBoard();

        Bishop bishop = new Bishop(boardModel, true);

        this.boardModel.setPieceOnBoard(bishop, 8, 'b');

        List<Position> realMoves = Arrays.asList(new Position(7, 'a'), new Position(7, 'c'), new Position(6, 'd'), new Position(5, 'e'), new Position(4, 'f'),
                new Position(3, 'g'), new Position(2, 'h'));

        List<Position> moves = bishop.possibleMoves();



        //assertEquals(realMoves, moves);
        assertTrue(realMoves.size() == moves.size() && realMoves.containsAll(moves) && moves.containsAll(realMoves));


        System.out.println("Real Bishop Moves: "+realMoves + "\nPossible Bishop Moves Method: " + moves);

    }

    /* Teste das possibleTakes*/

    @Test
    void PossibleTakesBishop() {

        start();

        this.boardModel.resetBoard();

        Bishop bishop = new Bishop(boardModel, false);
        Tower tower = new Tower(boardModel, true);

        this.boardModel.setPieceOnBoard(bishop, 8, 'b');

        this.boardModel.setPieceOnBoard(tower, 5, 'e');


        List<Position> realTakes = Arrays.asList(tower.getPosition());

        List<Position> takes = bishop.possibleTakes();



        //assertEquals(realMoves, moves);

        assertTrue(realTakes.size() == takes.size() && realTakes.containsAll(takes) && takes.containsAll(realTakes));

        System.out.println("Real Bishop Take: "+realTakes + "\nPossible Bishop Takes Method: " + takes);

    }

    /* Teste das checkMoveAction*/

    @Test
    void VerificationMoveBishop() {

        start();

        this.boardModel.resetBoard();

        Bishop bishop = new Bishop(boardModel, true);
        this.boardModel.setPieceOnBoard(bishop, 5, 'c');

        Position end = new Position(7, 'e');
        Position start = bishop.getPosition();



        this.boardModel.movePiece(start, end);



        assertEquals(end, bishop.getPosition());


        assertNull(this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));



        System.out.println("Original Bishop position: "+start +"\nFinal Bishop position: " +end);

        System.out.println("Check original position "+start+": "+this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));
    }

    /* Métodos de Teste Torre*/
    /* Teste das possibleMovesWithoutTakes*/

    @Test
    void PossibleMovesTower() {

        start();

        this.boardModel.resetBoard();

        Tower tower = new Tower(boardModel, false);
        Bishop bishop = new Bishop(boardModel, true);

        this.boardModel.setPieceOnBoard(tower, 6, 'd');
        this.boardModel.setPieceOnBoard(bishop, 6, 'g');


        List<Position> realMoves = Arrays.asList(new Position(7, 'd'), new Position(8, 'd'),
                                                new Position(6, 'a'), new Position(6, 'b'),
                                                new Position(6, 'c'), new Position(6, 'e'),
                                                new Position(6, 'f'), new Position(5, 'd'),
                                                new Position(4, 'd'), new Position(3, 'd'),
                                                new Position(2, 'd'), new Position(1, 'd'));

        List<Position> moves = tower.possibleMoves();

        //assertEquals(realMoves, moves);
        assertTrue(realMoves.size() == moves.size() && realMoves.containsAll(moves) && moves.containsAll(realMoves));

        System.out.println("Real Tower Moves: "+realMoves + "\nPossible Tower Moves Method: " + moves);

    }

    /* Teste das possibleTakes*/

    @Test
    void PossibleTakesTower() {

        start();

        this.boardModel.resetBoard();

        Tower tower = new Tower(boardModel, true);
        Queen queen = new Queen(boardModel, false);

        this.boardModel.setPieceOnBoard(tower, 8, 'b');

        this.boardModel.setPieceOnBoard(queen, 8, 'g');




        List<Position> realTakes = Arrays.asList(queen.getPosition());

        List<Position> takes = tower.possibleTakes();


        //assertEquals(realMoves, moves);

        assertTrue(realTakes.size() == takes.size() && realTakes.containsAll(takes) && takes.containsAll(realTakes));


        System.out.println("Real Tower Take: "+realTakes + "\nPossible Tower Takes Method: " + takes);


    }

    /* Teste das checkMoveAction*/

    @Test
    void VerificationMoveTower() {

        start();

        this.boardModel.resetBoard();

        Tower tower = new Tower(boardModel, true);
        this.boardModel.setPieceOnBoard(tower, 5, 'c');

        Position end = new Position(5, 'h');
        Position start = tower.getPosition();



        this.boardModel.movePiece(start, end);



        assertEquals(end, tower.getPosition());


        assertNull(this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));

        System.out.println("Original Tower position: "+start +"\nFinal Tower position: " +end);

        System.out.println("Check original position "+start+": "+this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));
    }

    /* Métodos de Teste Rainha*/
    /* Teste das possibleMovesWithoutTakes*/

    @Test
    void PossibleMovesQueen() {

        start();

        this.boardModel.resetBoard();

        Queen queen1 = new Queen(boardModel, false);
        Queen queen2 = new Queen(boardModel, false);
        Bishop bishop = new Bishop(boardModel, true);
        Tower tower = new Tower(boardModel, true);

        this.boardModel.setPieceOnBoard(queen1, 5, 'd');
        this.boardModel.setPieceOnBoard(bishop, 5, 'f');
        this.boardModel.setPieceOnBoard(tower, 6, 'd');
        this.boardModel.setPieceOnBoard(queen2, 3, 'b');


        List<Position> realMoves = Arrays.asList(new Position(5, 'e'), new Position(5, 'a'),
                new Position(5, 'b'), new Position(5, 'c'),
                new Position(4, 'c'), new Position(6, 'c'),
                new Position(7, 'b'), new Position(8, 'a'),
                new Position(4, 'd'), new Position(3, 'd'),
                new Position(2, 'd'), new Position(1, 'd'),
                new Position(4, 'e'), new Position(3, 'f'),
                new Position(2, 'g'), new Position(1, 'h'),
                new Position(6, 'e'), new Position(7, 'f'),
                new Position(8, 'g'));

        List<Position> moves = queen1.possibleMoves();


        //assertEquals(realMoves, moves);
        assertTrue(realMoves.size() == moves.size() && realMoves.containsAll(moves) && moves.containsAll(realMoves));

        System.out.println("Real Queen Moves: "+realMoves + "\nPossible Queen Moves Method: " + moves);

    }

    /* Teste das possibleTakes*/

    @Test
    void PossibleTakesQueen() {

        start();

        this.boardModel.resetBoard();

        Queen queen = new Queen(boardModel, false);
        Tower tower = new Tower(boardModel, true);
        Bishop bishop = new Bishop(boardModel, true);

        this.boardModel.setPieceOnBoard(tower, 8, 'b');
        this.boardModel.setPieceOnBoard(bishop, 3, 'g');
        this.boardModel.setPieceOnBoard(queen, 8, 'g');


        List<Position> realTakes = Arrays.asList(tower.getPosition(), bishop.getPosition());

        List<Position> takes = queen.possibleTakes();


        //assertEquals(realMoves, moves);

        assertTrue(realTakes.size() == takes.size() && realTakes.containsAll(takes) && takes.containsAll(realTakes));

        System.out.println("Real Queen Take: "+realTakes + "\nPossible Queen Takes Method: " + takes);

    }

    /* Teste das checkMoveAction*/

    @Test
    void VerificationMoveQueen() {

        start();

        this.boardModel.resetBoard();

        Queen queen = new Queen(boardModel, true);
        this.boardModel.setPieceOnBoard(queen, 5, 'e');

        Position end = new Position(2, 'b');
        Position start = queen.getPosition();



        this.boardModel.movePiece(start, end);



        assertEquals(end, queen.getPosition());


        assertNull(this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));

        System.out.println("Original Queen position: "+start +"\nFinal Queen position: " +end);

        System.out.println("Check original position "+start+": "+this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));

    }

    @Test
    void readInitialPlacesFromFile() {
        start();

        Piece kingW = new King(this.boardModel, new Position(1, 'e'), true);
        Piece kingB = new King(this.boardModel, new Position(8, 'e'), false);

        Piece bishopB1 = new Bishop(this.boardModel, new Position(8, 'c'), false);
        Piece bishopB2 = new Bishop(this.boardModel, new Position(8, 'f'), false);
        Piece bishopW1 = new Bishop(this.boardModel, new Position(1, 'c'), true);
        Piece bishopW2 = new Bishop(this.boardModel, new Position(1,'f'), true);

        Piece towerW1 = new Tower(this.boardModel, new Position(1, 'a'), true);
        Piece towerW2 = new Tower(this.boardModel, new Position(1, 'h'), true);
        Piece towerB1 = new Tower(this.boardModel, new Position(8, 'a'), false);
        Piece towerB2 = new Tower(this.boardModel, new Position(8, 'h'), false);

        Piece quenW = new Queen(this.boardModel, new Position(1, 'd'), true);
        Piece quenB = new Queen(this.boardModel, new Position(8, 'd'), false);

        assertEquals(kingB.toStringToCompare(), this.boardModel.getPiece(kingB.getPosition()).toStringToCompare());
        assertEquals(kingW.toStringToCompare(), this.boardModel.getPiece(kingW.getPosition()).toStringToCompare());

        assertEquals(bishopB1.toStringToCompare(), this.boardModel.getPiece(bishopB1.getPosition()).toStringToCompare());
        assertEquals(bishopB2.toStringToCompare(), this.boardModel.getPiece(bishopB2.getPosition()).toStringToCompare());
        assertEquals(bishopW1.toStringToCompare(), this.boardModel.getPiece(bishopW1.getPosition()).toStringToCompare());
        assertEquals(bishopW2.toStringToCompare(), this.boardModel.getPiece(bishopW2.getPosition()).toStringToCompare());

        assertEquals(towerB1.toStringToCompare(), this.boardModel.getPiece(towerB1.getPosition()).toStringToCompare());
        assertEquals(towerB2.toStringToCompare(), this.boardModel.getPiece(towerB2.getPosition()).toStringToCompare());
        assertEquals(towerW1.toStringToCompare(), this.boardModel.getPiece(towerW1.getPosition()).toStringToCompare());
        assertEquals(towerW2.toStringToCompare(), this.boardModel.getPiece(towerW2.getPosition()).toStringToCompare());

        assertEquals(quenB.toStringToCompare(), this.boardModel.getPiece(quenB.getPosition()).toStringToCompare());
        assertEquals(quenW.toStringToCompare(), this.boardModel.getPiece(quenW.getPosition()).toStringToCompare());
    }
}