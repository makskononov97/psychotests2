package psychotests.api.controller;


import psychotests.api.dto.AckDTO;
import psychotests.api.dto.SchoolDTO;
import psychotests.api.exception.BadRequestException;
import psychotests.api.factory.SchoolDTOFactory;
import psychotests.entity.SchoolEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import psychotests.repository.SchoolRepository;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@Transactional
public class SchoolController {

    SchoolRepository schoolRepository;

    SchoolDTOFactory schoolDTOFactory;

    public static final String CREATE_SCHOOL = "/api/schools/{schoolName}";
    public static final String FETCH_SCHOOLS = "/api/schools";
    public static final String DELETE_SCHOOL = "/api/schools/{schoolId}";

    @PostMapping(CREATE_SCHOOL)
    public ResponseEntity<SchoolDTO> createSchool(@PathVariable String schoolName) {

        if(schoolRepository.existsByName(schoolName)) {
            throw new BadRequestException(String.format("School with name \"%s\" already exists", schoolName));
        }

        SchoolEntity school = schoolRepository.saveAndFlush(
                SchoolEntity.makeDefault(schoolName)
        );

        return ResponseEntity.ok(schoolDTOFactory.createSchoolDTO(school));
    }

    @GetMapping(FETCH_SCHOOLS)
    public ResponseEntity<List<SchoolDTO>> fetchSchool(String filter) {

        boolean isFiltered = !filter.trim().isEmpty();

        List<SchoolEntity> schools = schoolRepository.findAllByFilter(isFiltered, filter);

        // мы тут список будем получать. Тебе ж SQL выдаст лист школ.
        return ResponseEntity.ok(schoolDTOFactory.createSchoolDTOList(schools));
    }

    @DeleteMapping(DELETE_SCHOOL)
    public ResponseEntity<AckDTO> deleteSchool(@PathVariable Long schoolId) {

        if (schoolRepository.existsById(schoolId)) {
            schoolRepository.deleteById(schoolId);
        }

        return ResponseEntity.ok(AckDTO.makeDefault(true));
    }
}
