package pt.ipbeja.po2.dispenser.model.products;

import java.util.List;
import java.util.Objects;

/**
 * @author DiogoPM
 * @version 09/05/2019
 */

public abstract class ProductWithAuthor extends Product {

    private List<Author> authors;

    public ProductWithAuthor(String name, int price, List<Author> authors) {
        super(name, price);
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductWithAuthor)) return false;
        if (!super.equals(o)) return false;
        ProductWithAuthor that = (ProductWithAuthor) o;
        return Objects.equals(authors, that.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), authors);
    }

    @Override
    public int compareTo(Product o) {
        int comp = super.compareTo(o);
        if (comp != 0 || !(o instanceof ProductWithAuthor)) {
            return comp;
        }

        ProductWithAuthor other = (ProductWithAuthor) o;
        return authors.size() - other.authors.size();
    }
}
























