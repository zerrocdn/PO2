import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {


    private File filePath;
    private List<String> textLines;


    public static void main(String[] args)throws Exception
    {

        Application.launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        FileChooser fileChooser = new FileChooser();
        this.filePath = fileChooser.showOpenDialog(primaryStage);
        readFile();
        writeFile();
        Platform.exit();

    }


    public List<String> readFile() throws IOException {

         this.textLines = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(this.filePath));
        String fileLine;

        while ((fileLine = br.readLine())!= null){
            textLines.add(fileLine);
        }



        System.out.println(textLines);

        return textLines;
    }

    public void writeFile(){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("teste.txt"));
            for (String line : textLines) {
                bw.write(" " + line);
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
