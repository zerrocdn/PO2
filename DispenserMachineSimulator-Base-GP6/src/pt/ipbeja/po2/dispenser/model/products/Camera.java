package pt.ipbeja.po2.dispenser.model.products;

import java.util.Objects;

/**
 * @author DiogoPM
 * @version 09/05/2019
 */

public class Camera extends Product {


    public static final double TAX = 0.2;

    private String brand;

    public Camera(String name, int price, String brand) {
        super(name, price);
        this.brand = brand;
    }

    @Override
    public int computeTax() {
        return (int) (price * TAX);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Camera)) return false;
        if (!super.equals(o)) return false;
        Camera camera = (Camera) o;
        return Objects.equals(brand, camera.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), brand);
    }

    @Override
    public String toString() {
        return "Camera{" +
                "brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Product o) {

        int diff = super.compareTo(o);

        if(diff == 0 && o instanceof Camera) {
            diff = this.brand.compareTo(((Camera) o).brand);
        }


        return diff;
    }
}