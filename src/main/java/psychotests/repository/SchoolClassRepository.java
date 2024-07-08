package psychotests.repository;

import psychotests.entity.SchoolClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolClassRepository extends JpaRepository<SchoolClassEntity, Long> {

    void deleteByIdAndSchoolId(Long classId, Long schoolId);
}
