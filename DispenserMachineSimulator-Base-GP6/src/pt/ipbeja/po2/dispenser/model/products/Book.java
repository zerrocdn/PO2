package pt.ipbeja.po2.dispenser.model.products;

import java.util.List;
import java.util.Objects;

/**
 * @author DiogoPM
 * @version 09/05/2019
 */

public class Book extends ProductWithAuthor {

    public static final double TAX = 0.1;
    private String publisher;

    public Book(String name, int price, String publisher, List<Author> authors) {
        super(name, price, authors);
        this.publisher = publisher;
    }

    @Override
    public int computeTax() {
        return (int) (TAX * price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(publisher, book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), publisher);
    }
}