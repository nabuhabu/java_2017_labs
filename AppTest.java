import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.assertEquals;
public class AppTest {
 /*   @Test
    public void someTest(){
        Assert.assertEquals(1,1);
    }
    @Test
    public void serikalizationTest(){
        Goods goods = new Goods(1,"Skiper","Copybook",5.67, LocalDate.of(2006, Month.AUGUST,7), LocalDate.of(2010,Month.APRIL,6));
        JsonIO<Goods> jsonIO = new JsonIO<>();
        String filePath = "serialization";
        jsonIO.toFile(goods, filePath);
        Goods from = jsonIO.fromFile(Goods.class, filePath);
        Assert.assertEquals(goods, from);
    }
    @Test
    public void serikalizationXmlTest(){
        Goods goods = new Goods(2,"Skiper","Copybook",5.67, LocalDate.of(2006, Month.AUGUST,7), LocalDate.of(2010,Month.APRIL,6));
        XmlIO<Goods> xmlIO = new XmlIO<>();
        String filePath = "serialization";
        xmlIO.toFile(goods, filePath);
        Goods from = xmlIO.fromFile(Goods.class, filePath);
        Assert.assertEquals(goods, from);
    }
    */



 //   public class DBServiceTest {
        WarehouseDBList warehouse;
        Goods good1;

        @BeforeClass
        void setup() throws SQLException, ClassNotFoundException {
            good1 = new ItemBuilder().setName("Good1").setProductionDate(2017, 03, 07).build();
            Goods good2 = new ItemBuilder().setName("Good2").setProductionDate(2017, 07, 03).build();
            Goods good3 = new ItemBuilder().setName("Good3").setProductionDate(2017, 01, 01).build();
            warehouse = new WareHouseDBListBuilder().setName("BestWarehouse").addProduct(good2).addProduct(good3).setId(2310).build();
        }

        @Test
        public void testGetNewConnection() throws Exception {
            DBService.getConnection();
        }

        @Test
        public void testAddItem() throws Exception {
            DBService.addItem(good1, 1);
        }

        @Test
        public void testAddWarehouse() throws Exception {
            DBService.addWarehouse(warehouse);
        }

        @Test
        public void testGetItem() throws Exception {
            good1 = DBService.getItem("Product11");
        }

        @Test
        public void testGetWarehouse() throws Exception {
            warehouse = DBService.getWarehouse("Storage11");
        }

        @Test
        public void testGetItems() throws Exception {
            DBService.getGoods();
        }

        @Test
        public void testGetWarehouses() throws Exception {
            DBService.getWarehouses();
        }



        @Test
        public void testUpdateWarehouse() throws Exception {

            WarehouseDBList warehouseDBList = DBService.getWarehouses().get(0);
            warehouseDBList.setName(warehouseDBList.getName() + "Changed");
            DBService.updateWarehouse(warehouseDBList);
            assertEquals(DBService.getWarehouseDBList(warehouseDBList.getId()).getName(), warehouseDBList.getName());
        }

        @Test
        public void testDeleteWarehouse() throws Exception {
            DBService.addWarehouse(warehouse);
            DBService.deleteWarehouse(DBService.getWarehouse(warehouse.getName()).getId());
            assertEquals(DBService.getWarehouseDBList(warehouse.getId()), null);
        }

        @Test
        public void testDeleteItem() throws Exception {
            DBService.addItem(good1, 1);
            DBService.deleteGoods(DBService.getItem(good1.getName()).getItem_ID());
            assertEquals(DBService.getItem(good1.getItem_ID()), null);
        }

        @Test
        public void negativeTestGetItem() throws Exception {
            assertEquals(DBService.getItem(222), null);
        }

        @Test
        public void negativeTestGetWarehouse() throws Exception {
            assertEquals(DBService.getWarehouseDBList(111), null);
        }

        @Test( expectedExceptions = Exception.class)
        public void negativeTestUpdateItem() throws Exception {
            good1.setItem_ID(1321);
            DBService.updateGoods(good1);
        }

        @Test( expectedExceptions = Exception.class)
        public void negativeTestUpdateWarehouse() throws Exception {
            Goods goods = DBService.getGoods().get(0);
            goods.setItem_ID(-1);
            DBService.updateGoods(goods);
        }

        @Test( expectedExceptions = Exception.class)
        public void negativeTestDeleteWarehouse() throws Exception {
            DBService.deleteWarehouse(555);
        }

        @Test( expectedExceptions = Exception.class)
        public void negativeTestDeleteItem() throws Exception {
            DBService.deleteGoods(555);
        }

//        @Test
//        public void expiredsItemsTest() throws SQLException, ClassNotFoundException {
//            DBService.expiredProducts();
//        }

    }

