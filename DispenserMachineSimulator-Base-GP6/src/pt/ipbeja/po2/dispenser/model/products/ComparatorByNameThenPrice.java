package pt.ipbeja.po2.dispenser.model.products;

import java.util.Comparator;

public class ComparatorByNameThenPrice implements Comparator<Product> {


    @Override
    public int compare(Product o1, Product o2) {
        int cn = o1.getName().compareTo(o2.getName());
        return cn == 0 ? o1.price - o2.price: cn;
    }
}
