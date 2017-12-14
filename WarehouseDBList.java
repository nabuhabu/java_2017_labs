

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WarehouseDBList {

    private int id;
    private String name;
    private List<Goods> items;



    private String responsible;
    private String address;
    //private HashMap<Goods, Integer> items;


    WarehouseDBList(int id, String name, List<Goods> items,String responsible, String address ) {
        setId(id);
        setName(name);
        setItems(items);
        setResponsible(responsible);
        setAddress(address);

    }

    WarehouseDBList(String name, List<Goods> items, int id) {

        this.id = id;
        this.name = name;
        this.items = new ArrayList<>();
        responsible = " Responsible ";
        address = "Address";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Goods> getItems() {
        return items;
    }

    public void setItems(List<Goods> items) {
        this.items = items;
    }


    public Goods getProduct(int index) {
        return items.get(index);
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean addItem(Goods item) {
        return items.add(item);
    }

    public boolean removeProduct(Goods item) {
        return items.remove(item);
    }

    private List<Goods> filter(Predicate<Goods> type) {
        return items.stream().filter(type).collect(Collectors.toList());
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarehouseDBList)) return false;

        WarehouseDBList warehouseDBList = (WarehouseDBList) o;

        return items != null ? items.equals(warehouseDBList.items) : warehouseDBList.items == null;
    }

    @Override
    public int hashCode() {
        return items != null ? items.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "WarehouseDBList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", items=" + items +
                ", responsible='" + responsible + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
