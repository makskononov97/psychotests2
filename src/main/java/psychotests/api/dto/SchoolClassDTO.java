package psychotests.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class SchoolClassDTO {

    @NonNull
    Long id;

    @NonNull
    String name;

    @NonNull
    SchoolDTO school;

//    public static List<SchoolDTO> createSchoolClassDTOList(List<SchoolClassEntity> entities) {
//
//        return entities
//                .stream()
//                .map(this::createSchoolClassDTO)
//                .collect(Collectors.toList());
//    }
}
