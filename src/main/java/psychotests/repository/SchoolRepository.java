package psychotests.repository;

import psychotests.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {

    boolean existsByName(String name);

    @Query("SELECT s FROM SchoolEntity s " +
            "WHERE LOWER(s.name) like LOWER(CONCAT('%', :filter, '%')) " +
            "OR :isFiltered = FALSE " +
            "order by s.name DESC")
    List<SchoolEntity> findAllByFilter(boolean isFiltered, String filter);
}
