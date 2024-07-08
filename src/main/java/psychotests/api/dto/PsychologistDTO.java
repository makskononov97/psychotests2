package psychotests.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class PsychologistDTO {

    @NonNull
    Long id;

    @NonNull
    String fio;

    @NonNull
    String login;

    @NonNull
    String password;
}
