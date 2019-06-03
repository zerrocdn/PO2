package pt.ipbeja.po2.dispenser.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipbeja.po2.dispenser.model.products.CD;
import pt.ipbeja.po2.dispenser.model.products.Camera;
import pt.ipbeja.po2.dispenser.model.products.Game;
import pt.ipbeja.po2.dispenser.model.products.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author DiogoPM
 * @version 23/04/2019
 */

class DispenserTest {

    private Dispenser dispenser;

    @BeforeEach
    void setup() {
        Register register = new Register("XYZ", "Bolhas company", 2006);
        this.dispenser = new Dispenser(register);
        Product bolachas = new CD("The Doors", 40, 3600, new ArrayList<>(), new ArrayList<>());
        this.dispenser.addProduct(bolachas);
        this.dispenser.addProduct(new Game("Arma 3", 100, "PC"));
        this.dispenser.addProduct(new Camera("Alpha 7", 50, "Sony"));
    }

    @Test
    void testInsertCoin() {

        //Dispenser dispenser = new Dispenser(40);

        // insertCoin devolve a quantidade inserida até ao momento
        int balance = dispenser.insertCoin(20);
        // verificamos que de facto a máquina aceitou os 20
        assertEquals(20, balance);

        // quando inserimos mais 10
        balance = dispenser.insertCoin(10);
        // verificamos que a máquina agora tem 30
        assertEquals(30, balance);

    }

    @Test
    void testCancelPurchase() {
        //Dispenser dispenser = new Dispenser(40);

        dispenser.insertCoin(20);
        int balance = dispenser.insertCoin(10);

        // Quantidades *antes* de cancelar para podermos ver se mudaram
        int productCount = dispenser.getProductStock();
        int salesMoney = dispenser.getSalesMoney();

        // Cancelamos a compra
        int dispensedMoney = dispenser.cancel();

        // Comparamos as quantidades antes e *depois* de cancelar
        assertEquals(balance, dispensedMoney);
        assertEquals(productCount, dispenser.getProductStock());
        assertEquals(salesMoney, dispenser.getSalesMoney());

        balance = dispenser.insertCoin(10);
        dispensedMoney = dispenser.cancel();
        assertEquals(balance, dispensedMoney);
    }

    @Test
    void testBuyProduct() {
        //Dispenser dispenser = new Dispenser(40);

        // quantidades antes de comprar
        int productsBefore = dispenser.getProductStock();
        int salesBefore = dispenser.getSalesMoney();

        dispenser.insertCoin(50);
        //dispenser.selectProduct("The Doors");
        int change = dispenser.buyProduct(0);

        // comparar antes com depois de comprar
        assertEquals(salesBefore + 40, dispenser.getSalesMoney());
        assertEquals(productsBefore - 1, dispenser.getProductStock());
        assertEquals(10, change);

        dispenser.insertCoin(50);
        //dispenser.selectProduct(1); // pastilhas, 50
        change = dispenser.buyProduct(1);
        assertEquals(0, change);

    }


    /**
     * Este teste não aparece no GP.
     * Visa testar que a máquina só aceita moedas válidas (5, 10, 20, 50)
     */
    @Test
    void testInsertInvalidCoins() {
        //Dispenser dispenser = new Dispenser(40);

        dispenser.insertCoin(20); // esta moeda é válida

        int balance = dispenser.insertCoin(7); // moeda inválida

        // verificamos que a máquina não aceitou a moeda e o balance manteve-se igual
        assertEquals(20, balance);

        // mais uns testes com moedas inválidas
        balance = dispenser.insertCoin(100);
        assertEquals(20, balance);
        balance = dispenser.insertCoin(24);
        assertEquals(20, balance);
    }

    @Test
    void testBuyProductInsufficientMoney() {
        //Dispenser dispenser = new Dispenser(40);
        dispenser.insertCoin(20);
        dispenser.insertCoin(5);

        // quantidades antes de tentar comprar
        int products = dispenser.getProductStock();
        int salesMoney = dispenser.getSalesMoney();

        // quando buyProduct é invocado com uma quantidade de dinheiro insuficiente
        // devolve o dinheiro em falta
        //dispenser.selectProduct(0);
        int missing = dispenser.buyProduct(0);

        // verificamos que de facto faltam 15 para comprar o produto
        assertEquals(-15, missing);

        // e que não foi vendido qualquer produto
        assertEquals(products, dispenser.getProductStock());
        assertEquals(salesMoney, dispenser.getSalesMoney());
    }

    @Test
    void testRegisterInformation() {

        assertEquals("XYZ", dispenser.getModel());
        assertEquals("Bolhas company", dispenser.getCompany());
        assertEquals(2006, dispenser.getYear());

    }

    @Test
    void getMostExpensiveTest() {
        Product expected = new Game("Arma 3", 100, "PC");
        Product expensive = dispenser.getMostExpensiveProduct();
        assertEquals(expected, expensive);
    }

    @Test
    void getCheapestTest() {
        Product expected = new CD("The Doors", 40, 3600, new ArrayList<>(), new ArrayList<>());
        Product cheapest = dispenser.getCheapestProduct();
        assertEquals(expected, cheapest);
    }

    @Test
    void getAveragePriceTest() {
        double avg = dispenser.getAveragePrice();
        double expected = 190 / 3.0;
        assertEquals(avg, expected);

    }

    @Test
    void getProductNamesTest() {
        String names = dispenser.getProductNames();
        assertEquals("The Doors\nArma 3\nAlpha 7\n", names);
    }

    @Test
    void getProductsCheaperThanTest() {
        List<Product> expected = Arrays.asList(
                new CD("The Doors", 40, 3600, new ArrayList<>(), new ArrayList<>()),
                new Camera("Alpha 7", 50, "Sony"));
        List<Product> cheaper = dispenser.getProductsCheaperThan(51);
        assertEquals(expected, cheaper);
    }



}



















