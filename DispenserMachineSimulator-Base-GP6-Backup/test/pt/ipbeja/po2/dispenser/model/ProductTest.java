package pt.ipbeja.po2.dispenser.model;

import org.junit.jupiter.api.Test;
import pt.ipbeja.po2.dispenser.model.products.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author DiogoPM
 * @version 02/05/2019
 */

class ProductTest {

    private List<Author> authors = Arrays.asList(new Author("João", "Portuguese"), new Author("Maria", "Brazilian"));
    private List<Song> songs = Arrays.asList(new Song("Light my fire", 180), new Song("LA Woman", 230));

    @Test
    void productListTest() {

        List<Product> productList = new ArrayList<>();

        Product p = new CD("The Doors", 50, 3600, authors, songs);
        productList.add(p);

        Product savedProduct = productList.get(0);
        assertEquals("The Doors", savedProduct.getName());
        assertEquals(50, savedProduct.getPrice());
        assertEquals(p, savedProduct);

        Product copy = new CD("The Doors", 50, 3600, authors, songs);
        assertEquals(p, copy);
        assertTrue(productList.contains(copy));

    }

    @Test
    void bookListTest() {

        List<Product> products = new ArrayList<>();
        products.add(new Book("The Martian", 300, "Penguin", authors));

        Book book = new Book("The Martian", 300, "Penguin", authors);

        assertTrue(products.contains(book));

        Book anotherBook = new Book("1984", 150, "Penguin", authors);

        products.add(new CD("1984", 150, 100, authors, Collections.singletonList(new Song("Cenas", 180))));

        // apesar do CD e o Book partilharem atributos, não são iguais
        assertFalse(products.contains(anotherBook));

    }



    @Test
    void productTaxTest() {

        Product book = new Book("1984", 150, "Penguin", Collections.singletonList(new Author("George Orwell", "British")));
        int expectedTax = 15;
        int expectedTotalPrice = 165;

        assertEquals(expectedTax, book.computeTax());
        assertEquals(expectedTotalPrice, book.priceWithTax());
    }

    @Test
    void cameraPriceAndTaxTest() {

        Product camera = new Camera("Alpha 7 III", 2000, "Sony");
        int actualTax = camera.computeTax();
        assertEquals(400, actualTax);

        int actualPrice = camera.priceWithTax();
        assertEquals(2400, actualPrice);



    }

    @Test
    void bookPriceAndTaxTest() {

        Product book = new Book("1984", 100, "Penguin", Collections.singletonList(new Author("George Orwell", "British")));
        int actualTax = book.computeTax();
        assertEquals(10, actualTax);

        int actualPrice = book.priceWithTax();
        assertEquals(110, actualPrice);
    }

    @Test
    void bookPriceOrder() {

        Product book = new Book("1984", 100, "Penguin", Collections.singletonList(new Author("George Orwell", "British")));
        Product book2 = new Book("1984", 110, "Penguin", Collections.singletonList(new Author("George Orwell", "British")));

        int actual = book.compareTo(book2);
        int expected = -10;

        assertEquals(expected, actual);
    }




}











