
package ass.service;

import java.util.List;
import ass.studentobject.Student;

public interface StudentService {
    List<Student> getAllStudents();
    
    void saveStudent(Student student);
    
    Student getStudentById(long id);
    
    void deleteStudentById(long id);
}
