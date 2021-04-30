package web.lab4.mbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Component;
import web.lab4.model.Data;

import java.util.HashMap;
import java.util.Map;

@ManagedResource(objectName = "weblab4:name=AreaMBean")
@Component
public class AreaMBean {

    private final Map<String, Integer> checkData = new HashMap<>();
    private final Map<String, Integer> allData = new HashMap<>();
    private final AreaMBeanNotifier areaMBeanNotifier;

    @Autowired
    public AreaMBean(AreaMBeanNotifier areaMBeanNotifier) {
        this.areaMBeanNotifier = areaMBeanNotifier;
    }

    @ManagedAttribute
    public Map<String, Integer> getCheckData() {
        return checkData;
    }

    @ManagedAttribute
    public Map<String, Integer> getAllData() {
        return allData;
    }

    public void addPoint(String name, Data data) {
        allData.putIfAbsent(name, 0);
        checkData.putIfAbsent(name, 0);
        allData.put(name, allData.get(name) + 1);
        if (data.getResult()) {
            checkData.put(name, checkData.get(name) + 1);
        }
        if (isOutOfArea(data.getX(), data.getY(), data.getR())) {
            areaMBeanNotifier.sendNotification("Point out of area", allData.get(name), "User " + name);
        }

    }

    public void deleteUserPoints(String name) {
        checkData.put(name, 0);
        allData.put(name, 0);
    }

    private boolean isOutOfArea(Double x, Double y, Double r) {
        return x > r + r / 6 || y > r + r / 6;
    }

}
