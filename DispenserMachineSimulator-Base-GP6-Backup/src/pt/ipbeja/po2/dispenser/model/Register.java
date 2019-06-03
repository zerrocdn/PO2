package pt.ipbeja.po2.dispenser.model;

/**
 * @author DiogoPM
 * @version 02/05/2019
 */

public class Register {

    private String model;
    private String company;
    private int year;

    public Register(String model, String company, int year) {
        this.model = model;
        this.company = company;
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public String getCompany() {
        return company;
    }

    public int getYear() {
        return year;
    }
}