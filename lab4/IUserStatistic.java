package web.lab4.mbeans;

import web.lab4.model.Data;

import java.util.Map;

public interface IUserStatistic {
    void addUserData(String name, Data data);

    void clearUserData(String name);

    void addUser(String name);

    Map<String, Integer> getAllUserPoints();

    Map<String, Integer> getAllCheckUserPoints();

    Map<String, Double> getAllMissUserRate();
}
