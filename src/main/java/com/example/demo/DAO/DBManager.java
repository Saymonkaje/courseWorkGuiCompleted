package com.example.demo.DAO;

import com.example.demo.Model.Coffee;
import com.example.demo.Model.Pack;
import com.example.demo.Model.PhysicalCondition;
import com.example.demo.Model.Sort;
import com.example.demo.logger.MyLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.logging.Level;

public class DBManager {
    private static DBManager instance;
    private static final String URL ="jdbc:mysql://localhost:3306/course_work" ;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private final Connection connection;

    private DBManager ()
    {
        MyLogger.getLogger().log(Level.INFO,"getting connection with database");
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            MyLogger.getLogger().log(Level.SEVERE,"error connecting with database",e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static DBManager getInstance() {
        if(instance==null)
            instance = new DBManager();
        return instance;
    }


    public ObservableList<Coffee> selectCoffee(String query)
    {
        MyLogger.getLogger().log(Level.INFO,"selecting coffee");
        ObservableList<Coffee> coffeeList= FXCollections.observableArrayList();
        try( Statement  statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            int n = 0;
            while (resultSet.next())
            {
                coffeeList.add(new Coffee(
                        new Sort(resultSet.getString(ColumnConstant.sort.trim()),
                                resultSet.getInt(ColumnConstant.coffeeSortId.trim())),
                        resultSet.getDouble(ColumnConstant.pricePerKilo.trim()),
                        new Pack(resultSet.getString(ColumnConstant.packName.trim()),
                                resultSet.getDouble(ColumnConstant.packVolume.trim()),
                                resultSet.getInt(ColumnConstant.packId.trim())),
                        new PhysicalCondition(resultSet.getString(ColumnConstant.physCondition.trim()),
                                resultSet.getInt(ColumnConstant.packId.trim())),
                        resultSet.getInt(ColumnConstant.coffeeId.trim()),
                        resultSet.getDouble(ColumnConstant.weight.trim())));
                n++;
            }
            if(n==0)
                System.out.println("Даних немає");
        } catch (SQLException e) {
            MyLogger.getLogger().log(Level.SEVERE,"error selecting coffee",e);
            throw new RuntimeException(e);
        }
        return coffeeList;
    }

    public Integer selectMaxId(String query)
    {
        MyLogger.getLogger().log(Level.INFO,"selecting max id");
        Integer id = 0;
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
            if(resultSet==null)
                id = 0;
            else
                id = resultSet.getInt(1);
            //System.out.println(resultSet.getInt(1));
        } catch (SQLException e) {
            MyLogger.getLogger().log(Level.SEVERE,"error selecting max id",e);
            throw new RuntimeException(e);
        }
        MyLogger.getLogger().log(Level.INFO,"our max = " +id);

        return id;
    }
    public Map<Integer, Double> selectAverageConsumption()
    {
        MyLogger.getLogger().log(Level.INFO,"selecting average consumption");
        Map<Integer,Double> map = new HashMap<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QueryConstant.selectAverageConsumptionPerMonth)){
            int n = 0;
            while (resultSet.next()) {
                map.put(resultSet.getInt(1), resultSet.getDouble(2));
                n++;
            }
            if(n==0)
                System.out.println("Даних нема");
        } catch (SQLException e) {
            MyLogger.getLogger().log(Level.SEVERE,"error average consumption",e);
            throw new RuntimeException(e);
        }
        return map;
    }

    private void updateCoffeeNum(int id, double num, String query)
    {
        MyLogger.getLogger().log(Level.INFO,"updating some numeric property of coffee");
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1,num);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            MyLogger.getLogger().log(Level.SEVERE,"error updating some numeric property of coffee",e);
            throw new RuntimeException(e);
        }
    }
    public void updateCoffeeWeight(int id, double weight)
    {
        MyLogger.getLogger().log(Level.INFO,"updating coffee weight");
        updateCoffeeNum(id,weight,QueryConstant.updateCoffeeWeight);
    }
    public void updateCoffeePrice(int id, double newPrice)
    {
        MyLogger.getLogger().log(Level.INFO,"updating coffee price");
        updateCoffeeNum(id,newPrice,QueryConstant.updateCoffeePrice);
    }


    public List<Sort> selectSort() {
        return selectIdAndName(QueryConstant.selectSort).
                entrySet().stream().
                map(x->new Sort(x.getValue(),x.getKey())).toList();

    }

    public List<Pack> selectPack() {
        MyLogger.getLogger().log(Level.INFO,"selecting pack");
        List<Pack> packList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QueryConstant.selectPack)) {
            int n = 0;
            while (resultSet.next()) {
                packList.add(new Pack(resultSet.getString("pack_name"),
                        resultSet.getDouble("volume"),resultSet.getInt("id")));
                n++;
            }
            if(n==0)
                System.out.println("Даних нема");
        } catch (SQLException e) {
            MyLogger.getLogger().log(Level.SEVERE,"error selecting pack",e);
            throw new RuntimeException(e);
        }
        return packList;
    }
    public List<PhysicalCondition> selectCondition() {
        MyLogger.getLogger().log(Level.INFO,"selecting condition");
        return selectIdAndName(QueryConstant.selectCondition).
                entrySet().stream().
                map(x->new PhysicalCondition(x.getValue(),x.getKey())).toList();
    }

    private Map<Integer,String> selectIdAndName(String query)
    {
        MyLogger.getLogger().log(Level.INFO,"selecting id and name");
        Map<Integer,String> packList = new HashMap<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            int n =0;
            while (resultSet.next()) {
                packList.put(resultSet.getInt(1),resultSet.getString(2));
                n++;
            }
            if(n==0)
                System.out.println("Даних нема");
        } catch (SQLException e) {
            MyLogger.getLogger().log(Level.SEVERE,"error selecting id and name",e);
            throw new RuntimeException(e);
        }
        return packList;
    }

    public double selectTotalPrice()
    {
        return selectSum(QueryConstant.selectTotalPrice);
    }
    public double selectTotalWeight()
    {
        return selectSum(QueryConstant.selectTotalWeight);
    }

    private double selectSum(String query)
    {
        MyLogger.getLogger().log(Level.INFO,"selecting sum");
        double sum = 0;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            int n = 0;
            if(resultSet.next()) {
                sum = resultSet.getDouble(1);
                n++;
            }
            if(n==0)
                System.out.println("Даних нема");
        } catch (SQLException e) {
            MyLogger.getLogger().log(Level.SEVERE,"error selecting sum",e);
            throw new RuntimeException(e);
        }
        return sum;
    }
    public void insertCoffee (int pack_id,int type_id,
                              int condition_id, double weight,
                              double price_per_kilogram)
    {
        MyLogger.getLogger().log(Level.INFO,"inserting coffee");
        try(PreparedStatement preparedStatement= connection.prepareStatement(QueryConstant.insertCoffee)) {
            preparedStatement.setInt(1,pack_id);
            preparedStatement.setInt(2,type_id);
            preparedStatement.setInt(3,condition_id);
            preparedStatement.setDouble(4,weight);
            preparedStatement.setDouble(5,price_per_kilogram);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            MyLogger.getLogger().log(Level.INFO,"error inserting coffee");
            throw new RuntimeException(e);
        }
    }
    public int insertPack (double volume, String name)
    {
        MyLogger.getLogger().log(Level.INFO,"inserting pack");
        int id=0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(QueryConstant.insertPack);
            Statement statement = connection.createStatement()) {
            preparedStatement.setDouble(1,volume);
            preparedStatement.setString(2,name);
            preparedStatement.executeUpdate();
            ResultSet resultSet = statement.executeQuery(QueryConstant.getLastIdCreated);
            if(resultSet.next())
                id = resultSet.getInt(1);
            resultSet.close();
            if(id==0)
                throw new SQLException("ПАКЕТИК НЕ ВСТАВИВСЯ");
        } catch (SQLException e) {
            MyLogger.getLogger().log(Level.SEVERE,"error inserting pack", e);
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        return id;
    }


    public int insertCondition(String condition)
    {
        MyLogger.getLogger().log(Level.INFO,"inserting condition");
        return insertString(condition,QueryConstant.insertCondition);
    }

    public int insertSort(String sort)
    {
        MyLogger.getLogger().log(Level.INFO,"inserting sort");
        return insertString(sort,QueryConstant.insertCoffeeSort);
    }

    private int insertString(String stringToBeInserted, String query) {
        int id = 0;
        MyLogger.getLogger().log(Level.INFO,"inserting some string");
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);
            Statement statement = connection.createStatement()) {
            preparedStatement.setString(1, stringToBeInserted);
            preparedStatement.executeUpdate();
            ResultSet resultSet = statement.executeQuery(QueryConstant.getLastIdCreated);
            if(resultSet.next())
                id = resultSet.getInt(1);
            resultSet.close();
        } catch (SQLException e) {
            MyLogger.getLogger().log(Level.SEVERE,"error inserting some string",e);
            throw new RuntimeException(e);
        }
        return id;
    }

    public void deleteCoffee(int id)
    {
        MyLogger.getLogger().log(Level.INFO,"deleting coffee");
        try(PreparedStatement preparedStatement = connection.prepareStatement(QueryConstant.deleteCoffeeById)) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            MyLogger.getLogger().log(Level.SEVERE,"error deleting coffee",e);
            throw new RuntimeException(e);
        }
    }
    public void close()
    {
        MyLogger.getLogger().log(Level.INFO,"closing connection");
        try {
            if(!connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            MyLogger.getLogger().log(Level.SEVERE,"error closing connection",e);
            throw new RuntimeException(e);
        }
    }

}
