import java.time.LocalDate;
import java.util.Objects;

public class Goods
{
    private int item_ID;
    private String name;
    private String type;
    private double price;
    private LocalDate productionDate;
    private LocalDate expiryDate;

    @Override
    public String toString() {
        return "Goods{" +
                "item_id='" + item_ID + '\'' +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", productionDate=" + productionDate +
                ", expiryDate=" + expiryDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Double.compare(goods.price, price) == 0 &&
                Objects.equals(name, goods.name) &&
                Objects.equals(item_ID, goods.item_ID) &&
                Objects.equals(type, goods.type) &&
                Objects.equals(productionDate, goods.productionDate) &&
                Objects.equals(expiryDate, goods.expiryDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(item_ID, name, type, price, productionDate, expiryDate);
    }

    public Goods(int item_ID, String name, String type, double price, LocalDate productionDate, LocalDate expiryDate) {

        this.item_ID = item_ID;
        this.name = name;
        this.type = type;
        this.price = price;
        this.productionDate = productionDate;
        this.expiryDate = expiryDate;
    }

    public int getItem_ID() {

        return item_ID;
    }

    public void setItem_ID(int item_ID) {
        this.item_ID = item_ID;
    }
    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public static int ifExpired(Goods good) {

        return LocalDate.now().compareTo(good.getExpiryDate());

    }



}
