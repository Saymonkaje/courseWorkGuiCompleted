package com.example.demo.Model;


import com.example.demo.DAO.ColumnConstant;
import com.example.demo.DAO.DBManager;
import com.example.demo.DAO.QueryConstant;
import com.example.demo.logger.MyLogger;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class Model {

    DBManager database;
    private static Model instance;

    public static Model getInstance() {
        if(instance == null)
            instance = new Model(DBManager.getInstance());
        return instance;
    }


    private Model(DBManager database)
    {
        this.database = database;
    }
    public List<Sort> getSortList()
    {
        MyLogger.getLogger().log(Level.INFO,"getting sort map");
        return database.selectSort();
    }

    public List<PhysicalCondition> getConditionList()
    {
        MyLogger.getLogger().log(Level.INFO,"getting condition map");
        return database.selectCondition();
    }
    public void loadNewCoffee(int pack_id,int sort_id,int condition_id,double weight,double price)
    {
        MyLogger.getLogger().log(Level.INFO,"loading new coffee");
        database.insertCoffee(pack_id,sort_id,condition_id,weight,price);
    }

    public List<Pack> showPacks()
    {
        return database.selectPack();
    }

    public int insertPack(String pack_name, double volume)
    {
        MyLogger.getLogger().log(Level.INFO,"inserting pack");
        return database.insertPack(volume,pack_name);
    }

    public int insertSort(String sort)
    {
        return database.insertSort(sort);
    }
    public int insertCondition(String condition)
    {
        MyLogger.getLogger().log(Level.INFO,"inserting condition");
        return database.insertCondition(condition);
    }

    public void consumptionCoffee(double newWeight, Coffee coffee)
    {
        MyLogger.getLogger().log(Level.INFO,"adding info about coffee consumption");
        database.updateCoffeeWeight(coffee.getId(),newWeight);
    }
    public void deletePack(int id)
    {
        MyLogger.getLogger().log(Level.INFO,"deleting pack");
        database.deletePack(id);
    }
    public void deleteSort(int id)
    {
        MyLogger.getLogger().log(Level.INFO,"deleting sort");
        database.deleteSort(id);
    }
    public void deleteCondition(int id)
    {
        MyLogger.getLogger().log(Level.INFO,"deleting condition");
        database.deleteCondition(id);
    }

    public void deleteCoffee(int id)
    {
        MyLogger.getLogger().log(Level.INFO,"deleting coffee");
        database.deleteCoffee(id);
    }

    public void setNewPrice(double newPrice, Coffee coffee)
    {
        MyLogger.getLogger().log(Level.INFO,"setting new price");
        database.updateCoffeePrice(coffee.getId(),newPrice);
    }
    public ObservableList<Coffee> getCoffee(String select)
    {
        return database.selectCoffee(select);
    }

    public List<Coffee> showCoffeeThatWillSoonRunOut()
    {
        return getCoffee(QueryConstant.selectAllCoffee+" where "+ColumnConstant.weight+"<50");

    }


    public String searchCoffee(Sort sort, Pack pack, PhysicalCondition physCond, String weight, String price)
    {
        MyLogger.getLogger().log(Level.INFO,"searching coffee");
        StringBuilder query = new StringBuilder(" where ");
        if(sort != null) {
            query.append(ColumnConstant.coffeeSortId + "=").append(sort.getId());
            query.append(" and ");
        }
        if(pack != null) {
            query.append(ColumnConstant.packId + "=").append(pack.getId());
            query.append(" and ");
        }
        if(physCond != null) {
            query.append(ColumnConstant.physConditionId + "=").append(physCond.getId());
            query.append(" and ");
        }
        if(weight != null) {
            query.append(ColumnConstant.weight).append(weight);
            query.append(" and ");
        }
        if(price != null) {
            query.append(ColumnConstant.pricePerKilo).append(price);
            query.append(" and ");
        }
        int index = query.lastIndexOf("and");
        if(index>=query.length()-4)
            query.delete(index,index+3);
        return QueryConstant.selectAllCoffee+query;
    }


    public double getTotalPrice()
    {
        MyLogger.getLogger().log(Level.INFO,"showing total price");
        return database.selectTotalPrice();
    }
    public double getTotalWeight()
    {
        MyLogger.getLogger().log(Level.INFO,"showing total weight");
        return database.selectTotalWeight();
    }


    public Map<Integer,Integer> showTimeToRunOutOfCoffee()
    {
        Map<Integer,Double> map = database.selectAverageConsumption();
        List<Coffee> coffeeList = database.selectCoffee(QueryConstant.selectAllCoffee);
        Map<Integer,Integer> coffeeIntegerMap = new HashMap<>();
        for(Coffee coffee: coffeeList)
        {
            coffeeIntegerMap.put(coffee.getId(),
                    map.get (coffee.getId())==null?null:(int) (coffee.getWeight() / map.get(coffee.getId())));
        }
        return coffeeIntegerMap;
    }
}
