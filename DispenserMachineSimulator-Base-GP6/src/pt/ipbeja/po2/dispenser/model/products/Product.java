package pt.ipbeja.po2.dispenser.model.products;

import java.util.Objects;

/**
 * @author DiogoPM
 * @version 02/05/2019
 */

public abstract class Product implements Comparable<Product>{

    protected String name;
    protected int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public abstract int computeTax();

    public int priceWithTax() {
        return price + computeTax();
    }

    @Override
    public boolean equals(Object o) {
        // Deve gerar este método com recurso à ferramenta em generate -> equals & hash
        // Atenção que se mudar os atributos da class, terá de apagar estes métodos e voltar a gerar!
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return price == product.price &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public int compareTo(Product o) {

        int diff = this.price - o.price;
            if (diff == 0){
                diff = this.name.compareTo(o.name);
            }

        return diff;
    }
}













