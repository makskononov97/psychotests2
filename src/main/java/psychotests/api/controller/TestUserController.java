package psychotests.api.controller;


import org.springframework.web.bind.annotation.*;
import psychotests.api.dto.AckDTO;
import psychotests.api.dto.TestUserDTO;
import psychotests.api.exception.BadRequestException;
import psychotests.api.exception.NotFoundException;
import psychotests.api.factory.TestUserDTOFactory;
import psychotests.entity.SchoolClassEntity;
import psychotests.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import psychotests.repository.SchoolClassRepository;
import psychotests.repository.TestUserRepository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@Transactional
public class TestUserController {

    TestUserRepository testUserRepository;

    TestUserDTOFactory testUserDTOFactory;

    SchoolClassRepository schoolClassRepository;


    public static final String FETCH_USERS = "/api/schools/classes/users";
    public static final String CREATE_USER = "/api/schools/classes/{classId}/users";
    public static final String DELETE_USER = "/api/schools/classes/users/{userId}";

    @GetMapping(FETCH_USERS)
    public ResponseEntity<List<TestUserDTO>> fetchUsers(String filter) {

        boolean isFiltered = !filter.trim().isEmpty();

        List<UserEntity> users = testUserRepository.findAllByFilter(isFiltered, filter);

        return ResponseEntity.ok(testUserDTOFactory.createUserDTOList(users));
    }

    @PostMapping(CREATE_USER)
    public ResponseEntity<TestUserDTO> createUser(
            @RequestParam String fullName,
            @RequestParam Instant birthday,
            @RequestParam Enum role,
            @RequestParam Long classId) {

        SchoolClassEntity schoolClass = schoolClassRepository
                .findById(classId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Class with this ID not found", classId)));

        if(testUserRepository.existsByName(fullName)) {
            throw new BadRequestException(String.format("User with name \"%s\" already exists", fullName));
        }

        UserEntity user = testUserRepository.saveAndFlush(
                UserEntity.makeDefault(fullName, birthday, role, schoolClass)
        );

        return ResponseEntity.ok(testUserDTOFactory.createUserDTO(user));
    }

    @DeleteMapping(DELETE_USER)
    public ResponseEntity<AckDTO> deleteUser(@PathVariable Long userId) {

        if (testUserRepository.existsById(userId)) {
            testUserRepository.deleteById(userId);
        }

        return ResponseEntity.ok(AckDTO.makeDefault(true));
    }

}
