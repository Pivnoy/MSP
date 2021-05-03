package web.lab4.mbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.lab4.model.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserStatistic implements IUserStatistic {

    private final Map<String, CustomObject> userStatistic = new HashMap<>();
    private final IUserDataChecker userDataChecker;

    @Autowired
    public UserStatistic(IUserDataChecker userDataChecker) {
        this.userDataChecker = userDataChecker;
    }

    @Override
    public void addUserData(String name, Data data) {
        if (!userStatistic.containsKey(name)) addUser(name);
        userDataChecker.checkCustomObject(userStatistic.get(name), data);
        userDataChecker.sendNotification(name, data);
    }

    @Override
    public void addUser(String name) {
        userStatistic.put(name, new CustomObject());
    }

    @Override
    public void clearUserData(String name) {
        userStatistic.get(name).clear();
    }

    @Override
    public Map<String, Integer> getAllUserPoints() {
        return userStatistic.entrySet().stream().collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().getAllPoints()
                )
        );
    }

    @Override
    public Map<String, Integer> getAllCheckUserPoints() {
        return userStatistic.entrySet().stream().collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().getCheckPoints()
                )
        );
    }

    @Override
    public Map<String, Double> getAllMissUserRate() {
        return userStatistic.entrySet().stream().collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().getAllPoints() == 0 ? 0.0 : (double) e.getValue().getMissPoints() / e.getValue().getAllPoints() * 100
                )
        );
    }

}
