package pt.ipbeja.po2.dispenser.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author DiogoPM
 * @version 11/04/2019
 */

class DispenserTest {

    @Test
    void testInsertCoin() {

        Dispenser dispenser = new Dispenser(40);

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
        Dispenser dispenser = new Dispenser(40);

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
        Dispenser dispenser = new Dispenser(40);

        // quantidades antes de comprar
        int productsBefore = dispenser.getProductStock();
        int salesBefore = dispenser.getSalesMoney();

        dispenser.insertCoin(50);
        int change = dispenser.buyProduct();

        // comparar antes com depois de comprar
        assertEquals(salesBefore + 40, dispenser.getSalesMoney());
        assertEquals(productsBefore - 1, dispenser.getProductStock());
        assertEquals(10, change);

        dispenser.insertCoin(50);
        change = dispenser.buyProduct();
        assertEquals(10, change);

    }


    /**
     * Este teste não aparece no GP.
     * Visa testar que a máquina só aceita moedas válidas (5, 10, 20, 50)
     */
    @Test
    void testInsertInvalidCoins() {
        Dispenser dispenser = new Dispenser(40);

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
        Dispenser dispenser = new Dispenser(40);
        dispenser.insertCoin(20);
        dispenser.insertCoin(5);

        // quantidades antes de tentar comprar
        int products = dispenser.getProductStock();
        int salesMoney = dispenser.getSalesMoney();

        // quando buyProduct é invocado com uma quantidade de dinheiro insuficiente
        // devolve o dinheiro em falta
        int missing = dispenser.buyProduct();

        // verificamos que de facto faltam 15 para comprar o produto
        assertEquals(-15, missing);

        // e que não foi vendido qualquer produto
        assertEquals(products, dispenser.getProductStock());
        assertEquals(salesMoney, dispenser.getSalesMoney());
    }


    @Test
    void testSetProductPrice() {

        Dispenser dispenser = new Dispenser(40);

        // tentamos mudar o preço para um múltiplo de 10
        dispenser.setProductPrice(20);
        // verificamos que resultou
        assertEquals(20, dispenser.getProductPrice());

        // tentamos mudar o preço para um valor que não é multiplo de 10
        dispenser.setProductPrice(15);

        // se resultasse, poderiamos comprar um produto com 15 e daria troco 0
        dispenser.insertCoin(10);
        dispenser.insertCoin(5);

        // verificamos que não foi possível pois o preço não era válido
        int result = dispenser.buyProduct();
        assertNotEquals(0, result);

    }

    @Test
    void testBuyMultipleProducts() {
        Dispenser dispenser = new Dispenser(40);

        // colocamos a quantidade de produtos a comprar (3 x 40)
        dispenser.setAmountToBuy(3);
        dispenser.insertCoin(50);
        dispenser.insertCoin(50);
        dispenser.insertCoin(50);
        // o troco terá de ser (150 - (3 x 40) = 30)
        int change = dispenser.buyProduct();
        assertEquals(30, change);
    }





}



















