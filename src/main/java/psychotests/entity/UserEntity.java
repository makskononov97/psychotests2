package psychotests.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @NonNull
    String fullName;

    @NonNull
    Instant birthday;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    Enum role;

    @ManyToOne
    SchoolClassEntity schoolClass;

    public static UserEntity makeDefault(
            String fullName,
            Instant birthday,
            Enum role,
            SchoolClassEntity schoolClass) {
        return builder()
                .fullName(fullName)
                .birthday(birthday)
                .role(role)
                .schoolClass(schoolClass)
                .build();
    }
}
