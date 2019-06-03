package pt.ipbeja.estig.chess.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Rei extends Piece{
    public Rei() {
        super.setText("R");
        ButtonHandler b = new ButtonHandler();
        super.setOnAction(b);
    }

    class ButtonHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            System.out.println("FUCK YOU");

        }
    }


}
