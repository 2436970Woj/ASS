
package ass.assignment;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assignment")
public class AssignmentObject implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "course_id")
    private String courseId;
    
    @Column(name = "student_id")
    private String studentId;
    
    @Column(name = "session")
    private String session;
    
    @Column(name = "term")
    private String term;
    
    @Column(name = "credit_units")
    private String creditUnits;
    
    
    @Column(name = "reference")
    private String reference;
    
    @Column(name = "text")
    private String text;
    
    @Column(name = "submitted")
    private String submitted;
    
    public AssignmentObject (){};

    public AssignmentObject(long id, String courseId, String studentId, String session, String term, String creditUnits, String reference, String text, String submitted) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.session = session;
        this.term = term;
        this.creditUnits = creditUnits;
        this.reference = reference;
        this.text = text;
        this.submitted = submitted;
    }

    public long getId() {
        return id;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSession() {
        return session;
    }

    public String getTerm() {
        return term;
    }

    public String getCreditUnits() {
        return creditUnits;
    }

    public String getReference() {
        return reference;
    }

    public String getText() {
        return text;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setCreditUnits(String creditUnits) {
        this.creditUnits = creditUnits;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    
}
