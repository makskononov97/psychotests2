package psychotests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psychotests.entity.PsychologistEntity;

public interface PsychologistRepository extends JpaRepository<PsychologistEntity, Long> {
}
