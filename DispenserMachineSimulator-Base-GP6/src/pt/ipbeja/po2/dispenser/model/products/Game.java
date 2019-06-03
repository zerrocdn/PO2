package pt.ipbeja.po2.dispenser.model.products;

import java.util.Objects;

/**
 * @author DiogoPM
 * @version 09/05/2019
 */

public class Game extends Product {

    private String platform;

    public Game(String name, int price, String platform) {
        super(name, price);
        this.platform = platform;
    }

    @Override
    public int computeTax() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        if (!super.equals(o)) return false;
        Game camera = (Game) o;
        return Objects.equals(platform, camera.platform);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), platform);
    }

    @Override
    public int compareTo(Product o) {
        return super.compareTo(o);
    }
}