package psychotests.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import psychotests.entity.SchoolClassEntity;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class UserDTO {

    @NonNull
    Long id;

    @NonNull
    String fullName;

    @NonNull
    Instant birthday;

    @NonNull
    Enum role;

    @NonNull
    Long schoolClassId;

}
