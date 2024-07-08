package psychotests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psychotests.entity.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
}
