package psychotests.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class AckDTO {

    boolean answer;

    public static AckDTO makeDefault(boolean answer) {
        return builder()
                .answer(answer)
                .build();
    }
}
