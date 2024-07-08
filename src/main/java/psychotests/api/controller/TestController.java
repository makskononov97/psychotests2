package psychotests.api.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@Transactional
public class TestController {

    public static final String FETCH_TESTS = "/api/tests";
    public static final String CREATE_OR_UPDATE_TEST = "/api/tests";
    public static final String DELETE_TEST = "/api/tests/{testId}";


//    @PostMapping(CREATE_OR_UPDATE_TEST)
//    public ResponseEntity<?> createOrUpdateTest(
//            @RequestParam Optional<Long> testId,
//            @RequestParam Optional<String> testName,
//            @RequestParam...
//    )
}
