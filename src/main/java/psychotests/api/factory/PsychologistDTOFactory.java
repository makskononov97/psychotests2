package psychotests.api.factory;


import org.springframework.stereotype.Component;
import psychotests.api.dto.PsychologistDTO;
import psychotests.entity.PsychologistEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PsychologistDTOFactory {

    public PsychologistDTO createPsychologistDTO(PsychologistEntity entity) {
        return PsychologistDTO.builder()
                .id(entity.getId())
                .fio(entity.getFio())
                .login(entity.getLogin())
                .password(entity.getPassword())
                .build();
    }

    public List<PsychologistDTO> createPsychologistDTOList(List<PsychologistEntity> entities) {
        return entities
                .stream()
                .map(this::createPsychologistDTO)
                .collect(Collectors.toList());
    }
}
