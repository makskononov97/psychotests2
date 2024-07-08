package psychotests.api.factory;

import org.springframework.stereotype.Component;
import psychotests.api.dto.SchoolDTO;
import psychotests.api.dto.UserDTO;
import psychotests.entity.SchoolEntity;
import psychotests.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDTOFactory {

    public UserDTO createUserDTO(UserEntity user) {

        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .birthday(user.getBirthday())
                .role(user.getRole())
                .schoolClassId(user.getSchoolClass().getId())
                .build();
    }

    public List<UserDTO> createUserDTOList(List<UserEntity> users) {

        return users
                .stream()
                .map(this::createUserDTO)
                .collect(Collectors.toList());
    }

}
