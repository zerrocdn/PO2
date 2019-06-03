package pt.ipbeja.po2.horoscope;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Horoscope {

    private List<String> predictions;
    private Random random = new Random();
    private static final String PLACEHOLDER = "SIGNO";
    private static final String[] SIGN_NAME = {
            "Aries",
            "Taurus",
            "Gemini",
            "Cancer",
            "Leo",
            "Virgo",
            "Libra",
            "Scorpio",
            "Sagittarius",
            "Capricorn",
            "Aquarius",
            "Pisces"
    };

    public Horoscope(String path) {

        try {
           this.predictions = Files.readAllLines(Paths.get(path));


        } catch (IOException e) {
            System.out.print("FUCK YOU MORON, THERE IS NO FILE");
            e.printStackTrace();
        }



    }

    public String getRandomSign(String signs){
        int index = random.nextInt(predictions.size());
        String prediction = predictions.get(index);
        prediction = prediction.replace(PLACEHOLDER, signs);

        return prediction;
    }

    public static void main(String[] args) {
        Horoscope horoscope = new Horoscope("signo.txt");
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            String signName = SIGN_NAME[r.nextInt(SIGN_NAME.length)];
            System.out.println(signName + " - " + horoscope.getRandomSign(signName));

        }
    }


}
