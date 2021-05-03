package web.lab4.mbeans;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Component;

import javax.management.Notification;

@ManagedResource(objectName = "weblab4:name=NotifierMBean")
@Component
public class NotifierMBean implements NotificationPublisherAware {
    private NotificationPublisher notificationPublisher;
    private long seqNumber = 0;

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }

    public void sendNotification(String type, String message) {
        notificationPublisher.sendNotification(new Notification(type, this, seqNumber++, message));
    }

}
