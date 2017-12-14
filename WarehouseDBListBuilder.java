

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class WareHouseDBListBuilder {
    private List<Goods> items;
    private String name;
    private int id;

    private final String NAME_PATTERN = "^[A-Z].{0,100}$";

    public WareHouseDBListBuilder() {
        this.items = new ArrayList<Goods>();
        this.name = "Storage";
        this.id = 0;
    }

    public WareHouseDBListBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public WareHouseDBListBuilder setProducts(List<Goods> products) {
        this.items = products;
        return this;
    }

    public WareHouseDBListBuilder addProduct(Goods product) {
        items.add(product);
        return this;
    }

    public WareHouseDBListBuilder setName(String name) {
        if (!Pattern.matches(NAME_PATTERN, name)) {
            throw new IllegalArgumentException("Too long name");
        }
        this.name = name;
        return this;
    }

    public WareHouseDBListBuilder fromString(String input) {
        this.name = Regex.getRegexGroup(input, Regex.NAME_REGEX);
        this.items = new ArrayList<>();
        String[] productsArray = Regex.getRegexGroup(input, Regex.PRODUCTS_REGEX).split("/");
        for (String item : productsArray) {
            if (!item.isEmpty())
                items.add(new ItemBuilder().fromString(item).build());
        }
        return this;
    }

    public WarehouseDBList build() {
        return new WarehouseDBList(name, items, id);
    }
}
