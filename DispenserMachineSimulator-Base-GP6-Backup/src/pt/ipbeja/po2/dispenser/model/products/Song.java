package pt.ipbeja.po2.dispenser.model.products;

import java.util.Objects;

/**
 * @author DiogoPM
 * @version 09/05/2019
 */

public class Song {

    private String title;
    private int time;

    public Song(String title, int time) {
        this.title = title;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return time == song.time &&
                Objects.equals(title, song.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time);
    }
}