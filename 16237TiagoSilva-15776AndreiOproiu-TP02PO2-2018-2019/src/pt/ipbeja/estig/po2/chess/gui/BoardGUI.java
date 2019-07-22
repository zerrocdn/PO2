package pt.ipbeja.estig.po2.chess.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.ipbeja.estig.po2.chess.model.BoardModel;
import pt.ipbeja.estig.po2.chess.model.Position;

import java.util.ArrayList;
import java.util.List;

public class BoardGUI extends BorderPane implements View{

    private BorderPane centerPane;
    private GridPane boardPane;

    private BoardModel model;
    private BoardButton boardButtons[][];
    private ArrayList<Label> historyLabels;
    private VBox historyPane;

    public BoardGUI() {
        this.model = new BoardModel(this);
        createCenterPanes();
    }

    private void createCenterPanes(){
        this.centerPane = new BorderPane();
        createBoardPane();
        this.centerPane.setCenter(this.boardPane);

        this.centerPane.setLeft(createLinesPane());
        this.centerPane.setRight(createLinesPane());

        this.centerPane.setTop(createColumnsPane());
        this.centerPane.setBottom(createColumnsPane());

        this.setRight(createHistoryPane());

        this.setCenter(this.centerPane);
    }

    private void createBoardPane(){
        this.boardPane = new GridPane();
        this.boardButtons = new BoardButton[this.model.BOARD_SIZE][this.model.BOARD_SIZE];

        for (int line = 0; line < this.model.BOARD_SIZE; line++){
            for (int col = 0; col < this.model.BOARD_SIZE; col++) {
                this.boardButtons[line][col] = new BoardButton(line, col);

                if (this.model.getPiece(line, col) != null) {
                    this.boardButtons[line][col].setText(this.model.getPiece(line, col).getPieceFullName());
                }

                this.boardButtons[line][col].setOnAction(new ButtonHandler());
                this.boardPane.add(this.boardButtons[line][col], col, line);
            }
        }
    }

    private VBox createLinesPane(){
        VBox vBo = new VBox();
        vBo.setAlignment(Pos.CENTER);

        for (int i = 0; i < this.model.BOARD_SIZE; i++) {
            Label label = new Label(this.model.BOARD_SIZE - i + "");
            label.setPrefSize(50, 50);
            label.setAlignment(Pos.CENTER);

            vBo.getChildren().add(label);
        }

        return vBo;
    }

    private HBox createColumnsPane(){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);

        for (char i = 'a'; i <= 'h'; i++) {
            Label label = new Label( i + "");
            label.setPrefSize(50, 50);
            label.setAlignment(Pos.CENTER);

            hBox.getChildren().add(label);
        }

        return hBox;
    }

    private VBox createHistoryPane(){
        this.historyPane = new VBox();

        this.historyLabels = new ArrayList<>();

        Label title = new Label("Jogadas: ");
        title.setStyle("-fx-font-weight: bold");

        this.historyPane.getChildren().add(title);

        return this.historyPane;
    }

    @Override
    public void markPossibleMoves(List<Position> moves) {
        for (Position p: moves ) {
            this.boardButtons[p.getTranslatedLine()][p.getTranslatedCol()].setAsMark();
        }
    }

    @Override
    public void markPossibleTakes(List<Position> takes) {
        for (Position p: takes ) {
            this.boardButtons[p.getTranslatedLine()][p.getTranslatedCol()].setAsTake();
        }
    }

    @Override
    public void resetButtonsBackground() {
        for (int line = 0; line < this.model.BOARD_SIZE; line++){
            for (int col = 0; col < this.model.BOARD_SIZE; col++) {
                if (this.model.getPiece(line, col) != null) {
                    this.boardButtons[line][col].setText(this.model.getPiece(line, col).getPieceFullName());
                }
                else {
                    this.boardButtons[line][col].setText("");
                }
                this.boardButtons[line][col].resetbackground();
            }
        }
    }

    @Override
    public void updateHistory(String play) {
        System.out.println(play.charAt(play.length() - 1));
        if (play.charAt(play.length() - 1) == ' '){
            this.historyLabels.add(new Label(play));
            this.historyPane.getChildren().add(historyLabels.get(historyLabels.size() - 1));
        }else {
            historyLabels.get(historyLabels.size() - 1).setText(play);
        }
    }

    class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            BoardButton b = (BoardButton) event.getSource();
            System.out.println("Click on: " + b.getLine() + " " + b.getCol());

            model.placeSelected(b.getLine(), b.getCol());
        }
    }




}
