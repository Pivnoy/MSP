package web.lab4.mbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.Map;

@ManagedResource(objectName = "weblab4:name=AreaMBean")
@Component
public class AreaMBean {

    private final IUserStatistic userStatistic;

    @Autowired
    public AreaMBean(IUserStatistic userStatistic) {
        this.userStatistic = userStatistic;
    }

    @ManagedAttribute
    public Map<String, Integer> getCheckData() {
        return userStatistic.getAllCheckUserPoints();
    }

    @ManagedAttribute
    public Map<String, Integer> getAllData() {
        return userStatistic.getAllUserPoints();
    }

}
