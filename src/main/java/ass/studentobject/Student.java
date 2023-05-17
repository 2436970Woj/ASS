
package ass.studentobject;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "students")
public class Student implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "course_id")
    private String courseId;
    
    @Column(name = "marticulation")
    private String marticulation;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "department")
    private String department;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "locked")
    private String locked;
    
    @Column(name = "type")
    private String type;
    
    public Student(){};

    public Student(long id, String courseId, String marticulation, String password, String firstName, String lastName, String department, String address, String email, String locked, String type) {
        this.id = id;
        this.courseId = courseId;
        this.marticulation = marticulation;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.address = address;
        this.email = email;
        this.locked = locked;
        this.type = type;
    }

    public String getLocked() {
        return locked;
    }

    public String getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getMarticulation() {
        return marticulation;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getFirstLastName() {
        return firstName + " " + lastName;
    }

    public String getDepartment() {
        return department;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setMarticulation(String marticulation) {
        this.marticulation = marticulation;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public void setType(String type) {
        this.type = type;
    }
    
   
}