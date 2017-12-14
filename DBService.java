


import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBService {

    private final static String USERNAME = "me";
    private final static String PASSWORD = "1234";
    private final static String URL = "jdbc:mysql://localhost:3306/java";


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Properties properties = new Properties();
        properties.setProperty("user", USERNAME);
        properties.setProperty("password", PASSWORD);
        return DriverManager.getConnection(URL, properties);
    }

    private static void createGoodsTable(Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement("CREATE TABLE goods\n" +
                "(\n" +
                "    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                "    item_name VARCHAR(100) NOT NULL,\n" +
                "    type_name VARCHAR(10) NOT NULL, \n" +
                "    price REAL,\n" +
                "    production_date DATE,\n" +
                "    expiration_date DATE,\n" +
                "    warehouse_ID INTEGER\n" +
                ");");
        st.executeUpdate();
    }

    private static void createWarehouseTable(Connection conn) throws SQLException {
        PreparedStatement st = conn.prepareStatement("CREATE TABLE warehouse\n" +
                "(\n" +
                "    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
                "    name CHAR(100) NOT NULL UNIQUE\n" +
                ");");
        st.executeUpdate();
    }


    public static void setDatabase() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        createGoodsTable(conn);
        createWarehouseTable(conn);
        conn.close();
    }

    private static void addGoodsBatch(Goods good, int warehouseDBListId, Statement st) throws SQLException, ClassNotFoundException {
        String sql =
                "INSERT INTO items (itemID ,name, type, productionDate, expiryDate, price, storage) "+
                        "VALUES ("+
                        "'" + good.getItem_ID() + "', " +
                        "'" + good.getName() + "', " +
                        "'" + good.getType() + "', " +
                        "'" + Date.valueOf(good.getProductionDate()) + "', " +
                        "'" + Date.valueOf(good.getExpiryDate())+ "', " +
                        good.getPrice() + ", " +
                        warehouseDBListId +
                        ");";
        st.addBatch(sql);
    }


    public static void addItem(Goods good, int warehouseDBList) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO items (item_name, type_name, price, production_date, expiration_date, warehouse_ID) VALUES (?,?,?,?,?,?)");
        statement.setString(1, good.getName());
        statement.setString(2, good.getType());
        statement.setDouble(3, good.getPrice());
        statement.setDate(4, Date.valueOf(good.getProductionDate()));
        statement.setDate(5, Date.valueOf(good.getExpiryDate()));
        statement.setInt(6, warehouseDBList);
        statement.executeUpdate();
        conn.close();
    }


    public static void addWarehouse(WarehouseDBList warehouseDBList) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement("INSERT  INTO warehouse (name) VALUES (?)");
        statement.setString(1, warehouseDBList.getName());
        statement.executeUpdate();
        conn.close();

    }

    public static void saveWarehouse(Warehouse warehouse) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO warehouse VALUES (?, ?)");
        String name = warehouse.getName();
        statement.setInt(1, warehouse.getId());
        statement.setString(2, name);
        statement.execute();
    }

    public static void saveGoods(Goods goods) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO items VALUES (?, ?, ?, ?, ?, ?, ?)");
        statement.setInt(1, goods.getItem_ID());
        statement.setString(2, goods.getName());
        statement.setString(3, goods.getType());
        statement.setDouble(4, goods.getPrice());
        statement.setDate(5, Date.valueOf(goods.getProductionDate()));
        statement.setDate(6, Date.valueOf(goods.getExpiryDate()));
        statement.setInt(7,1);
        statement.execute();
    }
    //===============

    public static Goods getItem(int id) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT  * FROM  items WHERE id=(?)");
        statement.setInt(1,id);
       ResultSet rs = statement.executeQuery();
        Goods item = null;
        if (rs.next()) {
            item = new ItemBuilder().setId(rs.getInt("id"))
                    .setName(rs.getString("item_name"))
                    .setType(rs.getString("type_name"))
                    .setProductionDate(LocalDate.parse(rs.getString("production_date")))
                    .setExpiryDate(LocalDate.parse(rs.getString("expiration_date")))
                    .setPrice(rs.getDouble("price"))
                    .build();
        }
        conn.close();
        return item;
    }


    public static Goods getItem(String name) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT  * FROM  items WHERE item_name=?");
        statement.setString(1,name);
        ResultSet rs = statement.executeQuery();
        Goods item = null;
        if(rs.next()) {
            item = getItem(rs.getInt("id"));
        }
        conn.close();
        return item;
    }


    public static ArrayList<Goods> getGoods() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT  * FROM items");
        ResultSet rs = statement.executeQuery();
        ArrayList<Goods> items = new ArrayList<>();
        while(rs.next()) {
            items.add(new ItemBuilder().setId(rs.getInt("id"))
                    .setName(rs.getString("item_name"))
                    .setType(rs.getString("type_name"))
                    .setProductionDate(LocalDate.parse(rs.getString("production_date")))
                    .setExpiryDate(LocalDate.parse(rs.getString("expiration_date")))
                    .setPrice(rs.getDouble("price"))
                    .build());
        }
        conn.close();
        return items;
    }


    public static WarehouseDBList getWarehouseDBList(int id) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT w.id, w.name, i.id, i.item_name, i.type_name, i.production_date, i.expiration_date, i.price  FROM items AS i, warehouse AS w WHERE i.warehouse_ID=w.id AND w.id=(?)");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        WarehouseDBList warehouseDBList = null;
        if (rs.next()) {
            warehouseDBList = new WareHouseDBListBuilder().setId(rs.getInt("id")).setName(rs.getString("name")).build();
        }
        conn.close();
        return warehouseDBList;
    }


    public static WarehouseDBList getWarehouse(String name) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT id, name FROM warehouse WHERE name=?");
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        WarehouseDBList warehouseDBList = null;
        if(rs.next()) {
            warehouseDBList = new WareHouseDBListBuilder().setId(rs.getInt("id")).setName(rs.getString("name")).build();
        }
        conn.close();
        return warehouseDBList;
    }


    public static ArrayList<WarehouseDBList> getWarehouses() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement( "SELECT * FROM warehouse");
        ResultSet rs = statement.executeQuery();
        ArrayList<WarehouseDBList> warehousesDBLists = new ArrayList<>();
        while(rs.next()) {

            WareHouseDBListBuilder builder = new WareHouseDBListBuilder();
            builder.setId(rs.getInt("id"));
            builder.setName(rs.getString("name"));
            WarehouseDBList result = builder.build();

            warehousesDBLists.add(result);
        }
        return warehousesDBLists;
    }


    public static void updateGoods(Goods item) throws SQLException, ClassNotFoundException, IllegalArgumentException {
        Connection conn = getConnection();
        Statement st = conn.createStatement();
        int rs = st.executeUpdate("UPDATE items SET " +
                "name='" + item.getName() + "', " +
                    "type='" + item.getType().toString() + "', " +
                "production_date='" + Date.valueOf(item.getProductionDate()) + "', " +
                "expiration_date='" + Date.valueOf(item.getExpiryDate())+ "', " +
                "price=" + item.getPrice()  + " " +
                "WHERE id=" + item.getItem_ID() + ";");
        conn.close();
        if(rs == 0) throw new IllegalArgumentException("Product not found");
    }

    public static void updateWarehouse(WarehouseDBList warehouseDBList) throws SQLException, ClassNotFoundException, IllegalArgumentException {
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement("UPDATE warehouse SET name = ? WHERE id = ?");
        statement.setString(1, warehouseDBList.getName());
        statement.setInt(2, warehouseDBList.getId());
        statement.executeUpdate();
        conn.close();
    }

    public static void deleteGoods(int item_ID) throws SQLException, ClassNotFoundException, IllegalArgumentException {
        Connection conn = getConnection();

        PreparedStatement statement = conn.prepareStatement("DELETE FROM items WHERE id=(?)");
        statement.setInt(1, item_ID);
        int rs = statement.executeUpdate();
        conn.close();
        if(rs == 0) throw new IllegalArgumentException("Product not found");
    }


    public static void deleteWarehouse(int id) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement("DELETE FROM warehouse WHERE id=(?)");
        statement.setInt(1, id);
        int rs = statement.executeUpdate();
        int result = statement.executeUpdate();
        conn.close();
        if (rs == 0){
            throw new SQLException();
        }
    }

    private static void drop() throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        Statement st = conn.createStatement();
        st.executeUpdate("DROP TABLE IF EXISTS 'goods';");
        st.executeUpdate("DROP TABLE IF EXISTS 'warehouse';");
        conn.close();
    }

//    public static List<Goods> expiredProducts() throws SQLException, ClassNotFoundException {
//        Connection conn = getConnection();
//        PreparedStatement statement = conn.prepareStatement( "SELECT * FROM items WHERE expiration_date > DATE('now') ORDER BY expiration_date");
//        ResultSet rs = statement.executeQuery();
//        ArrayList<Goods> items = new ArrayList<>();
//        while(rs.next()) {
//            items.add(new ItemBuilder().setId(rs.getInt("id"))
//                    .setName(rs.getString("name"))
//                    .setType(rs.getString("type"))
//                    .setProductionDate(LocalDate.parse(rs.getString("production_date")))
//                    .setExpiryDate(LocalDate.parse(rs.getString("expiration_date")))
//                    .setPrice(rs.getDouble("price"))
//                    .build());
//        }
//        conn.close();
//        return items;
//    }

}