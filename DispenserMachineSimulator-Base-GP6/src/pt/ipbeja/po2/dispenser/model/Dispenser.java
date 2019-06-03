package pt.ipbeja.po2.dispenser.model;

import pt.ipbeja.po2.dispenser.model.products.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DiogoPM
 * @version 23/04/2019
 */

public class Dispenser {

    // Código de erro em caso de tentativa de compra sem produto seleccionado
    public static final int NO_PRODUCT_SELECTED_ERROR = Integer.MIN_VALUE;

    private MoneyBox moneyBox;
    private Register register;


    private Product selectedProduct = null;
    private List<Product> products;


    public Dispenser(Register register) {
        this.moneyBox = new MoneyBox();
        this.register = register;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }




    // Podemos ter vários métodos com o mesmo nome com parâmetros diferentes e que realizam a mesma tarefa
    // Qualquer um pode ser útil consoante o caso de uso
    // Veja os 3 métodos selectProduct abaixo

    /**
     * Select a product
     * @param productIndex Index of the product on the list
     * @return The selected product
     */
    public Product selectProduct(int productIndex) {
        if(products.size() == 0 || productIndex < 0 || productIndex >= products.size()){
            return null; // se o índice não for válido, nem vale a pena procurar e devolvemos null
        }
        selectedProduct = products.get(productIndex);
        return selectedProduct;
    }

    /**
     * Selects a product
     * @param productName Name of the product
     * @return The selected product
     */
    public Product selectProduct(String productName) {
        for (Product product : products) {
            if(product.getName().equals(productName)) {
                selectedProduct = product;
                return selectedProduct; // quando encontramos um Product com aquele nome, devolvemos o Product
            }
        }
        return null; // se nenhum produto com aquele nome não existir, devolvemos null
    }

    /**
     * Selects a product
     * @param product The product to select
     * @return The selected product
     */
    public Product selectProduct(Product product) {
        // Não devemos/podemos confiar que o product que chega como parâmetro existe na lista!

        if(products.size() == 0){
            return null;
        }
        int index = products.indexOf(product); // devolve -1 se não existir
        return selectProduct(index); // Podemos inclusive fazer uso dos outros métodos de selectProduct
    }

    /**
     * Inserts a coin
     * @param coin The coin value
     * @return The total inserted amount so far
     */
    public int insertCoin(int coin) {
        return this.moneyBox.insertCoin(coin);
    }



    /**
     * Attempt to purchase a product
     * @return 0 (zero) or positive amount (change) if the money inserted was sufficient to buy the products.
     * A negative amount means how much more money is necessary for a purchase and no products were dispensed.
     */
    public int buyProduct() {
        if(selectedProduct == null) {
            // podemos tratar esta situação com um código de erro (um valor que não ocorrerá em operação normal)
            return NO_PRODUCT_SELECTED_ERROR; // neste caso foi escolhido o valor míninmo que um int pode tomar (-2^31)
        }

        int difference = moneyBox.buyProduct(selectedProduct.getPrice());
        if(difference >= 0) {
            dispenseProduct();
        }
        return difference;
    }


    /**
     * Performs a sale
     */
    private void dispenseProduct() {
        this.products.remove(selectedProduct); // o método remove faz uso do equals implementado em Product
        selectedProduct = null;
    }

    /**
     * Cancels the current operation and returns the inserted money
     * @return The inserted money
     */
    public int cancel() {
        selectedProduct = null;
        return this.moneyBox.cancel();
    }


    public List<Product> getProducts() {
        return products;
    }

    public int getProductStock() {
        return products.size();
    }

    public int getSalesMoney() {
        return moneyBox.getSalesMoney();
    }

    public String getModel() {
        return register.getModel();
    }

    public String getCompany() {
        return register.getCompany();
    }

    public int getYear() {
        return register.getYear();
    }



    public Product getMostExpensiveProduct() {
        Product expensive = null;
        for (Product product : products) {
            if(expensive == null || expensive.getPrice() < product.getPrice()) {
                expensive = product;
            }
        }
        return expensive;
    }

    public Product getCheapestProduct() {
        Product cheapest = null;
        for (Product product : products) {
            if(cheapest == null || cheapest.getPrice() > product.getPrice()) {
                cheapest = product;
            }
        }
        return cheapest;
    }

    public double getAveragePrice() {
        if(products.size() == 0) return 0; // não podemos dividir por 0

        int sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return (double)sum / products.size();
    }

    public String getProductNames() {
        String names = ""; // StringBuilder deve ser usado quando queremos concatenar muitas Strings
        for (Product product : products) {
            names += product.getName() + '\n';
        }
        return names;

        /*
         // Idealmente, a class StringBuilder deve ser usada quando queremos concatenar muitas Strings (ver o warning em "names += product.getName() + '\n'" acima)
        StringBuilder sb = new StringBuilder();
        for (Product product : products) {
            sb.append(product.getName()).append('\n');
        }
        return sb.toString();
        */
    }


    public List<Product> getProductsCheaperThan(int price) {

        List<Product> cheaper = new ArrayList<>();
        for (Product product : products) {
            if(product.getPrice() < price) {
                cheaper.add(product);
            }
        }
        return cheaper;
    }





    //-------- alternativa a buyProduct/dispenseProduct -----------//
    // Em vez de usarmos um método à parte para seleccionar o produto, podemos passar logo o índice do produto como arg

    /**
     * Attempt to purchase a product
     * @param productIndex Product index
     * @return 0 (zero) or positive amount (change) if the money inserted was sufficient to buy the products.
     * A negative amount means how much more money is necessary for a purchase and no products were dispensed.
     */
    public int buyProduct(int productIndex) {
        Product selectedProduct = products.get(productIndex);
        int difference = moneyBox.buyProduct(selectedProduct.getPrice());
        if(difference >= 0) {
            dispenseProduct(selectedProduct);
        }
        return difference;
    }

    private void dispenseProduct(Product product) {
        products.remove(product);
    }
}
















