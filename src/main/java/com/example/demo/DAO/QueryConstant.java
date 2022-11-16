package com.example.demo.DAO;

import static com.example.demo.DAO.ColumnConstant.*;

public class QueryConstant {
    public static String selectAllCoffee = "select "+sort+", "+packName+
            "            ,"+ packVolume +","+physCondition +
            "            ,"+weight+","+pricePerKilo+","+coffeeId+","+packId+
            "            ,"+coffeeSortId+", "+ physConditionId+
            "            from coffee\n" +
            "            join coffee_type on coffee.type_id = coffee_type.id\n" +
            "            join pack on pack_id = pack.id\n" +
            "            join physical_condition on condition_id = physical_condition.id";
    public static String selectTotalPrice = "select sum(price_per_kilogram*weight/1000) from coffee";
    public static String selectTotalWeight = "select sum(coffee.weight) " +
            "from coffee join pack on pack_id = pack.id";
    public static String insertPack = "insert into pack ("+ packVolume +", pack_name)" +
            " values (?, ?)";
    public static String insertCondition =  "insert into physical_condition (phys_condition) values (?)";

    public static String insertCoffeeSort = "insert into coffee_type (type_name) values (?)";

    public static String insertCoffee = "insert into coffee" +
            "(pack_id,type_id,condition_id,weight,price_per_kilogram) " +
            "values(?,?,?,?,?)";
    public static String updateCoffeeWeight = "update coffee set weight = ?" +
            " where id = ?";
    public static String updateCoffeePrice = "update coffee set "+pricePerKilo+ " = ? where id = ?";

    public static String getLastIdCreated = "select @@identity";
    public static String selectPack = "select * from pack";
    public static String selectSort = "select * from coffee_type";
    public static String selectCondition = "select * from physical_condition";
    public static final String selectAverageConsumptionPerMonth = """
            SELECT coffee_id, avg(consumedWeight)
            FROM course_work.coffee_consumption_history
            where datediff(now(), consumptionDate)<30
            group by coffee_id""";

    public static final String deleteCoffeeById = "delete from coffee where id = ?";
    public static final String deletePackById = "delete from pack where id = ?";
    public static final String deleteSortById = "delete from coffee_type where id = ?";
    public static final String deleteConditionById = "delete from physical_condition where id = ?";
}