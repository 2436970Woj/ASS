
package ass.service;

import ass.notificationobject.Notification;
import ass.repository.NotificationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationServiceImpl implements NotificationService{
    
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public void saveNotification(Notification notification) {
        this.notificationRepository.save(notification);
    }

    @Override
    public Notification getNotificationById(long id) {
        Optional<Notification> optional=notificationRepository.findById(id);
        Notification notification = null;
        if(optional.isPresent()){
            notification = optional.get();
        }else{
                throw new RuntimeException(" Notification not found for id:: " + id);
        }
        return notification;
    }

    @Override
    public void deleteNotificationById(long id) {
        this.notificationRepository.deleteById(id);
    }
    
}
