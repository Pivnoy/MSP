package web.lab4.mbeans;

import web.lab4.model.Data;

import java.util.Map;

public interface IAreaMBean {
    void addPoint(String name, Data data);
    Map<String, Integer> getCheckData();
    Map<String, Integer> getAllData();
    void deleteUserPoints(String name);
}
