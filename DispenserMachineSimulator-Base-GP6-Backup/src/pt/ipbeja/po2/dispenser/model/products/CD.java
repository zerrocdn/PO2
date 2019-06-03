package pt.ipbeja.po2.dispenser.model.products;

import java.util.List;
import java.util.Objects;

/**
 * @author DiogoPM
 * @version 09/05/2019
 */

public class CD extends Disc {

    private List<Song> songs;

    public CD(String name, int price, int seconds, List<Author> authors, List<Song> songs) {
        super(name, price, seconds, authors);
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CD)) return false;
        if (!super.equals(o)) return false;
        CD cd = (CD) o;
        return Objects.equals(songs, cd.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), songs);
    }


}