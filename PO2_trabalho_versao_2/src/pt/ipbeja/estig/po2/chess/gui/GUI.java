package pt.ipbeja.estig.po2.chess.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pt.ipbeja.estig.po2.chess.View;
import pt.ipbeja.estig.po2.chess.model.Board;
import pt.ipbeja.estig.po2.chess.model.Piece;
import pt.ipbeja.estig.po2.chess.model.Position;

import java.util.List;

public class GUI extends BorderPane implements View {

    private Board board;
    private ChessButton[][] btn;
    private GridPane boardGrid;
    private BorderPane gamePane;

    public GUI() {
        this.board = new Board(this);
        this.gamePane = new BorderPane();
        this.boardGrid = new GridPane();
        createChessBoard();
        createLabels();
    }

    public void createChessBoard(){
        ButtonHandler handler = new ButtonHandler();
        this.btn = new ChessButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.btn[i][j] = new ChessButton(i, j);
                this.btn[i][j].setOnAction(handler);
                if ((i+j)%2 == 0){
                    this.btn[i][j].setStyle("-fx-background-color: #eaf9a4");
                }else{
                    this.btn[i][j].setStyle("-fx-background-color: #f7b033");
                }
                this.boardGrid.add(this.btn[i][j],j, i);

            }
        }

        this.gamePane.setCenter(this.boardGrid);
        this.gamePane.setMaxSize(500,500);
        this.gamePane.setMinSize(500,500);
        this.setCenter(this.gamePane);
        setStartingState();
    }

    public void setStartingState(){

        for (Piece p: this.board.whitePieces) {
            Position pos = p.getPosition();
            this.btn[pos.getTranslatedLine()][pos.getTranslatedCol()].setText(p.getPieceName()+"");
            this.btn[pos.getTranslatedLine()][pos.getTranslatedCol()].setTextFill(Color.WHITE);
        }

        for (Piece p: this.board.blackPieces) {
            Position pos = p.getPosition();
            this.btn[pos.getTranslatedLine()][pos.getTranslatedCol()].setText(p.getPieceName()+"");
            this.btn[pos.getTranslatedLine()][pos.getTranslatedCol()].setTextFill(Color.BLACK);
        }
    }


    public void createLabels(){
        HBox top = new HBox();
        HBox bottom = new HBox();
        VBox left = new VBox();
        VBox right = new VBox();

        for (int i = 0; i < 8; i++) {
            ChessLabel topLabel = new ChessLabel(""+((char)('a'+ i)));
            top.setAlignment(Pos.CENTER);
            top.getChildren().addAll(topLabel);
            ChessLabel bottomLabel = new ChessLabel(""+((char)('a'+ i)));
            bottom.setAlignment(Pos.CENTER);
            bottom.getChildren().addAll(bottomLabel);
            ChessLabel leftLabel = new ChessLabel(""+ (8 - i));
            left.setAlignment(Pos.CENTER);
            left.getChildren().addAll(leftLabel);
            ChessLabel rightLabel = new ChessLabel(""+ (8 - i));
            right.setAlignment(Pos.CENTER);
            right.getChildren().addAll(rightLabel);
        }


        this.gamePane.setTop(top);
        this.gamePane.setBottom(bottom);
        this.gamePane.setLeft(left);
        this.gamePane.setRight(right);


    }

    @Override
    public void possibleMoves(List<Position> moves) {
        for (Position pos: moves) {
            this.btn[pos.getTranslatedLine()][pos.getTranslatedCol()].setStyle("-fx-background-color: green");
        }
    }

    @Override
    public void possibleTakes(List<Position> takes) {
        for (Position pos: takes) {
            this.btn[pos.getTranslatedLine()][pos.getTranslatedCol()].setStyle("-fx-background-color: red");
        }
    }

    @Override
    public void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.btn[i][j].setText("");
                if ((i+j)%2 == 0){
                    this.btn[i][j].setStyle("-fx-background-color: #eaf9a4");
                }else{
                    this.btn[i][j].setStyle("-fx-background-color: #f7b033");
                }
            }
        }

        setStartingState();
    }

    class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            ChessButton b = (ChessButton)event.getSource();
            board.placeSelected(b.getCol(),b.getLine());
        }
    }


}
