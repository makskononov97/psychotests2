package psychotests.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import psychotests.entity.PsychologistEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class TestDTO {

    @NonNull
    Long id;

    String name;

    @NonNull
    Boolean isStarted;

    @NonNull
    Long psychologistId;
}
