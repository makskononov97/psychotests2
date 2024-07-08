package psychotests.api.controller;


import org.springframework.web.bind.annotation.*;
import psychotests.api.dto.AckDTO;
import psychotests.api.dto.UserDTO;
import psychotests.api.exception.BadRequestException;
import psychotests.api.exception.NotFoundException;
import psychotests.api.factory.UserDTOFactory;
import psychotests.entity.SchoolClassEntity;
import psychotests.entity.SchoolEntity;
import psychotests.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import psychotests.repository.SchoolClassRepository;
import psychotests.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@Transactional
public class UserController {

    UserRepository userRepository;

    UserDTOFactory userDTOFactory;

    SchoolClassRepository schoolClassRepository;


    public static final String FETCH_USERS = "/api/schools/classes/users";
    public static final String CREATE_USER = "/api/schools/classes/{classId}/users";
    public static final String DELETE_USER = "/api/schools/classes/users/{userId}";

    @GetMapping(FETCH_USERS)
    public ResponseEntity<List<UserDTO>> fetchUsers(String filter) {

        boolean isFiltered = !filter.trim().isEmpty();

        List<UserEntity> users = userRepository.findAllByFilter(isFiltered, filter);

        return ResponseEntity.ok(userDTOFactory.createUserDTOList(users));
    }

    @PostMapping(CREATE_USER)
    public ResponseEntity<UserDTO> createUser(
            @RequestParam String fullName,
            @RequestParam Instant birthday,
            @RequestParam Enum role,
            @RequestParam Long classId) {

        SchoolClassEntity schoolClass = schoolClassRepository
                .findById(classId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Class with this ID not found", classId)));

        if(userRepository.existsByName(fullName)) {
            throw new BadRequestException(String.format("User with name \"%s\" already exists", fullName));
        }

        UserEntity user = userRepository.saveAndFlush(
                UserEntity.makeDefault(fullName, birthday, role, schoolClass)
        );

        return ResponseEntity.ok(userDTOFactory.createUserDTO(user));
    }

    @DeleteMapping(DELETE_USER)
    public ResponseEntity<AckDTO> deleteUser(@PathVariable Long userId) {

        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        }

        return ResponseEntity.ok(AckDTO.makeDefault(true));
    }

}
