package pt.ipbeja.po2.dispenser.model.products;

import java.util.List;
import java.util.Objects;

/**
 * @author DiogoPM
 * @version 09/05/2019
 */

public abstract class Disc extends ProductWithAuthor {


    private int duration;


    public Disc(String name, int price, int duration, List<Author> authors) {
        super(name, price, authors);
        this.duration = duration;
    }


    @Override
    public int computeTax() {
        int factor = duration / 3600;
        return (int) ((factor * 0.1) * price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disc)) return false;
        if (!super.equals(o)) return false;
        Disc disc = (Disc) o;
        return duration == disc.duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), duration);
    }
}