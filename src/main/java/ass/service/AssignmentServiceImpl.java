
package ass.service;

import ass.repository.AssignmentRepository;
import ass.assignment.AssignmentObject;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl implements AssignmentService{

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public List<AssignmentObject> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    @Override
    public void saveAssignment(AssignmentObject assignment) {
        this.assignmentRepository.save(assignment);
    }

    @Override
    public AssignmentObject getAssignmentById(long id) {
        Optional<AssignmentObject> optional=assignmentRepository.findById(id);
        AssignmentObject assignment = null;
        if(optional.isPresent()){
            assignment = optional.get();
        }else{
                throw new RuntimeException(" Student not found for id:: " + id);
        }
        return assignment;
    }

    @Override
    public void deleteAssignmentById(long id) {
        this.assignmentRepository.deleteById(id);
    }
    
}
