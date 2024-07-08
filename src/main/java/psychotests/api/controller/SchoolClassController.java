package psychotests.api.controller;

import psychotests.api.dto.AckDTO;
import psychotests.api.dto.SchoolClassDTO;
import psychotests.api.exception.NotFoundException;
import psychotests.api.factory.SchoolClassDTOFactory;
import psychotests.entity.SchoolClassEntity;
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
import psychotests.repository.SchoolClassRepository;
import psychotests.repository.SchoolRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@Transactional
public class SchoolClassController {

    SchoolRepository schoolRepository;

    SchoolClassRepository schoolClassRepository;

    SchoolClassDTOFactory schoolClassDTOFactory;

    public static final String  FETCH_SCHOOL_CLASSES = "/api/schools/{schoolId}/classes";
    public static final String  CREATE_SCHOOL_CLASS = "/api/schools/{schoolId}/classes/{className}";
    public static final String DELETE_SCHOOL_CLASS = "/api/schools/{schoolId}/classes/{classId}";

    @GetMapping(FETCH_SCHOOL_CLASSES)
    public ResponseEntity<List<SchoolClassDTO>> fetchClasses(
            @PathVariable String prefix,
            @PathVariable Long schoolId){

        SchoolEntity school = getSchoolOrThrowNotFound(schoolId);

        List<SchoolClassEntity> schoolClasses = school.getSchoolClasses()
                .stream()
                .filter(it -> it.getName().trim().toLowerCase().startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());


        return ResponseEntity.ok(schoolClassDTOFactory.createSchoolClassDTOList(schoolClasses));
    }

    @PostMapping(CREATE_SCHOOL_CLASS)
    public ResponseEntity<SchoolClassDTO> createSchoolClass(
            @PathVariable String className,
            @PathVariable Long schoolId) {

        SchoolEntity school = getSchoolOrThrowNotFound(schoolId);

        SchoolClassEntity schoolClass = schoolClassRepository
                .saveAndFlush(SchoolClassEntity.makeDefault(className.toUpperCase(), school));

        return ResponseEntity.ok(schoolClassDTOFactory.createSchoolClassDTO(schoolClass));
    }

    @DeleteMapping(DELETE_SCHOOL_CLASS)
    public ResponseEntity<AckDTO> deleteSchoolClass(
            @PathVariable Long schoolId,
            @PathVariable Long classId) {

        schoolClassRepository.deleteByIdAndSchoolId(classId, schoolId);

        return ResponseEntity.ok(AckDTO.makeDefault(true));
    }

    private SchoolEntity getSchoolOrThrowNotFound(Long schoolId) {
        return schoolRepository
                .findById(schoolId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("School with this ID not found", schoolId)));
    }

}
