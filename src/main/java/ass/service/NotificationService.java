
package ass.service;

import ass.notificationobject.Notification;
import java.util.List;


public interface NotificationService {
    List<Notification> getAllNotifications();
   
    void saveNotification(Notification notification);
    
    Notification getNotificationById(long id);
    
    void deleteNotificationById(long id);
}
