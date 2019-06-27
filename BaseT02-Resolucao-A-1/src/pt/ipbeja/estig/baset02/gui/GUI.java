package pt.ipbeja.estig.baset02.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pt.ipbeja.estig.baset02.model.Model;
import pt.ipbeja.estig.baset02.model.Position;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
public class GUI extends Application implements View {
	private final int BUTTON_SIZE = 50;

	private Model model;
	private GridPane buttonsGrid;
	private HBox labelPane;
	private List<Label> labels;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stgMain) throws Exception {
		this.model = new Model(this);

		stgMain.setTitle("T02 base");
		stgMain.setScene(this.createScene());
		stgMain.show();
	}

	private Scene createScene() {
		VBox vbxMain = new VBox();

		vbxMain.getChildren().addAll(this.createButtonsUI(), this.createLabelPane());
		return new Scene(vbxMain);

	}

	private Pane createButtonsUI() {
		this.buttonsGrid = new GridPane();
		this.buttonsGrid.setAlignment(Pos.CENTER);

		for (int row = 0; row < Model.N_LINES; row++) {
			for (int col = 0; col < Model.N_COLS; col++) {
				CellButton b = new CellButton("", new Position(row, col));
				b.setMinSize(BUTTON_SIZE, BUTTON_SIZE);
				this.buttonsGrid.add(b, col, row);
				b.setOnAction( (e) -> {
					CellButton cb = (CellButton)(e.getSource());
					this.model.positionSelected(cb.getPosition());
				});
			}
		}
		return buttonsGrid;
	}

	private HBox createLabelPane(){
		this.labelPane = new HBox();
		this.labels = new ArrayList<>();

		for (int i = 0; i < 7; i++) {
			Label l = new Label("0/0");
			l.setPrefWidth(50);
			this.labelPane.getChildren().add(l);
			this.labels.add(l);
		}

		return labelPane;
	}

	@Override
	public void updateText(List<List<Integer>> pieces) {
		for (int row = 0; row < Model.N_LINES; row++) {
			for (int col = 0; col < Model.N_COLS; col++) {
				CellButton b = (CellButton)this.buttonsGrid.getChildren().get(col + row * Model.N_COLS);
				b.setText("" + pieces.get(row).get(col));
			}
		}
	}

	@Override
	public void updateLabels(List<List<Integer>> tensTwenties) {
		for (int i = 0; i < 7; i++) {
			this.labels.get(i).setText(tensTwenties.get(i).get(0)+"/"+tensTwenties.get(i).get(1));
		}
	}
}