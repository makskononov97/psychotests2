package psychotests.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class SchoolDTO {

    @NonNull
    Long id;

    @NonNull
    String name;
}
