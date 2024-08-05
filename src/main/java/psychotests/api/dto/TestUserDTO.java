package psychotests.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class TestUserDTO {

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
