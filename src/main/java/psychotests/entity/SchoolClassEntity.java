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
@Table(name = "school_class")
public class SchoolClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @NonNull
    String name;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    SchoolEntity school;

    @Column(name = "school_id", updatable = false, insertable = false)
    Long schoolId;


    public static SchoolClassEntity makeDefault(String name, SchoolEntity school) {
        return SchoolClassEntity.builder()
                .name(name)
                .school(school)
                .build();
    }
}
