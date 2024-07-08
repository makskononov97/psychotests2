package psychotests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import psychotests.entity.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByName(String fullName);

    @Query("SELECT u from UserEntity u " +
           "WHERE :isFiltered = FALSE " +
           "AND LOWER(u.fullName) LIKE LOWER(CONCAT('%', :filter, '%') )")
    List<UserEntity> findAllByFilter(boolean isFiltered, String filter);
}
