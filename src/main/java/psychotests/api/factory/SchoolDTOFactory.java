package psychotests.api.factory;

import psychotests.api.dto.SchoolDTO;
import psychotests.entity.SchoolEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SchoolDTOFactory {

    //for SchoolController.createSchool
    public SchoolDTO createSchoolDTO(SchoolEntity entity) {

        return SchoolDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    //for SchoolController.fetchSchool - иначе List не сбилдится
    //я пока не буду это писать, так как не верю, что без этого не зарабоатет.
    //окей блять, пришлось писать, Java заставила
    //Если б использовал структуру без DTo и DTOFactories, то возвращал бы Entities тупо
    //учи лямбды, пацан. stream().map(). че да как.
    public List<SchoolDTO> createSchoolDTOList(List<SchoolEntity> entities) {

        return entities
                .stream()
                .map(this::createSchoolDTO)
                .collect(Collectors.toList());
    }
}
