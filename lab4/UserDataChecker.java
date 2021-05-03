package web.lab4.mbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.lab4.model.Data;

@Component
public class UserDataChecker implements IUserDataChecker {

    private final NotifierMBean notifierMBean;

    @Autowired
    public UserDataChecker(NotifierMBean notifierMBean) {
        this.notifierMBean = notifierMBean;
    }

    @Override
    public void checkCustomObject(CustomObject customObject, Data data) {
        if (data.check()) {
            customObject.addCheckPoint();
        } else {
            customObject.addMissPoint();
        }
    }

    @Override
    public void sendNotification(String name, Data data) {
        if (isOutOfArea(data.getX(), data.getY(), data.getR())) {
            notifierMBean.sendNotification("Point out of area", "User " + name);
        }
    }

    private boolean isOutOfArea(Double x, Double y, Double r) {
        return x > r + r / 6 || y > r + r / 6;
    }

}
