package pt.ipbeja.po2.dispenser.model.products;

import java.util.List;
import java.util.Objects;

/**
 * @author DiogoPM
 * @version 09/05/2019
 */

public class DVD extends Disc{

    private String director;

    public DVD(String name, int price, int seconds, List<Author> authors, String director) {
        super(name, price, seconds, authors);
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DVD)) return false;
        if (!super.equals(o)) return false;
        DVD dvd = (DVD) o;
        return Objects.equals(director, dvd.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), director);
    }

}