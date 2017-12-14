import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*Goods item = new Goods(1, "Skiper","Copybook",5.57, LocalDate.of(2006, Month.AUGUST,7), LocalDate.of(2010,Month.APRIL,6));
        HashMap<Goods,Integer> base = new HashMap<>();
        base.put(item, 3);


       // Warehouse warehouse = new Warehouse("Ivan","Chernivtsi" , base);

        //Warehouse warehouse = new Warehouse("Ivan","Chernivtsi",base,1);
        Warehouse warehouse = new Warehouse( "The Best", "Ivan","Kyiv", base, 1);
        JsonIO<Warehouse> jsonIO = new JsonIO<>();
        jsonIO.toFile(warehouse, "wh");
        System.err.println("json result: " + jsonIO.fromFile(Warehouse.class, "wh"));

        XmlIO<Warehouse> xmlIO = new XmlIO<>();
        xmlIO.toFile(warehouse, "wh_xml");
        System.err.println("xml result: " + xmlIO.fromFile(Warehouse.class, "wh_xml"));*/
//        DBService.setDatabase();
        /*System.err.println("Hola");
        Warehouse warehouse = new Warehouse("Avesome name", null, null, null, 1);
        DBService.saveWarehouse(warehouse);*/
        //Goods goods = new Goods(1, "Some name", "Some type", 3.22, LocalDate.now(), LocalDate.now());
       // DBService.saveGoods(goods);
        Goods goods = DBService.getItem(1);
        System.err.println(goods);
    }
}
