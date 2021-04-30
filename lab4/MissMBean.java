package web.lab4.mbeans;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ManagedResource(objectName = "weblab4:name=MissMBean")
@Component
public class MissMBean {

    private final Map<String, Integer> missData = new HashMap<>();
    private final Map<String, Integer> allData = new HashMap<>();
    private final Map<String, Double> missRate = new HashMap<>();

    @ManagedAttribute
    public Map<String, Double> getMissRate() {
        return missRate;
    }

    public void addResult(String name, boolean result) {
        allData.putIfAbsent(name, 0);
        missData.putIfAbsent(name, 0);
        missRate.putIfAbsent(name, 0.0);
        allData.put(name, allData.get(name) + 1);
        if (!result) {
            missData.put(name, missData.get(name) + 1);
        }
        missRate.put(name, (double) missData.get(name) / allData.get(name) * 100);
    }

    public void deleteUserPoints(String name) {
        missRate.put(name, 0.0);
        missData.put(name, 0);
        allData.put(name, 0);
    }

}
