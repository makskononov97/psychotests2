package psychotests.api.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import psychotests.api.dto.TestDTO;
import psychotests.api.exception.BadRequestException;
import psychotests.api.exception.NotFoundException;
import psychotests.api.factory.TestDTOFactory;
import psychotests.entity.PsychologistEntity;
import psychotests.entity.TestEntity;
import psychotests.repository.PsychologistRepository;
import psychotests.repository.TestRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@Transactional
public class TestController {

    TestRepository testRepository;

    TestDTOFactory testDTOFactory;

    PsychologistRepository psychologistRepository;

    public static final String FETCH_TESTS = "/api/psychologists/{psychologistId}/tests";
    public static final String CREATE_OR_UPDATE_TEST = "/api/tests";
    public static final String DELETE_TEST = "/api/tests/{testId}";

    @GetMapping(FETCH_TESTS)
    public ResponseEntity<List<TestDTO>> fetchTests(
            @PathVariable Long psychologistId,
            @RequestParam String filter) {

        boolean isFiltered = !filter.trim().isEmpty();

        List<TestEntity> tests = testRepository.findAllByFilter(isFiltered, filter, psychologistId);

      return ResponseEntity.ok(testDTOFactory.createTestDTOList(tests));
    }


    @PostMapping(CREATE_OR_UPDATE_TEST)
    public ResponseEntity<TestDTO> createOrUpdateTest(
            @RequestParam Long testId,
            @RequestParam Optional<String> testName,
            @RequestParam Optional<Long> psychologistId){

        TestEntity test;

        if(testId != null) {
            test = testRepository
                    .findById(testId)
                    .orElseThrow(() ->
                            new NotFoundException
                                    (String.format("Test with id = \"%s\" not found", testId))
                    );

            testName.ifPresent(test::setName);

        } else {

        test = TestEntity.makeDefault();

        if(!testName.isPresent()) {
            throw new BadRequestException("Name of test can't be empty.");
        }

        if(!psychologistId.isPresent()) {
            throw new BadRequestException("Psychologist's ID can't be empty.");
        }

            PsychologistEntity psychologist = psychologistRepository
                    .findById(psychologistId.get())
                    .orElseThrow(() ->
                            new NotFoundException(
                                    String.format("Psychologist with ID \"%s\" not found", psychologistId.get())
                            )
                    );

            test.setName(testName.get());
            test.setPsychologist(psychologist);

        }

        test = testRepository.saveAndFlush(test);

        return ResponseEntity.ok(testDTOFactory.createTestDTO(test));


    }

}
