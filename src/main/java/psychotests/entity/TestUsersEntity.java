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
@Table(name = "test_users")
public class TestUsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @NonNull
    String answers;

    @NonNull
    Instant createAt;

    @ManyToOne
    UserEntity user;

    @ManyToOne
    TestEntity test;
}
