package psychotests.api.factory;

import psychotests.api.dto.SchoolClassDTO;
import psychotests.entity.SchoolClassEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SchoolClassDTOFactory {

    SchoolDTOFactory schoolDTOFactory;

    public SchoolClassDTO createSchoolClassDTO(SchoolClassEntity entity) {

        return SchoolClassDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .school(schoolDTOFactory.createSchoolDTO(entity.getSchool()))
                .build();
    }

    public List<SchoolClassDTO> createSchoolClassDTOList(List<SchoolClassEntity> entities) {

        return entities
                .stream()
                .map(this::createSchoolClassDTO)
                .collect(Collectors.toList());
    }
}
