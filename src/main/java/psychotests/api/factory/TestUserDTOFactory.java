package psychotests.api.factory;

import org.springframework.stereotype.Component;
import psychotests.api.dto.TestUserDTO;
import psychotests.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestUserDTOFactory {

    public TestUserDTO createUserDTO(UserEntity user) {

        return TestUserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .birthday(user.getBirthday())
                .role(user.getRole())
                .schoolClassId(user.getSchoolClass().getId())
                .build();
    }

    public List<TestUserDTO> createUserDTOList(List<UserEntity> users) {

        return users
                .stream()
                .map(this::createUserDTO)
                .collect(Collectors.toList());
    }

}
