
package ass.notificationobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    
    @Column(name = "user_id")
    private long userId;
    
    @Column(name = "added_by")
    private String addedBy;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "timestamp")
    private String timestamp;
    
    @Column(name = "read_status")
    private String readStatus;
    
    @Column(name = "assignment_id")
    private long assignmentId;

    public Notification(long id, long userId, String addedBy, String description, String timestamp, String readStatus, long assignmentId) {
        this.id = id;
        this.userId = userId;
        this.addedBy = addedBy;
        this.description = description;
        this.timestamp = timestamp;
        this.readStatus = readStatus;
        this.assignmentId = assignmentId;
    }
    public Notification(){};

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public String getDescription() {
        return description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public long getAssignmentId() {
        return assignmentId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public void setAssignmentId(long assignmentId) {
        this.assignmentId = assignmentId;
    }
}
