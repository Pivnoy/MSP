package web.lab4.mbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.Map;

@ManagedResource(objectName = "weblab4:name=MissMBean")
@Component
public class MissMBean {

    private final IUserStatistic userStatistic;

    @Autowired
    public MissMBean(IUserStatistic userStatistic) {
        this.userStatistic = userStatistic;
    }

    @ManagedAttribute
    public Map<String, Double> getMissRate() {
        return userStatistic.getAllMissUserRate();
    }


}
