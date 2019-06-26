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
        this.boardModel = new BoardModel();
        this.possibleMoves = new ArrayList<>();
        this.possibleTakes = new ArrayList<>();
    }

    /* Métodos de Teste Rei*/
    /* Teste das possibleMovesWithoutTakes*/
    @Test
    void PossibleMovesKing() {

        start();

        King king = new King(boardModel, true);

        this.boardModel.setPieceOnBoard(king, 4, 'a');

        List<Position> realMoves = Arrays.asList(new Position(5, 'b'), new Position(5, 'a'), new Position(4, 'b'), new Position(3, 'a'), new Position(3, 'b'));

        List<Position> moves = king.possibleMoves();

        System.out.println(realMoves + " " + moves);

        //assertEquals(realMoves, moves);
        assertTrue(realMoves.size() == moves.size() && realMoves.containsAll(moves) && moves.containsAll(realMoves));

    }

    /* Teste das possibleTakes*/

    @Test
    void PossibleTakesKing() {

        start();

        King king = new King(boardModel, true);
        Bishop bishop = new Bishop(boardModel, false);

        this.boardModel.setPieceOnBoard(king, 4, 'a');
        this.boardModel.setPieceOnBoard(bishop, 5, 'a');

        List<Position> realTakes = Arrays.asList(bishop.getPosition());

        List<Position> takes = king.possibleTakes();

        System.out.println(realTakes + " " + takes);

        //assertEquals(realMoves, moves);

        assertTrue(realTakes.size() == takes.size() && realTakes.containsAll(takes) && takes.containsAll(realTakes));

    }

    /* Teste das checkMoveAction*/

    @Test
    void VerificationMoveKing() {

        start();

        King king = new King(boardModel, false);
        this.boardModel.setPieceOnBoard(king, 4, 'a');

        Position end = new Position(3, 'a');
        Position start = king.getPosition();



        this.boardModel.movePiece(start, end);



        assertEquals(end, king.getPosition());

        assertNull(this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));
    }

    /* Métodos de Teste Bispo*/
    /* Teste das possibleMovesWithoutTakes*/

    @Test
    void PossibleMovesBishop() {

        start();

        Bishop bishop = new Bishop(boardModel, true);

        this.boardModel.setPieceOnBoard(bishop, 8, 'b');

        List<Position> realMoves = Arrays.asList(new Position(7, 'a'), new Position(7, 'c'), new Position(6, 'd'), new Position(5, 'e'), new Position(4, 'f'),
                new Position(3, 'g'), new Position(2, 'h'));

        List<Position> moves = bishop.possibleMoves();

        System.out.println(realMoves + " " + moves);

        //assertEquals(realMoves, moves);
        assertTrue(realMoves.size() == moves.size() && realMoves.containsAll(moves) && moves.containsAll(realMoves));

    }

    /* Teste das possibleTakes*/

    @Test
    void PossibleTakesBishop() {

        start();

        Bishop bishop = new Bishop(boardModel, false);
        Tower tower = new Tower(boardModel, true);

        this.boardModel.setPieceOnBoard(bishop, 8, 'b');

        this.boardModel.setPieceOnBoard(tower, 5, 'e');


        List<Position> realTakes = Arrays.asList(tower.getPosition());

        List<Position> takes = bishop.possibleTakes();



        //assertEquals(realMoves, moves);

        assertTrue(realTakes.size() == takes.size() && realTakes.containsAll(takes) && takes.containsAll(realTakes));

        System.out.println(realTakes + " " + takes);

    }

    /* Teste das checkMoveAction*/

    @Test
    void VerificationMoveBishop() {

        start();

        Bishop bishop = new Bishop(boardModel, true);
        this.boardModel.setPieceOnBoard(bishop, 5, 'c');

        Position end = new Position(7, 'e');
        Position start = bishop.getPosition();



        this.boardModel.movePiece(start, end);



        assertEquals(end, bishop.getPosition());


        assertNull(this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));

        System.out.println("Start position:"+start +", End position:" +end);

        System.out.println(this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));
    }

    /* Métodos de Teste Torre*/
    /* Teste das possibleMovesWithoutTakes*/

    @Test
    void PossibleMovesTower() {

        start();

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

        System.out.println(realMoves + " " + moves);

        //assertEquals(realMoves, moves);
        assertTrue(realMoves.size() == moves.size() && realMoves.containsAll(moves) && moves.containsAll(realMoves));

    }

    /* Teste das possibleTakes*/

    @Test
    void PossibleTakesTower() {

        start();

        Tower tower = new Tower(boardModel, true);
        Queen queen = new Queen(boardModel, false);

        this.boardModel.setPieceOnBoard(tower, 8, 'b');

        this.boardModel.setPieceOnBoard(queen, 8, 'g');


        List<Position> realTakes = Arrays.asList(queen.getPosition());

        List<Position> takes = tower.possibleTakes();


        System.out.println(realTakes + " " + takes);

        //assertEquals(realMoves, moves);

        assertTrue(realTakes.size() == takes.size() && realTakes.containsAll(takes) && takes.containsAll(realTakes));



    }

    /* Teste das checkMoveAction*/

    @Test
    void VerificationMoveTower() {

        start();

        Tower tower = new Tower(boardModel, true);
        this.boardModel.setPieceOnBoard(tower, 5, 'c');

        Position end = new Position(5, 'h');
        Position start = tower.getPosition();



        this.boardModel.movePiece(start, end);



        assertEquals(end, tower.getPosition());


        assertNull(this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));

        System.out.println("Start position:"+start +", End position:" +end);

        System.out.println(this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));
    }

    /* Métodos de Teste Rainha*/
    /* Teste das possibleMovesWithoutTakes*/

    @Test
    void PossibleMovesQueen() {

        start();

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

        System.out.println(realMoves + " " + moves);

        //assertEquals(realMoves, moves);
        assertTrue(realMoves.size() == moves.size() && realMoves.containsAll(moves) && moves.containsAll(realMoves));

    }

    /* Teste das possibleTakes*/

    @Test
    void PossibleTakesQueen() {

        start();


        Queen queen = new Queen(boardModel, false);
        Tower tower = new Tower(boardModel, true);

        this.boardModel.setPieceOnBoard(tower, 8, 'b');

        this.boardModel.setPieceOnBoard(queen, 8, 'g');


        List<Position> realTakes = Arrays.asList(tower.getPosition());

        List<Position> takes = queen.possibleTakes();


        System.out.println(realTakes + " " + takes);

        //assertEquals(realMoves, moves);

        assertTrue(realTakes.size() == takes.size() && realTakes.containsAll(takes) && takes.containsAll(realTakes));



    }

    /* Teste das checkMoveAction*/

    @Test
    void VerificationMoveQueen() {

        start();

        Queen queen = new Queen(boardModel, true);
        this.boardModel.setPieceOnBoard(queen, 5, 'e');

        Position end = new Position(2, 'b');
        Position start = queen.getPosition();



        this.boardModel.movePiece(start, end);



        assertEquals(end, queen.getPosition());


        assertNull(this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));

        System.out.println("Start position:"+start +", End position:" +end);

        System.out.println(this.boardModel.getPiece(start.getTranslatedLine(), start.getTranslatedCol()));
    }
}