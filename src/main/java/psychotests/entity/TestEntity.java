package psychotests.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@Table(name = "test")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String name;

    @Builder.Default
    Boolean isStarted = false;

    @ManyToOne
    //@JoinColumn(table = "psychologist")
    PsychologistEntity psychologist;

    public static TestEntity makeDefault() {
        return builder()
                .build();
    }


}
