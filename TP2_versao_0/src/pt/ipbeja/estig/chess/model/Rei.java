package pt.ipbeja.estig.chess.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import pt.ipbeja.estig.chess.View;

public class Rei extends Piece{
    private View view;
    public Rei(View view) {
        super.setText("R");
        ButtonHandler b = new ButtonHandler();
        super.setOnAction(b);
        this.view = view;
    }

    class ButtonHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            view.print("fddffggf");

        }
    }


}
