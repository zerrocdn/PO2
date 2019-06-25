package pt.ipbeja.estig.po2.chess.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pt.ipbeja.estig.po2.chess.View;
import pt.ipbeja.estig.po2.chess.model.BoardModel;
import pt.ipbeja.estig.po2.chess.model.Piece;
import pt.ipbeja.estig.po2.chess.model.Position;

import java.util.List;

public class GUI extends BorderPane implements View {

    private BoardModel boardModel;
    private ChessButton[][] board;
    private GridPane boardGrid;
    private BorderPane gamePane;

    public GUI() {
        this.boardModel = new BoardModel(this);
        this.gamePane = new BorderPane();
        this.boardGrid = new GridPane();

        createChessBoard();
    }

    public void createChessBoard(){
        ButtonHandler handler = new ButtonHandler();
        this.board = new ChessButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new ChessButton(i, j);
                this.board[i][j].setOnAction(handler);
                if ((i+j)%2 == 0){
                    this.board[i][j].setStyle("-fx-background-color: #f9e48e");
                }else{
                    this.board[i][j].setStyle("-fx-background-color: #725d04");
                }
                this.boardGrid.add(this.board[i][j],j, i);

            }
        }

        this.gamePane.setCenter(this.boardGrid);
        this.gamePane.setMaxSize(500,500);
        this.gamePane.setMinSize(500,500);
        this.setCenter(this.gamePane);

        setStartingState();

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

    public void setStartingState(){

        for (Piece p: this.boardModel.whitePieces) {
            Position pos = p.getPosition();
            this.board[pos.getTranslatedLine()][pos.getTranslatedCol()].setText(p.getPieceName()+"");
            this.board[pos.getTranslatedLine()][pos.getTranslatedCol()].setTextFill(Color.WHITE);
        }

        for (Piece p: this.boardModel.blackPieces) {
            Position pos = p.getPosition();
            this.board[pos.getTranslatedLine()][pos.getTranslatedCol()].setText(p.getPieceName()+"");
            this.board[pos.getTranslatedLine()][pos.getTranslatedCol()].setTextFill(Color.BLACK);
        }
    }

    @Override
    public void possibleMoves(List<Position> moves) {

        for (Position pos: moves) {
            this.board[pos.getTranslatedLine()][pos.getTranslatedCol()].setGraphic(new Circle(5,Color.GREEN));
        }

    }

    @Override
    public void possibleTakes(List<Position> takes) {

    }

    class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            ChessButton b = (ChessButton)event.getSource();
        }
    }
}
