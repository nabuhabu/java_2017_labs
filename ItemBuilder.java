//package lab2;

//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
//import lab2.serializers.LocalDateDeserializer;

import java.time.LocalDate;
import java.util.regex.Pattern;



public class ItemBuilder {
    private String name;
    private String type;
    private LocalDate productionDate;
    private LocalDate expiryDate;
    private double price;
    private int id;

    public final static String NAME_PATTERN = "([A-Z][A-Za-z1-9 ]{0,100}?)";

    public ItemBuilder() {
        this.name = "";
        this.type = "";
        this.productionDate = LocalDate.now();
        this.expiryDate = LocalDate.now();
        this.price = 0;
        this.id = 0;
    }

    public ItemBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ItemBuilder setName(String name) {
        if (!Pattern.matches(NAME_PATTERN, name)) {
            throw new IllegalArgumentException("Input correct name, please");
        }
        this.name = name;
        return this;
    }

    public ItemBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public ItemBuilder setProductionDate(int year, int month, int day) throws IllegalArgumentException {
        LocalDate date = LocalDate.of(year, month, day);
        if(date.isAfter(LocalDate.now())) throw new IllegalArgumentException("Input correct date, please. The product couldn`t have been produced before today. Be honest");
        this.productionDate = date;
        return this;
    }


    public ItemBuilder setProductionDate(LocalDate date) throws IllegalArgumentException {
        if(date.isAfter(LocalDate.now())) throw new IllegalArgumentException("Input correct date before today, please.");
        this.productionDate = date;
        return this;
    }

    public ItemBuilder setExpirationDays(int expirationDays) throws IllegalArgumentException {
        if (expirationDays < 0) {
            throw new IllegalArgumentException("Input correct expiry period, please");
        }
        this.expiryDate = productionDate.plusDays(expirationDays);
        return this;
    }


    public ItemBuilder setExpiryDate(LocalDate expiryDate) throws IllegalArgumentException {
        if (expiryDate.isBefore(productionDate)) {
            throw new IllegalArgumentException("Input correct expiry date after production date, please");
        }
        this.expiryDate = expiryDate;
        return this;
    }

    public ItemBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ItemBuilder fromString(String input) {
        this.setName(Regex.getRegexGroup(input, Regex.NAME_REGEX));
        this.setPrice(Double.parseDouble(Regex.getRegexGroup(input, Regex.PRICE_REGEX)));
        this.setType(Regex.getRegexGroup(input, Regex.TYPE_REGEX));
        this.setExpiryDate(LocalDate.parse(Regex.getRegexGroup(input, Regex.EXPIRATION_REGEX)));
        return this;
    }




    public Goods build() {
        return new Goods(id, name, type, price, productionDate, expiryDate);
    }
}