
package ass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ass.studentobject.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    
}
