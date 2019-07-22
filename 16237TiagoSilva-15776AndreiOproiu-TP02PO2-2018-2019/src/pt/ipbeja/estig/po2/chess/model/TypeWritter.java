package pt.ipbeja.estig.po2.chess.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public String readPlays(){
        List<String> rawData = new ArrayList<>();
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filename));

            while ((line = br.readLine()) != null){
                rawData.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rawData.get(rawData.size() - 1);
    }

    public List<String> readInitialPlaces(){
        List<String> rawData = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("initial_places.txt"));
            String line = "";
            while ((line = br.readLine()) != null){
                rawData.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rawData;
    }
}
