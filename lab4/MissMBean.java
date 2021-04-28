package web.lab4.mbeans;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ManagedResource(objectName = "weblab4:name=MissMBean")
@Component
public class MissMBean implements IMissMBean {

    private final Map<String, Integer> missData = new HashMap<>();
    private final Map<String, Integer> allData = new HashMap<>();
    private final Map<String, Double> missRate = new HashMap<>();

    @Override
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

    @Override
    @ManagedAttribute
    public Map<String, Double> getMissRate() {
        return missRate;
    }

    @Override
    public void deleteUserPoints(String name) {
        missRate.put(name,0.0);
        missData.put(name,0);
        allData.put(name,0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissMBean missMBean = (MissMBean) o;
        return Objects.equals(missData, missMBean.missData) && Objects.equals(allData, missMBean.allData) && Objects.equals(missRate, missMBean.missRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missData, allData, missRate);
    }
}
