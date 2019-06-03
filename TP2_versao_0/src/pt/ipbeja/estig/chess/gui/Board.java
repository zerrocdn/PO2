package pt.ipbeja.estig.chess.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.ipbeja.estig.chess.View;
import pt.ipbeja.estig.chess.model.Model;
import pt.ipbeja.estig.chess.model.Rei;

public class Board extends BorderPane implements View {

    private final int BOARD_SIZE = 8;
    private GridPane board;
    private Model model;
    private BorderPane gamePane;
    private Label gameMenu;


    public Board() {
        this.board = new GridPane();
        this.model = new Model(this);
        this.gameMenu = new Label();
        this.gamePane = new BorderPane();
        this.gamePane.setMaxSize(500,500);
        this.setMinSize(500,500);
        this.setCenter(this.gamePane);
        createBoard();
        createLabels();
        setSides();
        setInicialPositions();

    }

    public void createBoard(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Button btn = new Button();
                //this.r= new Rei();
                btn.setMinSize(50,50);
                if((i+j) % 2 == 0){
                    btn.setStyle("-fx-background-color: white");
                }else{
                    btn.setStyle("-fx-background-color: black");
                }
                this.board.add(btn, j, i);
            }
        }
        this.gamePane.setCenter(this.board);
    }

    public void setInicialPositions(){
        Rei BKing= new Rei(this);
        Rei WKing= new Rei(this);
        this.board.add(WKing,4, 0);
        this.board.add(BKing,4, 7);
    }



    public void setSides(){
        VBox vBox = new VBox();
        vBox.setMinSize(200,550);
        this.setLeft(vBox);
        Label title = new Label("Chess Game");
        HBox hBoxTitle = new HBox();
        hBoxTitle.getChildren().add(gameMenu);
        hBoxTitle.setAlignment(Pos.CENTER);
        HBox hBoxPlayer = new HBox();
        HBox hBoxScore = new HBox();

        vBox.getChildren().addAll(hBoxTitle,hBoxPlayer , hBoxScore);
    }

    public void setHistory(String position){
        this.gameMenu.setText(position);
    }

    public void createLabels(){
        VBox vBoxLeft = new VBox();
        HBox hBoxTop = new HBox();
        hBoxTop.setAlignment(Pos.CENTER);
        VBox vBoxRight = new VBox();
        HBox hBoxBottom = new HBox();
        hBoxBottom.setAlignment(Pos.CENTER);

        for (int i = 1; i <= BOARD_SIZE; i++) {
            Label topLabel = new Label((char)(96+i)+"");
            topLabel.setMinSize(50, 50);
            topLabel.setAlignment(Pos.CENTER);
            hBoxTop.getChildren().add(topLabel);

            Label bottomLabel = new Label((char)(96+i)+"");
            bottomLabel.setMinSize(50, 50);
            bottomLabel.setAlignment(Pos.CENTER);
            hBoxBottom.getChildren().add(bottomLabel);

            Label leftLabel = new Label((BOARD_SIZE+1-i)+"");
            leftLabel.setMinSize(50, 50);
            leftLabel.setAlignment(Pos.CENTER);
            vBoxLeft.getChildren().add(leftLabel);

            Label rightLabel = new Label((BOARD_SIZE+1-i)+"");
            rightLabel.setMinSize(50, 50);
            rightLabel.setAlignment(Pos.CENTER);
            vBoxRight.getChildren().add(rightLabel);
        }
        this.gamePane.setTop(hBoxTop);
        this.gamePane.setBottom(hBoxBottom);
        this.gamePane.setLeft(vBoxLeft);
        this.gamePane.setRight(vBoxRight);
    }


    @Override
    public void print(String string) {
        this.gameMenu.setText(string);
    }
}
