
package ass.repository;

import ass.assignment.AssignmentObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentObject, Long>{
    
}
