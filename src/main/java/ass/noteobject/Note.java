
package ass.noteobject;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "note")
public class Note implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "assignment_id")
    private long assignmentId;
    
    @Column(name = "added_by")
    private String addedBy;
    
    @Column(name = "text")
    private String text;
    
    @Column(name = "timestamp")
    private String timestamp;

    public Note(long id, long assignmentId, String addedBy, String text, String timestamp) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.addedBy = addedBy;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Note() {
    }

    public long getAssignmentId() {
        return assignmentId;
    }

    public long getId() {
        return id;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public String getText() {
        return text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setAssignmentId(long assignmentId) {
        this.assignmentId = assignmentId;
    }
    
}
