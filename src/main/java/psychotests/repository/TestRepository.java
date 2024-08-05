package psychotests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import psychotests.entity.TestEntity;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<TestEntity, Long> {

    @Query("SELECT t FROM TestEntity t " +
            "WHERE t.psychologist.id =:psychologistId " +
            "AND (:isFiltered = FALSE " +
                "OR LOWER(t.name) LIKE LOWER(CONCAT('%', :folter, '%'))) " +
            "ORDER BY t.name" )
    List<TestEntity> findAllByFilter(boolean isFiltered, String filter, Long psychologistId);
}
