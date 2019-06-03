package pt.ipbeja.po2.dispenser.model;

import java.util.Arrays;

/**
 * @author DiogoPM
 * @version 02/05/2019
 */

public class MoneyBox {

    // array com as moedas válidas
    private static final int[] validCurrency = {5, 10, 20, 50};

    // array com a quantidade de moedas de cada moeda válida (30 moedas de 5, 20 de 10, etc.)
    private int[] coinBox = {30, 20, 10, 5};
    // identico para as quantidades de moedas que o utilizador inseriu
    private int[] insertedCoinBox = {0, 0, 0, 0};

    private int insertedMoney = 0;
    private int salesMoney = 0;


    /**
     * Inserts a coin
     * @param coin The coin value
     * @return The total inserted amount so far
     */
    public int insertCoin(int coin) {
        // só deixamos inserir a moeda se esta for válida
        if(isCoinValid(coin)) {
            this.insertedMoney += coin;

            // incrementamos o número de moedas desse valor no array
            insertedCoinBox[getCoinSlot(coin)]++;
        }
        return this.insertedMoney;
    }

    /**
     * Get the slot index for a given coin value
     * @param coin The coin to be evaluated
     * @return the index or -1 if not a valid coin
     */
    private int getCoinSlot(int coin) {
        for (int i = 0; i < validCurrency.length; i++) {
            if(validCurrency[i] == coin) return i;
        }
        return -1;
    }

    /**
     * Validate coin
     * @param coin Coin to validate
     * @return True if coin is valid, false otherwise
     */
    private boolean isCoinValid(int coin) {
        return getCoinSlot(coin) > -1;
        /*
        // alternativa:
        for (int validCoin : validCurrency) {
            if(coin == validCoin) return true;
        }
        return false;
        */
    }

    /**
     * Attempt to purchase a product
     * @param price Product price
     * @return 0 (zero) or positive amount (change) if the money inserted was sufficient to buy the products.
     * A negative amount means how much more money is necessary for a purchase and no products were dispensed.
     */
    public int buyProduct(int price) {

        int difference = getDifference(price);
        // só podemos efetuar a venda se houver dinheiro suficiente
        // também só poderemos fazer a vendas se houver produtos!
        // TODO criar um teste para essa situação e depois implementar código para que o passe
        if(difference >= 0) {
            makeSale(price);
            dispenseChange(difference);
        }
        return difference;
    }

    /**
     * Calculates the difference between the amount of money inserted and
     * the total price of the purchase. 0 or positive if enough money, negative otherwise
     * @return The difference
     */
    private int getDifference(int price) {
        return this.insertedMoney - price;
    }

    /**
     * Performs a sale
     * @param price Product price
     */
    private void makeSale(int price) {
        collectInsertedCoins(); // vamos recolher as moedas inseridas

        this.insertedMoney = 0;
        this.salesMoney += price; // somar o dinheiro da venda
        // this.productStock--; // não precisamos disto
    }

    /**
     * Cancels the current operation and returns the inserted money
     * @return The inserted money
     */
    public int cancel() {
        int temp = this.insertedMoney;
        this.insertedMoney = 0;
        clearInsertedCoinBox();
        return temp;
    }

    /**
     * Clears the inserted coin box
     * (modela a devolução das moedas inseridas pelo utilizador)
     */
    private void clearInsertedCoinBox() {
        Arrays.fill(insertedCoinBox, 0);
    }

    /**
     * Collects the inserted coins
     */
    private void collectInsertedCoins() {
        for (int slot = 0; slot < insertedCoinBox.length; slot++) {
            // colocar na caixa da maquina a quantidade de moedas que estavam na caixa do user
            coinBox[slot] += insertedCoinBox[slot];
            insertedCoinBox[slot] = 0;
        }
        this.insertedMoney = 0;
    }

    /**
     * Dispense change
     * @param amount The amount of money to be returned to the user
     */
    private void dispenseChange(int amount) {

        // vamos começar pelas moedas de maior valor
        // iteramos enquanto ainda houver dinheiro em falta a devolver
        for (int slot = coinBox.length - 1; slot >= 0 && amount > 0; slot--) {
            int coinValue = validCurrency[slot]; // valor da moeda para dado indice (50, 20, 10, 5)

            // enquanto tivermos moedas desse valor e o valor for *inferior* ao do troco em falta
            while (coinBox[slot] > 0 && coinValue <= amount) {
                // tiramos uma moeda da caixa
                coinBox[slot]--;
                // e decrementamos o valor dessa moeda ao troco em falta
                amount -= coinValue;
            }
        }
        // pode acontecer que não se consiga dar troco suficiente!
    }

    public int getSalesMoney() {
        return salesMoney;
    }

    public int[] getInsertedCoinBox() {
        return insertedCoinBox;
    }

    public int[] getCoinBox() {
        return coinBox;
    }
}












