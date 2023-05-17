
package ass.service;

import ass.assignment.AssignmentObject;
import java.util.List;


public interface AssignmentService {
    List<AssignmentObject> getAllAssignments();
   
    void saveAssignment(AssignmentObject assignment);
    
    AssignmentObject getAssignmentById(long id);
    
    void deleteAssignmentById(long id);
}
