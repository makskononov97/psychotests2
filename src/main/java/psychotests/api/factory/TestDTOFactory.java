package psychotests.api.factory;

import org.springframework.stereotype.Component;
import psychotests.api.dto.TestDTO;
import psychotests.entity.TestEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestDTOFactory {

    public TestDTO createTestDTO(TestEntity entity) {
        return TestDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isStarted(entity.getIsStarted())
                .psychologistId(entity.getPsychologist().getId())
                .build();
    }

    public List<TestDTO> createTestDTOList(List<TestEntity> entities) {
        return entities
                .stream()
                .map(this::createTestDTO)
                .collect(Collectors.toList());
    }
}
