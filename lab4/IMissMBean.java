package web.lab4.mbeans;

import java.util.Map;

public interface IMissMBean {
    void addResult(String name, boolean result);
    Map<String, Double> getMissRate();
    void deleteUserPoints(String name);
}
