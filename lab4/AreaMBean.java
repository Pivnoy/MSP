package web.lab4.mbeans;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Component;
import web.lab4.model.Data;

import javax.management.Notification;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ManagedResource(objectName = "weblab4:name=AreaMBean")
@Component
public class AreaMBean implements IAreaMBean, NotificationPublisherAware {

    private final Map<String, Integer> checkData = new HashMap<>();
    private final Map<String, Integer> allData = new HashMap<>();
    private NotificationPublisher notificationPublisher;

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }

    @Override
    public void addPoint(String name, Data data) {
        allData.putIfAbsent(name, 0);
        checkData.putIfAbsent(name, 0);
        allData.put(name, allData.get(name) + 1);
        if (data.getResult()) {
            checkData.put(name, checkData.get(name) + 1);
        }
        Double x = data.getX();
        Double y = data.getY();
        Double r = data.getR();
        if (x>r+r/6 || y>r+r/6) {
            notificationPublisher.sendNotification(new Notification("Point out of area", this, allData.get(name),
                    "User "+ name));
        }

    }

    @Override
    @ManagedAttribute
    public Map<String, Integer> getCheckData() {
        return checkData;
    }

    @Override
    @ManagedAttribute
    public Map<String, Integer> getAllData() {
        return allData;
    }

    @Override
    public void deleteUserPoints(String name) {
        checkData.put(name,0);
        allData.put(name,0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaMBean areaMBean = (AreaMBean) o;
        return Objects.equals(checkData, areaMBean.checkData) && Objects.equals(allData, areaMBean.allData) && Objects.equals(notificationPublisher, areaMBean.notificationPublisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkData, allData, notificationPublisher);
    }
}
