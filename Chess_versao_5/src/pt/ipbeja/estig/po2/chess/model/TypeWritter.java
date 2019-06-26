package pt.ipbeja.estig.po2.chess.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TypeWritter {

    private String filename;
    private BufferedWriter writter;
    private int counter;


    public TypeWritter(String filename) {
        this.counter = 0;

        this.filename = filename;
        try {
            this.writter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public void writePlay(Piece p, Position pos, int plays) {
        if (counter % 2 == 0){
            try {
                this.writter.append(plays + ". " + p.pieceName + pos.toString() + " ");
                this.writter.flush();
                counter++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                this.writter.append(p.pieceName + pos.toString() + "\n");
                this.writter.flush();
                counter--;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
