package web.lab4.mbeans;

import web.lab4.model.Data;

public interface IUserDataChecker {
    void checkCustomObject(CustomObject customObject, Data data);

    void sendNotification(String name, Data data);
}
